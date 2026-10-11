package com.example.song_manager.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.ui.components.Text
@Composable
fun Header(modifier: Modifier = Modifier) {
    Surface {
        Box(
            modifier = modifier.fillMaxWidth().padding(top = 12.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Soundcloud Uploader",
                fontSize = 45.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 36.dp)
            )
        }
    }
}