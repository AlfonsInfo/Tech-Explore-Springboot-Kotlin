package group.learn.springboot.features.carbrand.controller

import group.learn.springboot.data.Constants
import group.learn.springboot.features.carbrand.entity.CarBrand
import group.learn.springboot.features.carbrand.repository.CarBrandRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.Endpoint.V1)
class CarBrandController (
    val carBrandRepository: CarBrandRepository
){



    @GetMapping("/get-all")
    fun testGetCarBrand() : List<CarBrand> {
        return carBrandRepository.findAll()
    }
}