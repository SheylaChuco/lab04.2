package com.example.lab042

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab042.ui.theme.Lab042Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Block
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab042Theme {
                ComparandoEstadosConBloqueoScreen()
            }
        }
    }
}
@Composable
fun ComparandoEstadosConBloqueoScreen() {
    // Contador que se reinicia en rotación
    var contadorRemember by remember { mutableStateOf(0) }
    // Contador persistente
    var contadorSaveable by rememberSaveable { mutableStateOf(0) }
    // Estado para saber si el contador está bloqueado
    var bloqueado by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título principal
        Text(
            text = "Comparando estados",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF6C4EB6)
        )

        // Botón bloqueo/desbloqueo
        Button(
            onClick = { bloqueado = !bloqueado },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C4EB6))
        ) {
            Text(if (bloqueado) "Desbloquear" else "Bloquear")
        }

        // Mensaje con ícono si está bloqueado
        if (bloqueado) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF3E9F4))
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Block,
                    contentDescription = "Contador bloqueado",
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Contador bloqueado",
                    color = Color.Red,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

        // Contador con remember (ephemeral)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Ephemeral (remember): $contadorRemember",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { if (!bloqueado) contadorRemember++ },
                enabled = !bloqueado,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C4EB6))
            ) {
                Text("+1")
            }
        }

        // Contador con rememberSaveable (persistente)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Persistente (rememberSaveable): $contadorSaveable",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { if (!bloqueado) contadorSaveable++ },
                enabled = !bloqueado,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C4EB6))
            ) {
                Text("+1")
            }
        }

        // Instrucción final
        Text(
            text = "\uD83D\uDEAA Rota la pantalla y observa qué contador se reinicia.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF6C4EB6),
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab042Theme {
        ComparandoEstadosConBloqueoScreen()
    }
}