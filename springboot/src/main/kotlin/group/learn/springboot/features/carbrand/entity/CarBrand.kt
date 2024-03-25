package group.learn.springboot.features.carbrand.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "car_brand")
data class CarBrand(
    @Id
    val id : String,
    val name : String?,
    val description : String?,
    val countryOfOrigin : String?,
    val yearOfEstablishment : String?,
    val countModel : Int?
)
