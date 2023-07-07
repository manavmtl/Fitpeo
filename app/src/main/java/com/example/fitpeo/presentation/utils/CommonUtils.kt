package com.example.fitpeo.presentation.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.fitpeo.R

object CommonUtils {
    fun showNoInternetDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.no_internet_connection))
        builder.setMessage(context.getString(R.string.please_check_your_internet_connection_and_try_again))
        builder.setPositiveButton(context.getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.setCancelable(false)
        builder.show()
    }
}