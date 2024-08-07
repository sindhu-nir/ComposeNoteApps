package com.elahee.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elahee.noteapp.R
import com.elahee.noteapp.components.NoteButton
import com.elahee.noteapp.components.NoteInputText
import com.elahee.noteapp.data.NotesDataSource
import com.elahee.noteapp.model.Note
import com.elahee.noteapp.util.fromDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context= LocalContext.current
    Column(modifier = Modifier.padding(bottom = 6.dp)) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Notification")
            },
            colors = TopAppBarDefaults.topAppBarColors(Color(0xFFDADFE3))

        )
        //Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                }
            )
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                }
            )

            NoteButton(
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(
                            Note(title=title,
                            description = description)
                        )
                        title = ""
                        description = ""
                        Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
                    }
                },
                enable = true
            )
        }

        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes){note->
                NoteRow(
                    note = note,
                    onNoteClick = {
                        onRemoveNote(it)
                    }
                )
            }
        }

    }
}

@Composable
fun NoteRow(
    modifier: Modifier=Modifier,
    note:Note,
    onNoteClick: (Note)-> Unit
){
    Surface(modifier = Modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp
    ) {
        Column(
            modifier
                .clickable {
                    onNoteClick(note)
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = note.title, style = MaterialTheme.typography.titleSmall)
            Text(text = note.description, style = MaterialTheme.typography.labelSmall)
            Text(text = fromDate(note.entryDate.time), style = MaterialTheme.typography.labelSmall)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(
        notes = NotesDataSource().loadNotes(),
        onAddNote = {},
        onRemoveNote = {}
    )
}