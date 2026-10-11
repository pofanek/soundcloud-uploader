package com.example.song_manager

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.delete
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.song_manager.components.Mp3UploadBox
import com.example.song_manager.components.PlatformType
import com.example.song_manager.components.Textbar

@Composable
fun Home() {
    val textStateSoundcloud = rememberTextFieldState()
    val textStateYoutube = rememberTextFieldState()

    val (mp3Uri, setMp3Uri) = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        setMp3Uri(it)
    }

    val (soundcloudEnabled, setSoundcloudEnabled) = remember { mutableStateOf(true) }
    val (youtubeEnabled, setYoutubeEnabled) = remember { mutableStateOf(true) }
    val (fileEnabled, setFileEnabled) = remember { mutableStateOf(true) }

    LaunchedEffect(textStateSoundcloud.text, textStateYoutube.text) {
        if(textStateSoundcloud.text.isNotEmpty()) {
            setYoutubeEnabled(false)
            setFileEnabled(false)
            setSoundcloudEnabled(true)
        } else if(textStateYoutube.text.isNotEmpty()){
            setYoutubeEnabled(true)
            setSoundcloudEnabled(false)
            setFileEnabled(false)
        } else if(textStateYoutube.text.isEmpty() && textStateSoundcloud.text.isEmpty() && (mp3Uri == Uri.EMPTY || mp3Uri == null)) {
            setYoutubeEnabled(true)
            setSoundcloudEnabled(true)
            setFileEnabled(true)
        }
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Textbar(textState = textStateSoundcloud, platform = PlatformType.SOUNDCLOUD, soundcloudEnabled)
        Textbar(textState = textStateYoutube, platform = PlatformType.YOUTUBE, youtubeEnabled)
        Mp3UploadBox(hasFile = false, enabled = fileEnabled, onClick = {
            launcher.launch("audio/mpeg")
        })
//        nastepnie zrob media playera :) https://developer.android.com/media/media3/exoplayer/hello-world#add-exoplayer chyba to
    }
}