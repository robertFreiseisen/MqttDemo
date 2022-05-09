package at.htl.mqttdemo.viewmodel

import androidx.lifecycle.ViewModel
import at.htl.mqttdemo.services.MQTTClient

class MainViewModel : ViewModel() {

    var mqttClient: MQTTClient = MQTTClient()
}