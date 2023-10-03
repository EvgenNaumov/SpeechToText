package com.naumov.mytestapp.utils

import android.os.Build
import com.naumov.mytestapp.BuildConfig

const val DEBUG_TAG_NETWORK = "NetworkStatus"
const val KEY_PREFER = "KeyPref"
const val DEBUG_TAG = "DEBUG_TAG"
var DEBUG_ON = BuildConfig.BUILD_TYPE == "debug"

const val REQUEST_CODE_WRITE_EXT_STORAGE = 12
const val REQUEST_READ_EXTERNAL_STORAGE = 11
const val REQUEST_READ_MEDIA_AUDIO = 13