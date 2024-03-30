package com.example.demo

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

    val deliverCallback = DeliverCallback { consumerTag: String, message: Delivery ->
        println(String(message.body))
        println(message.envelope.routingKey)
    }

    val cancelCallBack = CancelCallback{ consumerTag ->
        println("Consumer Cancelled")
    }

    channel.basicConsume("whatsapp",true,deliverCallback,cancelCallBack)


    //channel.close()
    //connection.close()

    //closing connection
}