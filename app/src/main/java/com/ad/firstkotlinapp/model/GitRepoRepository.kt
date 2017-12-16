package com.ad.firstkotlinapp.model

import com.ad.firstkotlinapp.NetManagerWrapper

/**
 * Created by anastasiia on 16.12.17.
 * Model
 * выполняет функцию хранения данных (не модель объекта Car/Fish/Repository/.. ВАЖНО!))
 */
class GitRepoRepository(val netManager: NetManagerWrapper) {

    val localDataSource = GitRepoLocalDataSource()
    val remoteDataSource = GitRepoRemoteDataSource()

    //имитирование получения данных
//    fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
//        Handler().postDelayed({ onDataReadyCallback.onDataReady("new data") }, 2000)
//    }

//    fun getRepositories(onRepositoryReadyCallback: OnRepositoryReadyCallback) {
//        var arrayList = ArrayList<Repository>()
//        arrayList.add(Repository("First", "Owner 1", 100 , false))
//        arrayList.add(Repository("Second", "Owner 2", 30 , true))
//        arrayList.add(Repository("Third", "Owner 3", 430 , false))
//
//        Handler().postDelayed({ onRepositoryReadyCallback.onDataReady(arrayList) },2000)
//    }

//    interface OnDataReadyCallback {
//        fun onDataReady(data: String)
//    }

    fun getRepositories(onRepositoryReadyCallback: OnRepositoryReadyCallback) {

        //let, которая по сути выполняет 2 задачи:
        // позволяет вызвать код, если какое-то значение не равно null
        // и перекладывает это значение в переменную it
        netManager.isConnectedToInternet?.let {
            if (it) {
                //если интернет есть
                //получаем данные с удаленного источника
                remoteDataSource.getRepositories(object : OnRepoRemoteReadyCallback {
                    override fun onRemoteDataReady(data: ArrayList<Repository>) {
                        localDataSource.saveRepositories(data)                              //сохраняем локально
                        onRepositoryReadyCallback.onDataReady(data)                         //отдаем для отображения
                    }
                })
            } else {
                //если интернета нет
                //сразу запрашиваем данные из локального источника (например, БД)
                localDataSource.getRepositories(object : OnRepoLocalReadyCallback {
                    override fun onLocalDataReady(data: ArrayList<Repository>) {
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            }

        }
    }
}

interface OnRepositoryReadyCallback {
    fun onDataReady(data: ArrayList<Repository>)
}