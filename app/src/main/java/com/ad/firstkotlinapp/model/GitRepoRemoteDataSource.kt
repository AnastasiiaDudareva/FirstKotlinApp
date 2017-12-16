package com.ad.firstkotlinapp.model

import android.os.Handler

/**
 * Created by anastasiia on 16.12.17.
 */

//класс для работы с фейковыми удаленными ресурсами
class GitRepoRemoteDataSource {


    //получение данных (например, запрос к серверу)
    fun getRepositories(onRepositoryReadyCallback: OnRepoRemoteReadyCallback) {
        var arrayList = ArrayList<Repository>()
        arrayList.add(Repository("First from remote", "Owner 1", 100, false))
        arrayList.add(Repository("Second from remote", "Owner 2", 30, true))
        arrayList.add(Repository("Third from remote", "Owner 3", 430, false))

        Handler().postDelayed({ onRepositoryReadyCallback.onRemoteDataReady(arrayList) }, 2000)
    }
}

interface OnRepoRemoteReadyCallback {
    fun onRemoteDataReady(data: ArrayList<Repository>)
}