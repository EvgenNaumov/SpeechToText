package com.naumov.mytestapp.viewmodel

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.ViewModel
import com.naumov.mytestapp.App
import com.naumov.mytestapp.common.media.AudioHelper
import com.naumov.mytestapp.model.SpeechData
import com.naumov.mytestapp.network.ManagerNetworkConnect
import com.naumov.mytestapp.repository.TranslateSpeechToText

class MainActivityViewModel(private val translateSpeechToText: TranslateSpeechToText):ViewModel() {

    private val errorMakeAudio = { }
    private val succesMakeaudio = { }

    private val mutabelStateFlow = mutableListOf<SpeechData>()

    private fun getTranslate(){

    }

    fun onPressButtonRecord(){

        if (!ManagerNetworkConnect.instanceNet(App().applicationContext).getStatusCurrentNetwork()){

        }

        AudioHelper.getInstance().getOutputMediaFile {
            Toast.makeText(App.cont,"Error create file audio", LENGTH_SHORT).show()
        }
    }
}