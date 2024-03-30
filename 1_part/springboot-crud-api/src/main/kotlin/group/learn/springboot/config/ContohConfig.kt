package group.learn.springboot.config

import group.learn.springboot.service.impl.LogicService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class ContohConfig (
    private val logicService: LogicService
){

    @Bean
    fun printName(){
        println("Name : Alfonsus Setiawan Jacub")
    }

    @Bean
    fun getOddsOrEvent(){
        val result = logicService.oddsOrEvent(1)
        println("Number result is $result")
    }

}