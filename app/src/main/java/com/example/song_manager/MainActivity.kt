package com.example.song_manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composables.ui.theme.ComposablesTheme
import com.example.song_manager.components.Header
import com.example.song_manager.components.Navbar
import com.example.song_manager.components.SelectedItem
import com.example.song_manager.ui.theme.TestMobileAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileAppContent()
        }
    }
}

@Composable
fun MobileAppContent() {
    val (selectedItem, setSelectedItem) = remember { mutableStateOf(SelectedItem.House) }
    TestMobileAppTheme {
        ComposablesTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = { Navbar(selectedItem, setSelectedItem) },
                topBar = { Header() }
            ) { innerPadding ->
                Box(
                    modifier = Modifier.padding(innerPadding).fillMaxSize()
                ) {
                    when(selectedItem) {
                        SelectedItem.Library -> Library()
                        SelectedItem.House -> Home()
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun PreviewAll() {
    MobileAppContent()
}