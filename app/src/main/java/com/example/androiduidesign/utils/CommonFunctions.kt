package com.example.androiduidesign.utils

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast

fun getSpannable(text: String, startIndex: Int, endIndex: Int, color: Int, spanClickCallback: () -> Unit): Spannable {
    val spannable = SpannableString(text)
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(p0: View) {
            spanClickCallback()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
            ds.color = color
        }
    }
    spannable.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannable
}

fun showMessage(context: Context, message: String) {
    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
}