package com.naumov.mytestapp.repository

import com.naumov.mytestapp.App
import com.naumov.mytestapp.R
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.File
import kotlin.coroutines.coroutineContext

class TranslateSpeechToTextImpl():TranslateSpeechToText{

    override suspend fun getTextFromSpeechOutFlow(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getTextFromSpeechAudioFile(fileAudio: File): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getTextFromSpeechAudioFileSynch(fileAudio: File): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getTextFromSpeechAudioFileAsynch(fileAudio: File): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getFakeText(): Flow<String> {

        return flowOf(App.resours.getString(R.string.text_fake_metod_translate))
    }
}