package com.ad.firstkotlinapp.model

/**
 * Created by anastasiia on 15.12.17.
 */

//В Kotlin первичный конструктор является частью заголовка класса.
// Если вы не хотите предоставлять второй конструктор, это всё!
// Ваша работа по созданию класса завершена здесь.
// Нет параметров конструктора для назначений полей, нет геттеров и сеттеров.
// Целый класс в одной строке!
class Repository(var repositoryName: String?,
                 var repositoryOwner: String?,
                 var numberOfStars: Int?,
                 var hasIssues: Boolean = false)