package com.naumov.mytestapp.utils

import com.naumov.mytestapp.BuildConfig

const val DEBUG_TAG_NETWORK = "NetworkStatus"
const val KEY_PREFER = "KeyPref"
const val TAG = "DEBUG_TAG"
var DEBUG_ON = BuildConfig.BUILD_TYPE == "debug"

const val REQUEST_CODE_WRITE_EXT_STORAGE = 12
const val REQUEST_READ_EXTERNAL_STORAGE = 11
const val REQUEST_READ_MEDIA_AUDIO = 13

const val DIR_AUDIO_SAMPLE = "AudioSample"
const val PREF_AUDIO_FILES = "Aui_"
const val EXT_AUDIO_FILES = ".mp3"
const val MAX_SIZE_AUDIO_FILES_MB = 1024
const val MAX_LENGTH_AUDIO_FILES_SEC = 30
const val MAX_QUANTITY_AUDIO_CANAL = 1

const val BASE_URL = "https://stt.api.cloud.yandex.net"