package com.ad.firstkotlinapp.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.ad.firstkotlinapp.NetManagerWrapper
import com.ad.firstkotlinapp.model.GitRepoRepository
import com.ad.firstkotlinapp.model.OnRepositoryReadyCallback
import com.ad.firstkotlinapp.model.Repository

/**
 * Created by anastasiia on 16.12.17.
 * ViewModel
 * выполняет одну функцию - получает и обрабатывает данные
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    var gitRepoRepository: GitRepoRepository = GitRepoRepository(NetManagerWrapper(getApplication()))          //экземпляр класса GitRepoRepository
    val text = ObservableField<String>()
    val isLoading = ObservableField<Boolean>()      //boolean для хранения текущего состояния

    // LiveData - еще один компонент, поддерживающий жизненный цикл.
    // Наблюдает за жизненным циклом View.
    // Итак, однажды, когда Activity разрушена из-за изменений конфигурации,
    // LiveData знает об этом, поэтому она также разрушает наблюдателя от разрушенной активности.
    var repositories = MutableLiveData<ArrayList<Repository>>()



//    если нужно инициализировать поля
//    val text = ObservableField("old data")
//    val isLoading = ObservableField(false)

    //var - переменная
    //val - final ссылка
    //Используйте var, когда переменная меняется и val,
    //когда значение переменной не изменяется после первого присвоения.
    //val похож на ключевое слово readonly в C # или ключевое слово final  в Java.
    //val переменная должна быть инициализирована при объявлении


//    fun refresh() {
//        isLoading.set(true)
//        gitRepoRepository.refreshData(object : GitRepoRepository.OnDataReadyCallback {
//            override fun onDataReady(data: String) {
//                isLoading.set(false)
//                text.set(data)
//            }
//        })
//    }

    fun loadRepositories(){
        isLoading.set(true)
        gitRepoRepository.getRepositories(object : OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }


}