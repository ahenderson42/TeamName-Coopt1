package com.example.coop1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    val receivedUser = intent.getSerializableExtra("user") as? User
                    if(receivedUser != null){
                        val username = receivedUser.username
                        val email = receivedUser.email

                        Greeting2(username, email)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting2(userName: String, email: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $userName!, with this email $email",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ReceivedPreview(){
    val receivedUsername = "Testing Name"
    val receivedEmail = "email"
    Greeting2(receivedUsername, receivedEmail)
}