package com.example.cmyb

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class BillsAndCoinsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicia BillsActivity y le indica que debe continuar con CoinsActivity después
        startBillsActivity()
    }

    private fun startBillsActivity() {
        val intent = Intent(this, BillsActivity::class.java).apply {
            putExtra("option", "Billetes y Monedas")
        }
        startActivity(intent)
        finish() // Finaliza BillsAndCoinsActivity ya que su único propósito es redirigir.
    }
}

