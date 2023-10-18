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
import kotlinx.coroutines.flow.onEach
import java.io.File

class DataViewModel(private val translateSpeechToText: TranslateSpeechToText = TranslateSpeechToTextImpl()) :
    ViewModel() {

    private val errorMakeAudio = { }
    private val succesMakeaudio = { }

    private val _mutabelData = MutableLiveData<ViewStateData>()
    val mutabelData = _mutabelData


    private val coroutineScoupe = CoroutineScope(
        SupervisorJob() +
                CoroutineExceptionHandler { _, throwable ->
                    handleError(throwable)
                }
    )

    private fun handleError(throwable: Throwable) {
        _mutabelData.postValue(ViewStateData.ErrorService(throwable))
    }

    fun subscribe(): LiveData<ViewStateData> = mutabelData

    private fun getTranslate() {
        cancelJob()
        _mutabelData.postValue(ViewStateData.Loading)
        coroutineScoupe.launch {
            startInteractor()
        }
    }


    suspend fun startInteractor() =
        withContext(Dispatchers.IO) {

            translateSpeechToText.getFakeText()
                .collect { _mutabelData.postValue(ViewStateData.Success(SpeechData(it))) }
        }

    fun onPressButtonRecord(): Boolean {

        getTranslate()
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
