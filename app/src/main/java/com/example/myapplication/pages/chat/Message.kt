package com.example.myapplication.pages.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun Message(text: String, isSender: Boolean) {
    val senderIcon = painterResource(R.drawable.sender_logo)
    val receiverIcon = painterResource(R.drawable.receiver_logo)

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, start = 10.dp, end = 10.dp),
        horizontalArrangement = if (isSender){ Arrangement.End} else Arrangement.Start
    ) {
        if (isSender)Image(senderIcon,"Sender Icon", modifier = Modifier.size(30.dp))
        else Image(receiverIcon,"Sender Icon", modifier = Modifier.size(30.dp))
        Spacer(modifier = Modifier.padding(5.dp))
        Box(
            modifier = Modifier
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(8.dp),
                color = Color.Black
            )
        }
    }
}