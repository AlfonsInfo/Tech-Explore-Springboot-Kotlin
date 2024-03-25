package group.learn.springboot.data

object Constants {

    object NativeQuery{
        val GET_BRAND_CD_DESC = "get_brand_cd_desc"
    }

    object Endpoint{
        const val V1 = "api/v1" // only const val can be used on
        val CAR_BRAND = "$V1/car-brand"
    }

    object ParamsKey{
        val API_KEY ="APIKey"
    }

    object ResponseMessaging{
        //Code
        val SUCCESS_FLAG = "T"
        val FAILED_FLAG = "F"

        //Message
        val DEFAULT_SUCCESS_RESPONSE = "Success"
        val DEFAULT_BAD_RESPONSE = "Invalid Input"
        val UNAUTHORIZED_BASE_RESPONSE = "You do not have permissions to access the API!"
    }
}