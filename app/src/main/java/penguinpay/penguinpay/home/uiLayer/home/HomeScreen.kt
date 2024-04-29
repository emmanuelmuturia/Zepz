package penguinpay.penguinpay.home.uiLayer.home

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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import penguinpay.penguinpay.commons.domainLayer.state.PenguinPayState
import penguinpay.penguinpay.commons.uiLayer.components.ExitConfirmationDialog
import penguinpay.penguinpay.commons.uiLayer.components.PenguinPayBackgroundImage
import penguinpay.penguinpay.commons.uiLayer.components.PenguinPayHeader
import penguinpay.penguinpay.commons.uiLayer.state.ErrorScreen
import penguinpay.penguinpay.commons.uiLayer.state.LoadingScreen

@Composable
fun HomeScreen() {

    val homeScreenViewModel: HomeScreenViewModel = koinViewModel()

    val penguinPayState by homeScreenViewModel.penguinPayState.collectAsStateWithLifecycle()

    val exitDialogState = rememberSaveable { mutableStateOf(value = false) }

    val context = LocalContext.current

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

    when (penguinPayState) {

        is PenguinPayState.Loading -> LoadingScreen()

        is PenguinPayState.Error -> ErrorScreen()

        else -> HomeScreenElements()

    }

}

@Composable
fun HomeScreenElements() {

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
                verticalArrangement = Arrangement.spacedBy(space = 7.dp)
            ) {

                item { NameTextField() }

                item { NameTextField() }

                item { PhoneNumberTextField() }

                item { CountryList() }

                item { AmountToSendTextField() }

                item { AmountToBeReceived() }

                item { SendButton() }

            }

        }

    }

}

@Composable
fun NameTextField() {

    OutlinedTextField(
        value = "",
        onValueChange = { it },
        label = { Text(text = "") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Sentences
        ),
        singleLine = true,
        shape = RoundedCornerShape(size = 21.dp)
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryList() {

    val countries = arrayOf("Kenya", "Nigeria", "Tanzania", "Uganda")

    var isExpanded by remember { mutableStateOf(value = false) }

    var selectedCountry by remember { mutableStateOf(value = countries[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 28.dp)
    ) {

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }) {

            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .border(border = BorderStroke(width = 3.dp, color = Color.Black)),
                readOnly = true,
                value = "",
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = isExpanded
                    )
                },
                textStyle = MaterialTheme.typography.bodyLarge
            )

            ExposedDropdownMenu(modifier = Modifier
                .background(color = Color.White)
                .border(border = BorderStroke(width = 3.dp, color = Color.White)),
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {

                countries.forEach { country ->

                    DropdownMenuItem(text = {
                        Text(
                            text = country,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                    }, onClick = {
                        selectedCountry = country
                        isExpanded = false
                    })

                }

            }

        }

    }

}

@Composable
fun PhoneNumberTextField() {

    OutlinedTextField(
        value = "",
        onValueChange = { it },
        label = { Text(text = "") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
        shape = RoundedCornerShape(size = 21.dp)
    )

}

@Composable
fun AmountToSendTextField() {

    OutlinedTextField(
        value = "",
        onValueChange = { it },
        label = { Text(text = "") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
        shape = RoundedCornerShape(size = 21.dp)
    )

}

@Composable
fun AmountToBeReceived() {
    Text(text = "", style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun SendButton() {

    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(size = 21.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) { Text(text = "Send", style = MaterialTheme.typography.labelLarge, color = Color.White) }

}