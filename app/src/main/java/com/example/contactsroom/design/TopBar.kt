package com.example.contactsroom.design

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.contactsroom.R
import com.example.contactsroom.data.ContactEvent
import com.example.contactsroom.model.ContactState
import com.example.contactsroom.model.SortType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onEvent: (ContactEvent) -> Unit, state: ContactState) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = "Contacts",
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        },
        actions = {
            IconButton(onClick = { onEvent(ContactEvent.ShowDialog) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Contact"
                )
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_filter_list_24),
                    contentDescription = "Filter"
                )
            }
            DropdownMenu(expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                SortType.values().forEach { sortType ->
                    DropdownMenuItem(onClick = { onEvent(ContactEvent.SortContacts(sortType)) }, sortType.name)
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}