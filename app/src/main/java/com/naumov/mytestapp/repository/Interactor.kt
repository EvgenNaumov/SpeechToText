package com.naumov.mytestapp.repository

import com.naumov.mytestapp.model.SpeechData
import java.io.File
import kotlinx.coroutines.flow.Flow

interface Interactor {
    val fromFlow: Int
    val fromFile: Int
    val fromFileSynch:Int
    val fromFileAsynch:Int
    val fromFake:Int
    suspend fun getData(typeGetData:Int, fileAudio: File?): Flow<String>
}