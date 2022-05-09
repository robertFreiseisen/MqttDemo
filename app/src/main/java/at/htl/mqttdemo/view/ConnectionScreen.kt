package at.htl.mqttdemo.view

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.navigation.compose.composable
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.*
import at.htl.mqttdemo.R
import at.htl.mqttdemo.ui.theme.MqttDemoTheme
import at.htl.mqttdemo.viewmodel.ClientScreen
import at.htl.mqttdemo.viewmodel.MainViewModel

val serverURI =  mutableStateOf("tcp://broker.hivemq.com:1883")
val clientId =  mutableStateOf("")
val username =  mutableStateOf("")
val password =  mutableStateOf("")

@Composable
fun ConnectionScreen(viewModel: MainViewModel = MainViewModel(), navController: NavHostController){
    val context = LocalContext.current

    Scaffold(
        topBar = { MyAppBar(title = "ConnectionScreen", icon = Icons.Default.Home){} }
    ){
        Column(){

            TextField(value = serverURI.value,
                label = {Text(text = stringResource(R.string.server_uri_txt))},
                onValueChange = { serverURI.value = it})
            TextField(value = clientId.value,
                label = { Text(text = stringResource(R.string.client_id)) },
                onValueChange = { clientId.value = it})
            TextField(value = username.value,
                label = { Text(text = stringResource(R.string.user_name)) },
                onValueChange = {username.value = it})
            TextField(value = password.value,
                label = { Text(text = stringResource(R.string.password)) },
                onValueChange = { password.value = it})

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                onClick = {

                    viewModel.mqttClient.connect(context)

                    navController.navigate("client_screen")
                } ){
                Text(text = "Connect")
            }
        }
    }
}

@Composable
fun MainScreen(mainViewModel: MainViewModel = MainViewModel()) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "connection_screen"){

        composable("connection_screen"){
            ConnectionScreen(mainViewModel, navController = navController)
        }

        composable("client_screen"){
            ClientScreen(viewModel = mainViewModel, navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MqttDemoTheme {
        MainScreen()
    }
}