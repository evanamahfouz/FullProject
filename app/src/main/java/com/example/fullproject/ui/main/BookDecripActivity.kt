package com.example.fullproject.ui.main

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fullproject.R
import kotlinx.android.synthetic.main.activity_book_decrip.*

class BookDecripActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_decrip)

        intent.getStringExtra(ARG_DESC)?.let {
            text_desc?.text = it
        } ?: let {
            text_desc?.text = getString(R.string.no_desc)
            text_desc?.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
            text_desc?.setPadding(32, 0, 0, 0)
        }
    }

    companion object {
        const val ARG_DESC = "Description"
    }
}
