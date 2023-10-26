package com.naumov.mytestapp.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class MainInteractor(val repo:TranslateSpeechToText = TranslateSpeechToTextImpl()):Interactor{
    override val fromFlow: Int
        get() = 1
    override val fromFile: Int
        get() = 2
    override val fromFileSynch: Int
        get() = 3
    override val fromFileAsynch: Int
        get() = 4
    override val fromFake: Int
        get() = 5

    override suspend fun getData(fileAudio: File, typeGetData: Int): Flow<String> {
        when(typeGetData){
            fromFlow->{return repo.getTextFromSpeechOutFlow()}
            fromFile->{return repo.getTextFromSpeechAudioFile(fileAudio)}
            fromFileSynch->{return repo.getTextFromSpeechAudioFileSynch(fileAudio)}
            fromFileAsynch->{return repo.getTextFromSpeechAudioFileAsynch(fileAudio)}
            fromFake->{return repo.getFakeText()}
            else ->return flow { emit("") }
        }
    }
}