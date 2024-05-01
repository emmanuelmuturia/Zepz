package penguinpay.penguinpay.home.uiLayer.home

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import penguinpay.penguinpay.commons.uiLayer.components.ExitConfirmationDialog
import penguinpay.penguinpay.commons.uiLayer.components.PenguinPayBackgroundImage
import penguinpay.penguinpay.commons.uiLayer.components.PenguinPayHeader
import penguinpay.penguinpay.commons.uiLayer.theme.Caveat

@Composable
fun HomeScreen() {

    val homeScreenViewModel: HomeScreenViewModel = koinViewModel()

    val penguinPayState by homeScreenViewModel.penguinPayState.collectAsStateWithLifecycle()

    val exitDialogState = rememberSaveable { mutableStateOf(value = false) }

    val context = LocalContext.current

    val countries = mapOf(
        254 to "Kenya",
        234 to "Nigeria",
        255 to "Tanzania",
        256 to "Uganda"
    )

    var selectedCountry by remember { mutableStateOf(value = countries[254]) }

    BackHandler(enabled = true) { exitDialogState.value = !exitDialogState.value }

    if (exitDialogState.value) {
        ExitConfirmationDialog(
            onConfirmExit = {
                (context as? ComponentActivity)?.finish()
            },
            onDismiss = {
                exitDialogState.value = false
            }
        )
    }

    HomeScreenElements(homeScreenViewModel = homeScreenViewModel, countries = countries, selectedCountry = selectedCountry!!, onCountrySelect = { selectedCountry = it })

}

@Composable
fun HomeScreenElements(homeScreenViewModel: HomeScreenViewModel, countries: Map<Int, String>, selectedCountry: String, onCountrySelect: (String) -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {

        PenguinPayBackgroundImage()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PenguinPayHeader(headerTitle = "PenguinPay")

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(space = 7.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item { NameTextField(label = "First Name...") }

                item { NameTextField(label = "Last Name...") }

                item { CountryList(countries = countries, selectedCountry = selectedCountry, onCountrySelect = onCountrySelect) }

                item { SendableAndReceivableAmounts(homeScreenViewModel = homeScreenViewModel, selectedCountry = selectedCountry) }

                item { SendButton() }

            }

        }

    }

}

@Composable
fun NameTextField(label: String) {

    var name by rememberSaveable { mutableStateOf(value = "") }

    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text(text = label, style = MaterialTheme.typography.labelLarge) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Sentences
        ),
        singleLine = true,
        shape = RoundedCornerShape(size = 21.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryList(countries: Map<Int, String>, selectedCountry: String, onCountrySelect: (String) -> Unit) {

    var isExpanded by remember { mutableStateOf(value = false) }

    var phoneNumber by rememberSaveable { mutableStateOf(value = "") }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Box {

            ExposedDropdownMenuBox(

                expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded }) {

                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor()
                        .border(border = BorderStroke(width = 2.1.dp, color = Color.Black)),
                    readOnly = true,
                    value = selectedCountry!!,
                    onValueChange = { },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = isExpanded
                        )
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = Caveat,
                        fontSize = 25.sp
                    )
                )

                ExposedDropdownMenu(modifier = Modifier
                    .background(color = Color.White)
                    .border(border = BorderStroke(width = 1.dp, color = Color.White)),
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }) {

                    countries.forEach { country ->

                        DropdownMenuItem(text = {
                            Text(
                                text = country.value,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                        }, onClick = {
                            phoneNumber = "+${country.key} "
                            onCountrySelect(country.value)
                            isExpanded = false
                        })

                    }

                }

            }

        }

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text(text = "Phone Number...", style = MaterialTheme.typography.labelLarge) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            shape = RoundedCornerShape(size = 21.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )

    }

}

@Composable
fun SendableAndReceivableAmounts(homeScreenViewModel: HomeScreenViewModel, selectedCountry: String) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 7.dp)
    ) {

        var amountToSend by rememberSaveable { mutableStateOf(value = "") }

        val amountToReceive = homeScreenViewModel.getReceivableAmount(sendableAmount = amountToSend, selectedCountry = selectedCountry) ?: ""

        OutlinedTextField(
            value = amountToSend,
            onValueChange = { amountToSend = it },
            label = { Text(text = "Amount To Send...", style = MaterialTheme.typography.labelLarge) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            shape = RoundedCornerShape(size = 21.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )

        Text(
            text = "Amount Receivable: $amountToReceive",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            overflow = TextOverflow.Visible
        )

    }

}

@Composable
fun SendButton() {

    val context = LocalContext.current

    Button(
        onClick = {
            Toast.makeText(context, "Your money was successfully sent...", Toast.LENGTH_LONG).show()
        },
        shape = RoundedCornerShape(size = 21.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) { Text(text = "Send", style = MaterialTheme.typography.labelLarge, color = Color.White) }

}