package com.ad.firstkotlinapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.ad.firstkotlinapp.databinding.ActivityMainBinding
import com.ad.firstkotlinapp.model.Repository
import com.ad.firstkotlinapp.test.Rect
import com.ad.firstkotlinapp.test.Rect2

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //вместо setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var rect: Rect = Rect()     //объект класса Rect (java)
        var side: Int = rect.a    //то же что и int side = square.getA(); в java
        var rect2: Rect2 = Rect2();  //объект класса Rect2 (Kotlin)


        //apply позволяет вам вызывать несколько методов на одном экземпляре.
        binding.repositoryName.text = "Article"
        binding.apply {
            repositoryName.text = "Medium Android Repository Article"
            repositoryOwner.text = "Fleka"
            numberOfStarts.text = "1000 stars"
        }


        var repository: Repository = Repository("Habrahabr Android Repository Article",
                "Fleka",
                1000,
                true)

        binding.repository = repository
        binding.executePendingBindings()


        //не работает
        //Handler().postDelayed({repository.repositoryName="New Name"}, 2000)
        //работает, но лучше использовать паттерны Property Observer (RxJava)
        Handler().postDelayed({
            repository.repositoryName="New Name"
            binding.repository = repository
            binding.executePendingBindings()},
                2000)

    }
}
