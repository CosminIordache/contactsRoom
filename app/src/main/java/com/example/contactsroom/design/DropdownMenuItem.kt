package com.example.contactsroom.design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DropdownMenuItem(onClick: () -> Unit, name: String) {
    Box(
        Modifier.width(150.dp)
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontSize = 16.sp
            ),
            modifier = Modifier
                .padding(10.dp)
                .clickable(onClick = onClick)
                .fillMaxWidth()
        )
    }
}