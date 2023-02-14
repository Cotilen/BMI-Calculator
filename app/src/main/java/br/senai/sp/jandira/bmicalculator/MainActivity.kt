package br.senai.sp.jandira.bmicalculator

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.bmicalculator.calculate.calculator
import br.senai.sp.jandira.bmicalculator.model.Client
import br.senai.sp.jandira.bmicalculator.model.Product
import br.senai.sp.jandira.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val p1 = Product()
//        p1.id = 100
//        p1.productName = "Mouse sem fio"
//
//        val c1 = Client(
//            100,
//            "Pedro")
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

    var weightState = rememberSaveable {
        mutableStateOf("")
    }
    var heightState = rememberSaveable{
        mutableStateOf("")
    }
    var bmiState = rememberSaveable{
        mutableStateOf(0.0)
    }



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
                    value = weightState.value,
                    onValueChange = {
                        Log.i("ds2m",it)
                        weightState.value = it
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
                    value = heightState.value,
                    onValueChange = {Log.i("ds2ma",it)
                                    heightState.value = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 10.dp),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)


                )
                Spacer(modifier = Modifier.height(48.dp))
                Button(
                    onClick = {bmiState.value = calculator()(weight = weightState.value.toDouble(),
                    height = heightState.value.toDouble()).toString()},
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
                    shape = RoundedCornerShape(topStart = (25.dp), topEnd = (25.dp)),
                    backgroundColor = Color(79, 54, 232)

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
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = bmiState.value,
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = stringResource(id = R.string.ideal_state),
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Row() {
                            Button(onClick = { /*TODO*/ }) {
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
