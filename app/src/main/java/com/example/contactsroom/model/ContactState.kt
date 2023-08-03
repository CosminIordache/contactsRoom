package com.example.contactsroom.model

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val name: String = "",
    val last_name: String = "",
    val phone_number: String = "",
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.Name
)
