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

class BillsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val option = intent.getStringExtra("option") ?: "Billetes"

        setContent {
            BillActivity(
                onBackClick = { finish() },
                onAcceptClick = { billsAmount ->
                    when (option) {
                        "Billetes" -> {
                            val formattedTotal = MoneyCalculator.formatearTotal(billsAmount)
                            val intent = Intent(this, SummaryActivity::class.java).apply {
                                putExtra("totalAmount", formattedTotal)
                                putExtra("source", "Billetes")
                                putExtra("color", 0xFF2D6C5B.toInt())
                            }
                            startActivity(intent)
                            finish()
                        }
                        "Billetes y Monedas" -> {
                            val intent = Intent(this, CoinsActivity::class.java).apply {
                                putExtra("billsAmount", billsAmount)
                                putExtra("option", "Billetes y Monedas")
                            }
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun BillActivity(onBackClick: () -> Unit, onAcceptClick: (Float) -> Unit) {
    val billValues = listOf(5, 10, 20, 50, 100, 200, 500)
    val billNames = listOf("5 euros", "10 euros", "20 euros", "50 euros", "100 euros", "200 euros", "500 euros")

    val quantities = remember { mutableStateListOf(*Array(billValues.size) { "" }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Billetes", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        billValues.forEachIndexed { index, value ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${billNames[index]}:", fontSize = 18.sp)
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
                val totalAmount = MoneyCalculator.calcularBilletes(intQuantities)
                onAcceptClick(totalAmount)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2D6C5B))
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

