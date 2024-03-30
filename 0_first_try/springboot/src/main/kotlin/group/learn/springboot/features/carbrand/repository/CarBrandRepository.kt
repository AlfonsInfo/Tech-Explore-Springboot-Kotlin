package group.learn.springboot.features.carbrand.repository

import group.learn.springboot.features.carbrand.entity.CarBrand
import org.springframework.data.jpa.repository.JpaRepository

interface CarBrandRepository : JpaRepository<CarBrand, String>{

}