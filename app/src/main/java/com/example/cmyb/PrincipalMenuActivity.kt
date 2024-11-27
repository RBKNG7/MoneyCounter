package com.example.cmyb

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.* // Importar todas las funciones de layout
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class PrincipalMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrincipalMenuScreen(
                navigateTo = { destination ->
                    when (destination) {
                        "Billetes" -> startActivity(Intent(this, BillsActivity::class.java))
                        "Monedas" -> startActivity(Intent(this, CoinsActivity::class.java))
                        "Billetes y monedas" -> startActivity(Intent(this, BillsAndCoinsActivity::class.java))
                    }
                }
            )
        }
    }
}

@Composable
fun PrincipalMenuScreen(navigateTo: (String) -> Unit) {
    // Color de fondo
    val backgroundColor = Color(0xFFF3F7F6)

    // Configuramos la pantalla completa
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(60.dp), // Añade un margen alrededor de los elementos
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Primera sección: Billetes
        SectionWithImageAndText(
            imageResource = R.drawable.billetes,
            text = "Billetes",
            textColor = Color(0xFF2D6C5B),
            onClick = { navigateTo("Billetes") }
        )

        // Segunda sección: Monedas
        SectionWithImageAndText(
            imageResource = R.drawable.monedas,
            text = "Monedas",
            textColor = Color(0xFFD56F09),
            onClick = { navigateTo("Monedas") }
        )

        // Tercera sección: Billetes y monedas
        SectionWithImageAndText(
            imageResource = R.drawable.myb,
            text = "Billetes y monedas",
            textColor = Color.Black,
            onClick = { navigateTo("Billetes y monedas") }
        )
    }
}

@Composable
fun SectionWithImageAndText(imageResource: Int, text: String, textColor: Color, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null, // No es necesario una descripción en este caso
            modifier = Modifier.size(180.dp) // Ajusta el tamaño de la imagen
        )
        Spacer(modifier = Modifier.height(0.dp)) // Espacio entre la imagen y el texto
        Text(
            text = text,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}