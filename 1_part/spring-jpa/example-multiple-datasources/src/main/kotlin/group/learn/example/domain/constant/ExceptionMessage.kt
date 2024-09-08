package group.learn.example.domain.constant

class ExceptionMessage {
    companion object{
        //NOT FOUND
        const val DEPARTMENT_NOT_FOUND = "Department with id : %s not found"
        const val REQUEST_ID_CANNOT_NULL = "Request ID Cannot null"

        //DUPLICATE
        const val DEPARTMENT_DUPLICATE = "Department with name : %s already exist"
    }
}