package com.naumov.mytestapp.model

sealed class ViewStateActivity{

    object errorAudio: ViewStateActivity()
    object errorFile: ViewStateActivity()
    object errorService:ViewStateActivity()
    object success:ViewStateActivity()
    data class SpeechData(private val result:String):ViewStateActivity()

}
