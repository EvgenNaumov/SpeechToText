package com.naumov.mytestapp.common.media

import android.media.AudioManager
import android.media.AudioRecordingConfiguration
import android.media.MediaRecorder
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore.Audio
import android.util.Log
import android.widget.Toast
import com.naumov.mytestapp.App
import com.naumov.mytestapp.utils.DEBUG_ON
import com.naumov.mytestapp.utils.DEBUG_TAG
import java.io.File
import java.io.IOException
import java.util.concurrent.Executor

class AudioRecord {

    private var mediaRecorder: MediaRecorder? = null
    private lateinit var fileOutput: File
    private lateinit var mAudio: Audio

    private var isRecording = false

    fun prepareMediaRecorder(): Boolean {

        val file = AudioHelper.getInstance().getOutputMediaFile {
            Toast.makeText(App.cont, "Error get file", Toast.LENGTH_SHORT).show()
        } ?: return false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            mediaRecorder = MediaRecorder(App.cont).apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.OGG)
                setOutputFile(file)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                //регистрация callback
                registerAudioRecordingCallback(CallbaclAudioRecord(), object : AudioManager.AudioRecordingCallback() {
                    override fun onRecordingConfigChanged(configs: MutableList<AudioRecordingConfiguration>?) {
                        super.onRecordingConfigChanged(configs)

                    }
                })
                setOnErrorListener(onErrorListenerMediaRecord())
                //регистрация callback
                try {
                    prepare()

                } catch (e: IllegalStateException) {
                    Log.d(DEBUG_TAG, "IllegalStateException preparing MediaRecorder: " + e.message);
                    releaseMediaRecorder();
                    return false;
                } catch (e: IOException) {
                    Log.d(DEBUG_TAG, "IOException preparing MediaRecorder: " + e.message);
                    releaseMediaRecorder();
                    return false;
                }
            }
        }
        return true
    }

    private fun releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder!!.reset()
            mediaRecorder!!.release()
            mediaRecorder = null

        }
    }

    fun startRecord() {
        if (!prepareMediaRecorder()) {
            releaseMediaRecorder()
            return
        }

        Thread() {

            mediaRecorder?.start()
            isRecording = true

        }.start()

    }

    fun stopRecord() {
        if (isRecording) {

            try {
                mediaRecorder?.stop()
            } catch (e: java.lang.RuntimeException) {
                if (DEBUG_ON) {
                    Log.d(DEBUG_TAG, "RuntimeException: stop() is called immediately after start()")
                    fileOutput.delete()
                }
            }

            releaseMediaRecorder()
        }
    }

    inner class CallbaclAudioRecord : Executor {
        override fun execute(command: Runnable?) {
            command.run {

            }
        }
    }

    inner class onErrorListenerMediaRecord: MediaRecorder.OnErrorListener {
        override fun onError(mr: MediaRecorder?, what: Int, extra: Int) {

        }
    }

}