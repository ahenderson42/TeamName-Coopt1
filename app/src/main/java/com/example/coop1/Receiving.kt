package com.example.coop1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coop1.ui.theme.CoOp1Theme



class Receiving : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoOp1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    @Suppress("DEPRECATION")
                    val receivedUser = intent.getSerializableExtra("user") as? User
                    if(receivedUser != null){
                        val username = receivedUser.username
                        val email = receivedUser.email
                        val checked = receivedUser.checked
                        val selected = receivedUser.selected

                        Greeting2(
                            username,
                            email,
                            checked,
                            selected) {
                            val intent = Intent(this@Receiving, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting2(
    userName: String,
    email: String,
    checked: Boolean,
    selected: Int,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text(
            text = "Hello $userName! Your email is: $email",
            modifier = modifier
        )
        if (checked) {
            Text(text = "You checked the box")
        } else {
            Text(text = "You did *not* check the box")
        }
        Text(
            text = "Option selected: $selected"
        )
        Button(
            onClick = onButtonClick
        ) {
            Text(
                text = "Go Back"
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReceivedPreview(){
    val receivedUsername = "Testing Name"
    val receivedEmail = "email"
    val receivedChecked = true
    val selected = 2
    Greeting2(receivedUsername, receivedEmail, receivedChecked, selected){
        // This is a dummy action, it does nothing
    }
}