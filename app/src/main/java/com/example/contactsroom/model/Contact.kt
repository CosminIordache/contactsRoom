package com.example.contactsroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val name: String,
    val last_name: String,
    val phone_number: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
