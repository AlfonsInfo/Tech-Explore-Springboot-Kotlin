package learn.spring.kotlin.features.testing

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HtmlController {
    @GetMapping("/")
    fun blog() : String{
        return "ini dari HTML Controller"
    }
}