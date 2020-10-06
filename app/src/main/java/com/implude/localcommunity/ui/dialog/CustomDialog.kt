package com.implude.localcommunity.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.implude.localcommunity.R
import kotlinx.android.synthetic.main.custom_dialog.view.*

fun Context.startDialog(text :String) {

    val view = LayoutInflater.from(this).inflate(R.layout.custom_dialog,null,false)

    view.txt.setText(text)
    val dialog = AlertDialog.Builder(this)
        .setView(view)
        .create()

    dialog.show()

    view.btny.setOnClickListener {
        dialog.dismiss()
    }
}