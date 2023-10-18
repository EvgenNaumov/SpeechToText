package com.naumov.mytestapp.utils

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.naumov.mytestapp.R
import com.naumov.mytestapp.model.SpeechData


fun getAlertDialog(context: Context, title: String?, message: String?): AlertDialog {
    val builder = AlertDialog.Builder(context)
    var finalTitle: String? = context.getString(R.string.alertdialog_title_stub)
    if (!title.isNullOrBlank()) {
        finalTitle = title
    }
    builder.setTitle(finalTitle)
    if (!message.isNullOrBlank()) {
        builder.setMessage(message)
    }
    builder.setCancelable(true)
    builder.setPositiveButton(R.string.dialog_button_cancel) { dialog, _ -> dialog.dismiss() }
    return builder.create()
}

fun convertDTOtoData(dataDTO:SpeechData):SpeechData{

    val speechData = SpeechData(result = dataDTO._result)
    return speechData
}

fun logDebug(title:String,message: String?){
    if (DEBUG_ON) {
        Log.d(TAG, title.plus(": $message"))
    }
}