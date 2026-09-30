package com.example.treu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                TriangleCalculatorScreen()
            }
        }
    }
}

@Composable
fun TriangleCalculatorScreen() {
    var letterInput by remember { mutableStateOf("") }
    var valueInput by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("Результат появится здесь") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Калькулятор треугольника", fontSize = 18.sp, style = MaterialTheme.typography.titleMedium)

        OutlinedTextField(
            value = letterInput,
            onValueChange = { letterInput = it },
            label = { Text("Буква элемента (k, g, p)") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )

        OutlinedTextField(
            value = valueInput,
            onValueChange = { valueInput = it },
            label = { Text("Значение элемента") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )

        Button(
            onClick = {
                val letter = letterInput.trim().lowercase()
                val value = valueInput.toDoubleOrNull() ?: 0.0

                var a = 0.0
                var c = 0.0
                var s = 0.0

                // Добавили оператор when и формулы
                when (letter) {
                    "k" -> { a = value; c = a * sqrt(2.0); s = (a * a) / 2.0 }
                    "g" -> { c = value; a = c / sqrt(2.0); s = (c * c) / 4.0 }
                    "p" -> { s = value; a = sqrt(2.0 * s); c = 2.0 * sqrt(s) }
                }

                resultText = "Катет: $a, Гипотенуза: $c, Площадь: $s"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Рассчитать")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = resultText, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
    }
}