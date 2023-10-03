package com.naumov.mytestapp.repository

import kotlinx.coroutines.flow.Flow
import java.io.File

interface TranslateSpeechToText {

    fun getTextFromSpeechOutFlow():Flow<String>

    fun getTextFromSpeechAudioFile(fileAudio:File):Flow<String>

    fun getTextFromSpeechAudioFileSynch(fileAudio:File):Flow<String>

    fun getTextFromSpeechAudioFileAsynch(fileAudio:File):Flow<String>

    fun getFakeText():Flow<String>
}