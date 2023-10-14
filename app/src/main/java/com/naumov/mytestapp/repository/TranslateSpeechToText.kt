package com.naumov.mytestapp.repository

import kotlinx.coroutines.flow.Flow
import java.io.File

interface TranslateSpeechToText {

    suspend fun getTextFromSpeechOutFlow():Flow<String>

    suspend fun getTextFromSpeechAudioFile(fileAudio:File):Flow<String>

    suspend fun getTextFromSpeechAudioFileSynch(fileAudio:File):Flow<String>

    suspend fun getTextFromSpeechAudioFileAsynch(fileAudio:File):Flow<String>

    suspend fun getFakeText():Flow<String>
}