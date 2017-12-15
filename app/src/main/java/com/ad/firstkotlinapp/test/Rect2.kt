package com.ad.firstkotlinapp.test

/**
 * Created by anastasiia on 15.12.17.
 */
class Rect2 {
    var a:Int
        set(value) { field = Math.abs(value) }          //сеттер, field и есть поле a

        //set(value) { a = Math.abs(value) } так делать нельзя,будет рекурсия
        //Это означает, что вы вызываете метод set внутри метода set,
        // потому что нет прямого доступа к свойству в мире Kotlin

    var b:Int
        set(value) { field = Math.abs(value) }          //сеттер, field и есть поле b

    constructor(){
        a=1
        b=2
    }

    constructor(a: Int, b: Int) {
        this.a = a
        this.b = b
    }



}