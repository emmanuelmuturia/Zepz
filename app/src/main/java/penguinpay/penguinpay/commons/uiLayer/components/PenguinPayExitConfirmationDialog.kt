package penguinpay.penguinpay.commons.uiLayer.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import emmanuelmuturia.penguinpay.R

@Composable
fun ExitConfirmationDialog(
    onConfirmExit: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 21.dp))
            .border(width = 3.dp, color = Color.White),
        onDismissRequest = { onDismiss() },
        tonalElevation = 21.dp,
        text = {
            Text(
                text = stringResource(R.string.are_you_sure_you_want_to_exit_the_app),
                style = MaterialTheme.typography.titleLarge,
                lineHeight = 30.sp
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmExit()
                    onDismiss()
                },
                shape = RoundedCornerShape(size = 21.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.yes),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                shape = RoundedCornerShape(size = 21.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.no),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        },
        containerColor = Color.White,
        textContentColor = Color.Black,
        titleContentColor = Color.Black,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    )
}