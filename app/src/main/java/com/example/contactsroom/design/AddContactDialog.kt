@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.contactsroom.design

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.contactsroom.data.ContactEvent
import com.example.contactsroom.model.ContactState

@Composable
fun AddContactDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    Dialog(onDismissRequest = { onEvent(ContactEvent.HideDialog) }) {
        Surface(
            shadowElevation = 8.dp,
            shape = RoundedCornerShape(16.dp), tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = { onEvent(ContactEvent.HideDialog) }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                        )
                    }

                    Spacer(modifier = Modifier.padding(28.dp))

                    Text(
                        text = "Add Contact",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }

                OutlinedTextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(ContactEvent.SetName(it))
                    },
                    label = { Text("Name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = KeyboardActions.Default.onNext
                    ),
                )

                OutlinedTextField(
                    value = state.last_name,
                    onValueChange = {
                        onEvent(ContactEvent.SetLastName(it))
                    },
                    placeholder = { Text("Last Name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = KeyboardActions.Default.onNext
                    ),
                )

                OutlinedTextField(
                    value = state.phone_number,
                    onValueChange = {
                        onEvent(ContactEvent.SetPhoneNumber(it))
                    },
                    placeholder = { Text("Phone Number") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = KeyboardActions.Default.onDone
                    ),
                )

                Button(
                    onClick = { onEvent(ContactEvent.SaveContact) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, bottom = 16.dp, top = 8.dp)
                        .height(50.dp)
                ) {
                    Text(text = "Save", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }

            }
        }
    }
}