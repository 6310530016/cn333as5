package com.example.mynotes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mynotes.db.PhoneBook
import com.example.mynotes.viewmodel.PhoneBookViewModel

@ExperimentalMaterialApi
@Composable
fun PhoneBookItem(
    phoneBook: PhoneBook,
    viewModel: PhoneBookViewModel
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = phoneBook.name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = phoneBook.phoneNumber,
                    style = MaterialTheme.typography.caption
                )
            }
            IconButton(
                onClick = { viewModel.editPhoneBook(phoneBook) }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit PhoneBook"
                )
            }
            IconButton(
                onClick = { viewModel.deletePhoneBook(phoneBook.id) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete PhoneBook"
                )
            }
        }
    }
}
