package br.senai.sp.jandira.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                Column(
                    modifier= Modifier
                        .background(Color.Cyan)
                        .fillMaxSize(),
                    verticalArrangement =Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Hello, world!",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Red)
                            .padding(32.dp),

                        color = Color.Blue,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center

                        )
                    Text(
                        text = "Cleiton",
                        modifier= Modifier.fillMaxWidth(),
                        color = Color(133, 9, 0, 255),
                        fontSize = 48.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,

                        ) {
                        for (i in 0 .. 2){
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "Volta $i ",
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                    TextField(
                        value ="", 
                        onValueChange ={},
                        label = {
                            Text(text = "Qual é o seu nome?")
                        }
                    )
                }
            }
        }
    }
}