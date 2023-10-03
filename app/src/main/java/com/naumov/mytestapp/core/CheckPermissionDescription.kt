package com.naumov.mytestapp.core

import com.naumov.mytestapp.App
import com.naumov.mytestapp.R
import com.naumov.mytestapp.utils.REQUEST_CODE_WRITE_EXT_STORAGE
import com.naumov.mytestapp.utils.REQUEST_READ_EXTERNAL_STORAGE
import com.naumov.mytestapp.utils.REQUEST_READ_MEDIA_AUDIO

class CheckPermissionDescription {

    fun getDescriptionPermission(permissionString: String): PermimissionDescripion {

        when (permissionString) {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                return PermimissionDescripion.WRITE_STORAGE

            }

            android.Manifest.permission.READ_EXTERNAL_STORAGE -> {
                return PermimissionDescripion.READ_STORAGE

            }

            android.Manifest.permission.READ_MEDIA_AUDIO -> {
                return PermimissionDescripion.READ_MEDIA_AUDIO

            }
            else->
                return PermimissionDescripion.EMPTY

        }

    }

    data class DescriptionAlert(
        val title_alert: String,
        val message_alert: String,
        val key_code: Int
    )

    enum class PermimissionDescripion(private val descriptionAlert: DescriptionAlert) {
        WRITE_STORAGE(
            DescriptionAlert(
                title_alert = App.resours.getString(R.string.alert_write_ext_storage_title),
                message_alert = App.resours.getString(R.string.alert_write_ext_storage_request),
                key_code = REQUEST_CODE_WRITE_EXT_STORAGE
            )
        ),

        READ_STORAGE(
            DescriptionAlert(
                title_alert = App.resours.getString(R.string.alert_read_ext_storage_title),
                message_alert = App.resours.getString(R.string.alert_read_ext_storage_request),
                key_code = REQUEST_READ_EXTERNAL_STORAGE
            )
        ),

        READ_MEDIA_AUDIO(
            DescriptionAlert(
                title_alert = App.resours.getString(R.string.alert_read_media_audio_title),
                message_alert = App.resours.getString(R.string.alert_read_media_audio_message),
                key_code = REQUEST_READ_MEDIA_AUDIO
            )
        ),

        EMPTY(
            DescriptionAlert(
                title_alert = "",
                message_alert = "",
                key_code = 0
            )
        );

        fun getTitle(): String = descriptionAlert.title_alert
        fun getMessage(): String = descriptionAlert.message_alert
        fun getKeyCode(): Int = descriptionAlert.key_code
    }
}

