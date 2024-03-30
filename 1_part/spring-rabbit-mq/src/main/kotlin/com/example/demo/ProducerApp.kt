package com.example.demo

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery

fun main() {
    //* creating a connection
    val factory =  ConnectionFactory()
    factory.setUri("amqp://guest:guest@localhost:5672/")
    factory.virtualHost  = "/"
    val connection : Connection = factory.newConnection()
    val channel = connection.createChannel()

    for(i in 1..10){
        val message =  "message whatsapp $i"

        val properties : AMQP.BasicProperties = AMQP
            .BasicProperties()
            .builder()
            .headers(mapOf("sample" to "values"))
            .build()

        channel.basicPublish("notification", "whatsapp", properties, message.toByteArray())
    }

    //channel.close()
    //connection.close()

    //closing connection
}