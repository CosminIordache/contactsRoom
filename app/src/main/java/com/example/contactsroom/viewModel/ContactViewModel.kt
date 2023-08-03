package com.example.contactsroom.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsroom.data.ContactDataAccessObject
import com.example.contactsroom.data.ContactEvent
import com.example.contactsroom.model.Contact
import com.example.contactsroom.model.ContactState
import com.example.contactsroom.model.SortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ContactViewModel(
    private val dao: ContactDataAccessObject
) : ViewModel() {

    private val _sortType = MutableStateFlow(SortType.Name)
    private val _state = MutableStateFlow(ContactState())

    private val _contacts = _sortType
        .flatMapLatest { sortType ->
        when(sortType){
            SortType.Name -> dao.getContactOrderByName()
            SortType.`Last name` -> dao.getContactOrderByLastName()
            SortType.`Phone number` -> dao.getContactOrderByPhoneNumber()
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        state.copy(
            contacts = contacts,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun onEvent( event: ContactEvent ){
        when(event){
            is ContactEvent.DeleteContact -> {
                viewModelScope.launch {
                    dao.deleteContact(event.contact)
                }
            }

            ContactEvent.SaveContact -> {
                val name = state.value.name
                val last_name = state.value.last_name
                val phone_number = state.value.phone_number

                if (name.isBlank() || last_name.isBlank() || phone_number.isBlank()){
                    return
                }

                val contact = Contact(
                    name = name,
                    last_name = last_name,
                    phone_number = phone_number
                )
                viewModelScope.launch {
                    dao.upsertContact(contact)
                }
                _state.update { it.copy(
                    isAddingContact = false,
                    name = "",
                    last_name = "",
                    phone_number = ""
                )}
            }

            is ContactEvent.SetLastName -> {
                _state.update { it.copy(last_name = event.last_name) }
            }

            is ContactEvent.SetName -> {
                _state.update { it.copy(name = event.name) }
            }

            is ContactEvent.SetPhoneNumber -> {
                _state.update { it.copy(phone_number = event.phone_number) }
            }

            is ContactEvent.SortContacts -> {
                _sortType.value = event.sortType
            }

            ContactEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingContact = false
                ) }
            }

            ContactEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingContact = true
                ) }
            }

        }
    }
}