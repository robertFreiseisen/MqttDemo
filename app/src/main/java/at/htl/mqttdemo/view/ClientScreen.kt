package at.htl.mqttdemo.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import at.htl.mqttdemo.R
import at.htl.mqttdemo.view.*

var topicPublish = mutableStateOf("Topic")
var msg = mutableStateOf("Message")
var topic = mutableStateOf("Topic")
@Composable
fun ClientScreen(viewModel: MainViewModel = MainViewModel(), navController: NavHostController){

    Scaffold(
        topBar = { MyAppBar(title = "ClientScreen", icon = Icons.Default.ArrowBack){} }
    ){
        Column() {
            TextField(value = topicPublish.value,
                label = { Text(text = stringResource(R.string.topic_txt)) },
                onValueChange = { topicPublish.value = it})
            TextField(value = msg.value,
                label = { Text(text = stringResource(R.string.msg_txt)) },
                onValueChange = { msg.value = it})

            Button(onClick = {viewModel.mqttClient.publish(topic = topic.value, msg = msg.value)}) {
                Text(text = "PUBLISH")
            }

            TextField(value = topic.value,
                label = { Text(text = stringResource(R.string.topic_txt)) },
                onValueChange = { topic.value = it})

            Row() {
                Button(onClick = {viewModel.mqttClient.subscribe(topic = topic.value)}) {
                    Text(text = "SUBSCRIBE")
                }
                Button(onClick = {viewModel.mqttClient.unsubscribe(topic = topic.value)}) {
                    Text(text = "UNSUBSCRIBE")
                }
            }

            Button(onClick = {
                viewModel.mqttClient.disconnect()
                navController.navigate("connection_screen")
            }) {
                Text(text = "DISCONNECT")
            }
        }
    }


}