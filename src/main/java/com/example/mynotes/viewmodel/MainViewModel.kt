package com.example.mynotes.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynotes.db.PhoneBook

class PhoneBookViewModel : ViewModel() {

    // สร้าง LiveData เพื่อเก็บข้อมูล PhoneBook
    private val _phoneBook = MutableLiveData<List<PhoneBook>>()
    val phoneBook: LiveData<List<PhoneBook>> = _phoneBook

    // สร้าง MutableLiveData เพื่อเก็บข้อมูลต่างๆ
    val name = mutableStateOf("")
    val phoneNumber = mutableStateOf("")
    val tags = mutableStateOf<List<String>>(listOf())

    // สร้างฟังก์ชันเพิ่มข้อมูล PhoneBook
    fun addPhoneBook() {
        val phoneBookList = _phoneBook.value?.toMutableList() ?: mutableListOf()
        phoneBookList.add(PhoneBook(name.value, phoneNumber.value, tags.value))
        _phoneBook.value = phoneBookList.toList()
    }

    // สร้างฟังก์ชันลบข้อมูล PhoneBook
    fun deletePhoneBook(phoneBook: PhoneBook) {
        val phoneBookList = _phoneBook.value?.toMutableList() ?: mutableListOf()
        phoneBookList.remove(phoneBook)
        _phoneBook.value = phoneBookList.toList()
    }

    // สร้างฟังก์ชันอัพเดทข้อมูล PhoneBook
    fun updatePhoneBook(phoneBook: PhoneBook) {
        val phoneBookList = _phoneBook.value?.toMutableList() ?: mutableListOf()
        val index = phoneBookList.indexOfFirst { it.phoneNumber == phoneBook.phoneNumber }
        if (index != -1) {
            phoneBookList[index] = phoneBook
            _phoneBook.value = phoneBookList.toList()
        }
    }
}
