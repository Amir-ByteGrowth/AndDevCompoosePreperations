package com.example.brushesinjetpackcomopose.ui.screens


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.brushesinjetpackcomopose.ui.theme.Purple80
import com.example.brushesinjetpackcomopose.ui.theme.c1
import com.example.brushesinjetpackcomopose.ui.theme.c2
import com.example.brushesinjetpackcomopose.ui.theme.c3
import com.example.brushesinjetpackcomopose.ui.theme.c4
import com.example.brushesinjetpackcomopose.ui.theme.c5
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random


@Composable
fun GradientWithNoise(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        // Gradient background
        Canvas(modifier = Modifier.fillMaxSize()) {

            // Convert the angle to radians
            val angleInRadians = Math.toRadians(50.0)

            // Calculate the unit vectors for the angle
            val cosAngle = cos(angleInRadians)
            val sinAngle = sin(angleInRadians)

            // Calculate the maximum distance from the center to the corners
            val diagonal = sqrt(size.width * size.width + size.height * size.height)

            // Calculate the start and end points based on the angle
            val startPoint = Offset(
                x = (size.width / 2) - (cosAngle * diagonal / 2).toFloat(),
                y = (size.height / 2) - (sinAngle * diagonal / 2).toFloat()
            )
            val endPoint = Offset(
                x = (size.width / 2) + (cosAngle * diagonal / 2).toFloat(),
                y = (size.height / 2) + (sinAngle * diagonal / 2).toFloat()
            )


            drawRect(
                brush = Brush.linearGradient(
                    colorStops = arrayOf(0.1f to c1, 0.2f to c2, 0.3f to c3, 0.5f to c4,1f to c5),
                    start = startPoint,
                    end = endPoint
                )
            )
        }

        // Noise overlay
        Canvas(modifier = Modifier.fillMaxSize()) {
            val noiseSize = 2f
            val noiseDensity = 0.4f // Increase for more noise

            for (x in 0 until (size.width / noiseSize).toInt()) {
                for (y in 0 until (size.height / noiseSize).toInt()) {
                    if (Random.nextFloat() < noiseDensity) {
                        drawRect(
                            color = Color.Black.copy(alpha = 0.1f),
                            topLeft = Offset(x * noiseSize, y * noiseSize),
                            size = Size(noiseSize, noiseSize),
                            blendMode = BlendMode.ColorBurn
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GradientWithNoisePreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            GradientWithNoise(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}


