package com.naumov.mytestapp.model

data class SpeechData (private val result:String){
    val _result:String
    get() = result.trim()
}