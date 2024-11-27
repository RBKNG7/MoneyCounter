package com.example.cmyb

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

class CoinsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val billsAmount = intent.getFloatExtra("billsAmount", 0f)
        val option = intent.getStringExtra("option") ?: "Monedas"

        setContent {
            CoinActivity(
                onBackClick = { finish() },
                onAcceptClick = { coinsAmount ->
                    if (option == "Billetes y Monedas") {
                        val totalAmount = billsAmount + coinsAmount
                        val formattedTotal = MoneyCalculator.formatearTotal(totalAmount)

                        val intent = Intent(this, SummaryActivity::class.java).apply {
                            putExtra("totalAmount", formattedTotal)
                            putExtra("source", "Billetes y Monedas")
                            putExtra("color", 0xFF000000.toInt())
                        }
                        startActivity(intent)
                        finish()
                    } else {
                        val formattedTotal = MoneyCalculator.formatearTotal(coinsAmount)
                        val intent = Intent(this, SummaryActivity::class.java).apply {
                            putExtra("totalAmount", formattedTotal)
                            putExtra("source", "Monedas")
                            putExtra("color", 0xFFD56F09.toInt())
                        }
                        startActivity(intent)
                        finish()
                    }
                }
            )
        }
    }
}

@Composable
fun CoinActivity(onBackClick: () -> Unit, onAcceptClick: (Float) -> Unit) {
    val coinValues = listOf(1, 2, 5, 10, 20, 50, 100, 200)
    val coinNames = listOf("1 cent", "2 cent", "5 cent", "10 cent", "20 cent", "50 cent", "1 euro", "2 euros")

    val quantities = remember { mutableStateListOf(*Array(coinValues.size) { "" }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Monedas", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        coinValues.forEachIndexed { index, value ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${coinNames[index]}:", fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = quantities[index],
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            quantities[index] = newValue
                        }
                    },
                    modifier = Modifier.width(100.dp)
                )
            }
        }

        Button(
            onClick = {
                val intQuantities = quantities.map { it.toIntOrNull() ?: 0 }
                val totalAmount = MoneyCalculator.calcularMonedas(intQuantities)
                onAcceptClick(totalAmount)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD56F09))
        ) {
            Text(text = "Aceptar", color = Color.White)
        }

        Button(
            onClick = { onBackClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text(text = "Atrás", color = Color.White)
        }
    }
}

