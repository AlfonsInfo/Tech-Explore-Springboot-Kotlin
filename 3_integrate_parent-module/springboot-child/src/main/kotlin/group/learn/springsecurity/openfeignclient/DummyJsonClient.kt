package group.learn.springsecurity.openfeignclient

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@FeignClient(value = "DummyJson" , url = "https://dummyjson.com/products")
interface DummyJsonClient {

    @GetMapping("/{id}")
    fun getDummyJson(
        @PathVariable("id") id : String
    ) : Map<String,Any>
}