package com.ad.firstkotlinapp.model

import android.os.Handler

/**
 * Created by anastasiia on 16.12.17.
 */

//класс для фейковой работы с локальными данными
class GitRepoLocalDataSource {

    //фейковое получение данных (например, получение из БД)
    fun getRepositories(onRepositoryReadyCallback: OnRepoLocalReadyCallback) {
        var arrayList = ArrayList<Repository>()
        arrayList.add(Repository("First From Local", "Owner 1", 100, false))
        arrayList.add(Repository("Second From Local", "Owner 2", 30, true))
        arrayList.add(Repository("Third From Local", "Owner 3", 430, false))

        Handler().postDelayed({ onRepositoryReadyCallback.onLocalDataReady(arrayList) }, 2000)
    }

    //фейковое сохранение данных (например, сохранение в БД)
    fun saveRepositories(arrayList: ArrayList<Repository>){
        //todo save repositories in DB
    }
}

interface OnRepoLocalReadyCallback {
    fun onLocalDataReady(data: ArrayList<Repository>)
}