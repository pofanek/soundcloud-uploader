package com.example.song_manager.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.composables.ui.components.Text
import com.composables.ui.components.TextField
import com.composables.ui.theme.ComposablesTheme

enum class PlatformType {
    SOUNDCLOUD,
    YOUTUBE
}
@Composable
fun Textbar(textState: TextFieldState, platform: PlatformType, enabled: Boolean) {
    ComposablesTheme {
        TextField(
            enabled = enabled,
            state = textState,
            modifier = Modifier.fillMaxWidth(),
            accessibilityLabel = platform.toString(),
            placeholder = { Text(if(platform == PlatformType.SOUNDCLOUD) "soundcloud URL" else "youtube URL") }
        )
    }
}