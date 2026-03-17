package ci.nsu.moble.main.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import androidx.compose.ui.platform.LocalContext
import ci.nsu.moble.main.R
import ci.nsu.moble.main.ui.theme.PracticeTheme
import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.size(400.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image1),
                contentDescription = "Локальное изображение",
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}

@Composable
fun LoadImageFromUrl(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }
            is AsyncImagePainter.State.Error -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Error loading image",
                    modifier = Modifier.size(100.dp)
                )
            }
            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = painter,
                    contentDescription = "Image from internet",
                    modifier = Modifier.fillMaxSize()
                )
            }
            else -> {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PracticeTheme {
        HomeScreen()
    }
}