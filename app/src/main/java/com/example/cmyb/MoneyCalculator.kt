package com.example.cmyb

import kotlin.math.floor

class MoneyCalculator {

    companion object {
        fun calcularMonedas(monedas: List<Int>): Float {
            val valor = listOf(0.01f, 0.02f, 0.05f, 0.10f, 0.20f, 0.50f, 1.00f, 2.00f)
            return totalEuros(monedas, valor)
        }

        fun calcularBilletes(billetes: List<Int>): Float {
            val valor = listOf(5f, 10f, 20f, 50f, 100f, 200f, 500f)
            return totalEuros(billetes, valor)
        }

        private fun totalEuros(cantidades: List<Int>, valores: List<Float>): Float {
            return cantidades.zip(valores) { cantidad, valor -> cantidad * valor }.sum()
        }

        fun formatearTotal(total: Float): String {
            return if (total == floor(total)) {
                String.format("%.0f", total)
            } else {
                String.format("%.2f", total)
            }
        }
    }
}

