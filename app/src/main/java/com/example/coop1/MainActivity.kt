package com.example.coop1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.coop1.ui.theme.CoOp1Theme
import java.io.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoOp1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //calling the coopActivity() function
                    CoopActivity()
                }
            }
        }
    }
}

//add new values to this class to pass to the receiving activity
data class User(
    val username: String,
    val email: String
): Serializable



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoopActivity() {
    //creating generic values for that will be remebered while the activity is stopped
    var username by rememberSaveable { mutableStateOf("")}
    var email by rememberSaveable { mutableStateOf("")}

    //creating a context for the current composable
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = username,
            onValueChange = { newUserName: String ->
                username = newUserName
            },
            label = {Text("username ")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )


        TextField(
            value = email,
            onValueChange = { newEmail: String ->
                email = newEmail
            },
            label = {Text("email ")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                val message = "Username = $username, email = $email"

                Toast.makeText(
                    context,
                    message,
                    Toast.LENGTH_SHORT
                ).show()

                val user = User(username, email)

                //creating the intent and passing in the user data using context
                val intent = Intent(context, Receiving::class.java)
                intent.putExtra("user", user as Serializable)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Press for splash screen")
        }
    }
}
