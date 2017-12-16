package com.ad.firstkotlinapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.ad.firstkotlinapp.adapters.RepositoryRecyclerViewAdapter
import com.ad.firstkotlinapp.databinding.ActivityMainBinding
import com.ad.firstkotlinapp.model.Repository
import com.ad.firstkotlinapp.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), RepositoryRecyclerViewAdapter.OnItemClickListener {


    lateinit var binding: ActivityMainBinding
//    var mainViewModel = MainViewModel()

    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //вместо setContentView(R.layout.activity_main)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        var rect: Rect = Rect()     //объект класса Rect (java)
//        var side: Int = rect.a    //то же что и int side = square.getA(); в java
//        var rect2: Rect2 = Rect2();  //объект класса Rect2 (Kotlin)

        //apply позволяет вам вызывать несколько методов на одном экземпляре.
//        binding.repositoryName.text = "Article"
//        binding.apply {
//            repositoryName.text = "Medium Android Repository Article"
//            repositoryOwner.text = "Fleka"
//            numberOfStarts.text = "1000 stars"
//        }

//        var repository: Repository = Repository("Habrahabr Android Repository Article",
//                "Fleka",
//                1000,
//                true)
//        binding.repository = repository
//        binding.viewModel = mainViewModel
//        binding.executePendingBindings()

        //не работает
        //Handler().postDelayed({repository.repositoryName="New Name"}, 2000)

        //работает, но лучше использовать паттерны Property Observer (RxJava)
        //        Handler().postDelayed({
        //            repository.repositoryName="New Name"
        //            binding.repository = repository
        //            binding.executePendingBindings()},
        //                2000)

        //работает благодаря BaseObservable
//        Handler().postDelayed({
//            repository.repositoryName = "New Name"
//        }, 2000)



        //без привязки к жизненному циклу активности
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.viewModel = mainViewModel
//        binding.executePendingBindings()

        //с привязкой к жизненному циклу активности
        // Не создаем новый экземпляр MainViewModel.
        // Теперь мы получаем его от ViewModelProviders.
        // ViewModelProviders - это класс, который имеет методы для получения ViewModelProvider.
        // Если вызывать ViewModelProviders.of (this) в Activity ,
        // то ViewModel будет жить до тех пор, пока активность будет существовать
        // (после Activity.onDestroy вызовется VievModel.onCleared()).
        // Следовательно, если вызвать это в Fragment, ViewModel будет жить до тех пор,
        // пока этот фрагмент не будет разрушен.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //MainViewModel::class не бутет работать правильно
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.rvRepository.layoutManager = LinearLayoutManager(this)
//        binding.rvRepository.adapter = RepositoryRecyclerViewAdapter(viewModel.repositories, this)
        binding.rvRepository.adapter = repositoryRecyclerViewAdapter
        viewModel.repositories.observe(this,
                Observer<ArrayList<Repository>> { it?.let{ repositoryRecyclerViewAdapter.replaceData(it)} })

    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
