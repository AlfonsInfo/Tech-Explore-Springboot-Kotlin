package group.learn.springboot.exception

class CustomUnauthorizedException : RuntimeException {
    constructor(message: String?) : super(message)
}