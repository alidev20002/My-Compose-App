package com.example.composeproject.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeproject.ui.viewmodel.TaskViewModel

@Composable
fun TaskList(taskViewModel: TaskViewModel) {
    var textfield by remember {
        mutableStateOf("")
    }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        LazyColumn(modifier = Modifier.fillMaxSize().background(Color(0xCC221212))) {

            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                        .heightIn(min = 70.dp), verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedTextField(
                        singleLine = true,
                        value = textfield,
                        onValueChange = { newText -> textfield = newText },
                        label = {
                            Text(
                                text = "Task Name",
                                color = Color.White
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Enter task name",
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 70.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            textColor = Color.White
                        ),
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Add,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.clickable {
                                    if (textfield.isNotEmpty()) {
                                        taskViewModel.addTask(textfield)
                                        textfield = ""
                                    }
                                }
                            )
                        },
                        textStyle = TextStyle(fontSize = 15.sp)
                    )
                }
            }
            itemsIndexed(taskViewModel.tasks) { index, item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(0.dp, 16.dp, 16.dp, 16.dp),
                    backgroundColor = Color.Gray
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp), verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = item,
                            color = Color.White,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.weight(0.8f)
                        )

                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .weight(0.2f)
                                .clickable {
                                    taskViewModel.removeTask(index)
                                }
                        )
                    }
                }
            }
        }
    }
}