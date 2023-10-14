package com.naumov.mytestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naumov.mytestapp.common.media.AudioRecord
import com.naumov.mytestapp.model.SpeechData
import com.naumov.mytestapp.model.ViewStateData
import com.naumov.mytestapp.repository.TranslateSpeechToText
import com.naumov.mytestapp.repository.TranslateSpeechToTextImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class DataViewModel(private val translateSpeechToText: TranslateSpeechToText = TranslateSpeechToTextImpl()) :
    ViewModel() {

    private val errorMakeAudio = { }
    private val succesMakeaudio = { }

    private val _mutabelData = MutableLiveData<ViewStateData>()
    val mutabelData = _mutabelData

    val audioRecord:AudioRecord = AudioRecord()

    private val coroutineScoupe = CoroutineScope(
        SupervisorJob() +
                CoroutineExceptionHandler { _, throwable ->
                    handleError(throwable)}
    )
    private fun handleError(throwable: Throwable) {
        _mutabelData.postValue(ViewStateData.ErrorService(throwable))
    }

    fun subscribe():LiveData<ViewStateData> = mutabelData

    private fun getTranslate() {
        cancelJob()
        _mutabelData.postValue(ViewStateData.Loading)
        coroutineScoupe.launch { translateSpeechToText.getFakeText().map { convertToSpeechData(it) }}
    }

    private fun convertToSpeechData(it: String):SpeechData {
           return SpeechData(it)
    }

    fun onPressButtonRecord():Boolean {

       audioRecord.startRecord()
       return true
    }

    override fun onCleared() {
        _mutabelData.postValue(ViewStateData.Success(null))
        super.onCleared()
        cancelJob()
    }

    private fun cancelJob() {
        coroutineScoupe.coroutineContext.cancelChildren()
    }


}
