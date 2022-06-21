package com.ahnsh1996.keepit.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ahnsh1996.keepit.R
import com.ahnsh1996.keepit.ui.common.ViewModelFactory
import com.ahnsh1996.keepit.viewmodel.KeepViewModel

class KeepActivity : AppCompatActivity() {

    private val viewModel: KeepViewModel by viewModels() { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keep)

        val textViewSelectText = findViewById<TextView>(R.id.textview_select_text)
        val selectText = intent.getStringExtra(Intent.EXTRA_PROCESS_TEXT) ?: ""

        if (selectText.isNotBlank()) {
            Toast.makeText(this, "Keep it! : ${selectText}", Toast.LENGTH_LONG).show()
            viewModel.addKeepData(selectText, textViewSelectText) { finish() }
        }
    }
}