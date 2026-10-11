package com.example.song_manager.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Volume2

private val BorderGray = Color(0xFFBEBDC1)

@Composable
fun Mp3UploadBox(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    hasFile: Boolean = false,
    containerColor: Color = Color(0xFFF1F1F1),
    cornerRadius: Dp = 20.dp,
    onClick: () -> Unit,
) {
    val colors = MaterialTheme.colorScheme
    val shape = RoundedCornerShape(cornerRadius)

    val contentAlpha by animateFloatAsState(if (enabled) 1f else 0.5f, label = "alpha")
    val borderColor by animateColorAsState(
        if (hasFile) BorderGray else BorderGray.copy(alpha = 0f),
        label = "border"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .alpha(contentAlpha)
            .clip(shape)
            .background(containerColor)
            .border(2.dp, borderColor, shape)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 28.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Lucide.Volume2,
                    contentDescription = null,
                    tint = colors.onSurfaceVariant,
                    modifier = Modifier.size(26.dp)
                )
            }

            Text(
                text = buildAnnotatedString {
                    if (enabled) {
                        withStyle(SpanStyle(color = colors.primary, fontWeight = FontWeight.SemiBold)) {
                            append("Click here")
                        }
                        append(" to upload a file.")
                    } else {
                        append("Uploading is disabled")
                    }
                },
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                color = colors.onSurfaceVariant
            )

            Text(
                text = "Allowed format: MP3",
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                color = colors.onSurfaceVariant.copy(alpha = 0.7f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Mp3UploadBoxEmptyPreview() {
    MaterialTheme {
        Mp3UploadBox(modifier = Modifier.padding(16.dp), onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun Mp3UploadBoxDisabledPreview() {
    MaterialTheme {
        Mp3UploadBox(modifier = Modifier.padding(16.dp), enabled = false, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun Mp3UploadBoxWithFilePreview() {
    MaterialTheme {
        Mp3UploadBox(modifier = Modifier.padding(16.dp), hasFile = true, onClick = {})
    }
}