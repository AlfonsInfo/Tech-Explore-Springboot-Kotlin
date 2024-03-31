package group.learn.springsecurity

import group.learn.springsecurity.openfeignclient.DummyJsonClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dummy")
class DummyJsonController(
    val dummyJsonClient: DummyJsonClient
) {

    @GetMapping("/{id}")
    fun getDummyJson(@PathVariable("id") id : String) : Map<String,Any>{
        return dummyJsonClient.getDummyJson(id)
    }
}