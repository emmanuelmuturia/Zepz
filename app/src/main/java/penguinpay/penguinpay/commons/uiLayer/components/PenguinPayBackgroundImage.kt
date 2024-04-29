package penguinpay.penguinpay.commons.uiLayer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import emmanuelmuturia.penguinpay.R

@Composable
fun PenguinPayBackgroundImage() {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background),
        contentDescription = "The Background Image...",
        contentScale = ContentScale.FillBounds
    )

}