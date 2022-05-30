package com.atahar.githubapi.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.atahar.githubapi.R
import com.google.android.material.snackbar.Snackbar

object HelperFunctions {

    fun showErrorMessage(view: View, msg: String) {
        val snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
        val snackBarView: View = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(view.context, R.color.snackbar_bg))
        snackBar.show()
    }


}