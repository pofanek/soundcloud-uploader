package com.example.song_manager.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.composables.icons.lucide.House
import com.composables.icons.lucide.Library
import com.composables.icons.lucide.Lucide
import com.composables.ui.components.Icon
import com.composables.ui.components.NavigationBar
import com.composables.ui.components.NavigationBarItem

enum class SelectedItem {
    House,
    Library
}
@Composable
fun Navbar(selectedItem: SelectedItem, setSelectedItem: (SelectedItem) -> Unit) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        NavigationBarItem(
            selected = selectedItem == SelectedItem.House,
            onClick = { setSelectedItem(SelectedItem.House) },
            modifier = Modifier.weight(1f),
        ) {
            Icon(imageVector = Lucide.House)
        }
        NavigationBarItem(
            selected = selectedItem == SelectedItem.Library,
            onClick = {setSelectedItem(SelectedItem.Library)},
            modifier = Modifier.weight(1f)
        ) {
            Icon(imageVector = Lucide.Library)
        }
    }
}