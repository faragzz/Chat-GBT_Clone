package com.example.myapplication.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.pages.chat.Message
import com.example.myapplication.ui.theme.appBar
import com.example.myapplication.ui.theme.appBody
import com.example.myapplication.ui.theme.darkGrey
import com.example.myapplication.ui.theme.greenbackground
import com.example.myapplication.ui.theme.unfocusedBorder
import com.example.myapplication.ui.theme.white

@Preview
@Composable
fun HomeScreen() {
    val menu = painterResource(id = R.drawable.menu)
    val items = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(appBar)
        ) {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.Start) {
                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Icon(menu, "menu icon", modifier = Modifier.size(40.dp), tint = white)
                    Spacer(modifier = Modifier.padding(start = 90.dp))
                    TextHeader(text = "Chat Bot")
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(appBody)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items) { item ->
                    Message(text = item, isSender = true)
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
                .background(darkGrey)
        ) {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                InputField(
                    hint = stringResource(id = R.string.txtFieldHint),
                    addItem = { item ->
                        items.add(item)
                    }
                )
            }
        }
    }
}

@Composable
fun TextHeader(text: String) {
    Text(
        text = text,
        color = white,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(hint: String, addItem: (String) -> Unit) {
    val sendIcon = painterResource(R.drawable.send)
    var values by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.padding(15.dp),
        shadowElevation = 15.dp,
        shape = RoundedCornerShape(8.dp),
        color = darkGrey
    ) {
        OutlinedTextField(
            value = values,
            onValueChange = { values = it },
            label = { Text(text = hint, color = white) },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
            shape = RoundedCornerShape(7.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = white,
                unfocusedBorderColor = unfocusedBorder
            ),
            trailingIcon = {
                Box(
                    modifier = Modifier.background(greenbackground, RoundedCornerShape(8.dp))
                ) {
                    IconButton(onClick = {
                        if (values.isNotBlank()) {
                            addItem(values)
                            values = ""
                        }
                    }) {
                        Icon(
                            sendIcon,
                            contentDescription = "Send Icon",
                            modifier = Modifier
                                .padding(6.dp)
                                .background(greenbackground)
                        )
                    }
                }
            },
        )
    }
}
