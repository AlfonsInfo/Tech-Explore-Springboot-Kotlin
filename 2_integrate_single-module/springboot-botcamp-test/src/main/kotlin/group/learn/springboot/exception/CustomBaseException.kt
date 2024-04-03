package group.learn.springboot.exception

class CustomBaseException : RuntimeException {
    constructor(message: String? = "Server Under Maintenance") : super(message)
}