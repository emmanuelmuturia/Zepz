package penguinpay.penguinpay.commons.uiLayer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PenguinPayHeader(
    headerTitle: String
) {

    Row(modifier = Modifier.fillMaxWidth().padding(top = 43.dp), horizontalArrangement = Arrangement.Center) {
        Text(
            text = headerTitle,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 35.sp,
            color = Color.Black
        )
    }

}
