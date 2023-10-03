package com.naumov.mytestapp.repository

import com.naumov.mytestapp.App
import com.naumov.mytestapp.R
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.flow.Flow
import java.io.File
import kotlin.coroutines.coroutineContext

class TranslateSpeechToTextImpl():TranslateSpeechToText{

    override fun getTextFromSpeechOutFlow(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getTextFromSpeechAudioFile(fileAudio: File): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getTextFromSpeechAudioFileSynch(fileAudio: File): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getTextFromSpeechAudioFileAsynch(fileAudio: File): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getFakeText(): Flow<String> {
        TODO("Not yet implemented")
        //return coroutineContext{<App.resours.getString(R.string.text_fake_metod_translate)>
    }
}