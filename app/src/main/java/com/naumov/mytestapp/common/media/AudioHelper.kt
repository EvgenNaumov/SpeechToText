package com.naumov.mytestapp.common.media

import android.annotation.SuppressLint
import android.os.Environment
import android.util.Log
import com.naumov.mytestapp.utils.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class AudioHelper {

    @SuppressLint("SimpleDateFormat")
    fun getOutputMediaFile(errorFile:()->Unit): File? {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null
        }

        val mediaStorageDir = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            ), DIR_AUDIO_SAMPLE
        )

        try {
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdir()) {
                    if (DEBUG_ON) {
                        Log.d(TAG, "getOutputMediaFile: error mkdir ${DIR_AUDIO_SAMPLE}")
                        return null
                    }
                }
            }
        } catch (e:SecurityException){
            errorFile()
        }

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        try {
            var mediaFile = File(mediaStorageDir.path + File.separator + PREF_AUDIO_FILES + timeStamp + EXT_AUDIO_FILES)
            return mediaFile
        } catch (e:java.lang.NullPointerException ){
            errorFile()
          return null
        }
    }


    companion object {
        fun getInstance(): AudioHelper {
            return AudioHelper()
        }
    }
}