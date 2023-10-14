package com.naumov.mytestapp.model

sealed class ViewStateData{

    data class ErrorAudio(private val error: Throwable): ViewStateData()
    data class ErrorFile(private val error: Throwable): ViewStateData()
    data class ErrorService(private val error: Throwable):ViewStateData()
    object Loading:ViewStateData()
    data class Success( val resultSpeech:SpeechData?):ViewStateData()

}
