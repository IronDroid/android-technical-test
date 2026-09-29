package org.ajcm.bnbtest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.ajcm.bnbtest.ui.screens.InformationScreen
import org.ajcm.bnbtest.ui.theme.BNBTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BNBTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF00A859)
                ) {
                    InformationScreen(
                        onBackClick = {
                            Toast.makeText(this, "Atrás presionado", Toast.LENGTH_SHORT).show()
                        },
                        onNextClick = { state ->
                            Toast.makeText(
                                this,
                                "Siguiente: Celular=${state.phoneNumber}, Carnet=${state.idNumber}, Comp=${state.complement}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun MainActivityPreview() {
    BNBTestTheme {
        InformationScreen()
    }
}
