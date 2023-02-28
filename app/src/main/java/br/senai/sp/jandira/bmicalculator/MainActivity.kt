package br.senai.sp.jandira.bmicalculator

import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.bmicalculator.calculate.calculate
import br.senai.sp.jandira.bmicalculator.calculate.getBmiClassification
import br.senai.sp.jandira.bmicalculator.model.Client
import br.senai.sp.jandira.bmicalculator.model.Product
import br.senai.sp.jandira.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val p1 = Product()
        p1.id = 100
        p1.productName = "Mouse sem fio"

        val Cl = Client(100, "Pedro", 1500.00)



        setContent {
            BMICalculatorTheme {
                CalculatorScreen()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CalculatorScreen() {


    var weightState by rememberSaveable {
        mutableStateOf("")
    }
    var heightState by rememberSaveable {
        mutableStateOf("")
    }
    var bmiState by rememberSaveable {
        mutableStateOf("0.0")
    }
    var bmiClassificationState by rememberSaveable() {
        mutableStateOf("")
    }
    val context = LocalContext.current.applicationContext




    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
//Header
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier.size(128.dp),
                    painter = painterResource(id = R.drawable.bmi),
                    contentDescription = "",

                    )
                Text(
                    text = stringResource(id = R.string.title),
                    fontSize = 32.sp,
                    color = Color.Blue,
                    letterSpacing = 4.sp,
                    fontWeight = FontWeight.Bold
                )
            }

//Form
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(id = R.string.weight_label),
                    Modifier
                        .padding(top = 40.dp)
                        .padding(start = 24.dp),
                    fontSize = 18.sp
                )
                OutlinedTextField(
                    value = weightState,
                    onValueChange = {
                        Log.i("ds2m", it)
                        weightState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 10.dp),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Text(
                    text = stringResource(id = R.string.height_label),
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 10.dp),
                    fontSize = 18.sp
                )
                OutlinedTextField(
                    value = heightState,
                    onValueChange = {
                        heightState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 10.dp),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

                )
                Spacer(modifier = Modifier.height(48.dp))
                Button(

                    onClick = {
                        var w = weightState.toDouble()
                        var h = heightState.toDouble()
                        var bmi = calculate(weight = w, height = h)

                        bmiState = String.format("%.1f", bmi)
                        bmiClassificationState = getBmiClassification(bmi,context)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    border = BorderStroke(4.dp, Color.Black),
                    shape = RoundedCornerShape(16.dp)
                )
                {
                    Text(
                        text = stringResource(id = R.string.button_calculate),
                        fontSize = 18.sp
                    )
                }

            }
//Footer
            Column() {
                Card(
                    modifier = Modifier
                        .fillMaxSize(),
                    backgroundColor = Color(red = 79, green = 54, blue = 232),
                    shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp),


                    ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.your_score),
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = bmiState,
                            color = Color.White,
                            fontSize = 30.sp
                        )
                        Text(
                            text = bmiClassificationState,
                            color = Color.White,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row() {
                            Button(onClick = {
                                bmiState = "0.0"
                                bmiClassificationState = ""
                                weightState = ""
                                heightState = ""
                            }) {
                                Text(text = stringResource(id = R.string.reset))
                            }
                            Spacer(modifier = Modifier.width(32.dp))
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = stringResource(id = R.string.share))
                            }
                        }

                    }
                }

            }


        }

    }

}