package group.learn.springboot.data.schema

import group.learn.springboot.data.Constants
import java.util.*


data class BaseResponse <T> (
    var outStat : String = "",
    var outMes : String = "",
    var  outData : T ,
){

    fun successResponse(data: T, message: String?): BaseResponse<T> {
        var lMessage = message
        if (Objects.isNull(lMessage)) {
            lMessage = Constants.ResponseMessaging.DEFAULT_SUCCESS_RESPONSE
        }
        this.outStat = Constants.ResponseMessaging.SUCCESS_FLAG
        this.outMes = lMessage!!
        this.outData = data
        return this
    }
}