package com.example.contactsroom.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactsroom.data.ContactDataAccessObject
import com.example.contactsroom.model.Contact

@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase: RoomDatabase() {
    abstract val dao: ContactDataAccessObject
}