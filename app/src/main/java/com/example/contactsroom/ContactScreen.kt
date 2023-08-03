package com.example.contactsroom

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactsroom.data.ContactEvent
import com.example.contactsroom.design.AddContactDialog
import com.example.contactsroom.design.TopBar
import com.example.contactsroom.model.ContactState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    Scaffold(
        topBar = { TopBar(onEvent, state) }
    ) { padding ->

        if (state.isAddingContact){
            AddContactDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ){
            items(state.contacts) { contact ->
                Card(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${contact.name} ${contact.last_name}",
                                fontSize = 20.sp
                            )
                            Text(text = contact.phone_number, fontSize = 14.sp)
                        }
                        IconButton(onClick = { onEvent(ContactEvent.DeleteContact(contact)) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Contact"
                            )
                        }
                    }
                }
            }
        }
    }
}



