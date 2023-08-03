package com.example.contactsroom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.contactsroom.model.Contact
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactDataAccessObject {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getContactOrderByName(): Flow<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY last_name ASC")
    fun getContactOrderByLastName(): Flow<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY phone_number ASC")
    fun getContactOrderByPhoneNumber(): Flow<List<Contact>>


}