package com.example.contactsroom.data

import com.example.contactsroom.model.Contact
import com.example.contactsroom.model.SortType

sealed interface ContactEvent {

    object SaveContact: ContactEvent
    object ShowDialog: ContactEvent
    object HideDialog: ContactEvent

    data class SetName(val name: String) : ContactEvent
    data class SetLastName(val last_name: String) : ContactEvent
    data class SetPhoneNumber(val phone_number: String) : ContactEvent
    data class SortContacts(val sortType: SortType): ContactEvent
    data class DeleteContact(val contact: Contact): ContactEvent

}