package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.compose.ui.unit.dp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {


    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit  by remember {
        mutableStateOf("centimeters")
    }

    var outputUnit  by remember {
        mutableStateOf("meters")
    }

    var iExpanded  by remember {
        mutableStateOf(false)
    }

    var oExpanded  by remember {
        mutableStateOf(false)
    }
    
    val conversionFactor = remember {
        mutableStateOf(1.00)
    }
    val oConversionFactor = remember {
        mutableStateOf(1.00)
    }

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default ,
        fontSize = 30.sp ,
        color = Color.Black
    )


    fun convertUnits() {
        // ?: elvis operator (if statement) if you return double then use it otherwise ( if null) use 0.0
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0 // It wont crush if we leave it empty
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value ).toFloat() / 100
        outputValue  = result.toString()
    }



    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // UI elements will be stacked below each other

        Text(text = "Unit Converter",
            style = customTextStyle )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue , onValueChange = {
                inputValue = it  // nothing should happen when it changes
                convertUnits()
             },
            label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
        //Here will be stacked next to each other

            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown , contentDescription = "Arrow Down")
                }

                DropdownMenu(expanded = iExpanded , onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "centimeters") }, onClick = {
                        // text becomes input field
                        // expanded menu closes
                        iExpanded = false
                        inputUnit = "centimeters"
                        conversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "meters") }, onClick = {
                        iExpanded = false
                        inputUnit = "meters"
                        conversionFactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "feet") }, onClick = {
                        iExpanded = false
                        inputUnit = "feet"
                        conversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "millimeters") }, onClick = {
                        iExpanded = false
                        inputUnit = "millimeters"
                        conversionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Output box
            Box {
                Button(onClick = { oExpanded = true  }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown , contentDescription = "Arrow Down")
                }

                DropdownMenu(expanded = oExpanded , onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "centimeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "centimeters"
                        oConversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "meters") }, onClick = {
                        oExpanded = false
                        outputUnit = "meters"
                        oConversionFactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "feet") }, onClick = {
                        oExpanded = false
                        outputUnit = "feet"
                        oConversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "millimeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "millimeters"
                        oConversionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
            
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Result text
        Text(text = "Result: $outputValue",
            style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
