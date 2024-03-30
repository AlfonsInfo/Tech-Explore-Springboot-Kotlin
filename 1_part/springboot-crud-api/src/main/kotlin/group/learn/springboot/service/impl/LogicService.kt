package group.learn.springboot.service.impl

import org.springframework.stereotype.Service

@Service
class LogicService {

    fun oddsOrEvent(number : Int) : String{
        return if(number % 2 == 0){
            "Event"
        }else{
            "Odds"
        }
    }
}