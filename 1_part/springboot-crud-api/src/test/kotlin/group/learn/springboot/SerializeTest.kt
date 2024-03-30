package group.learn.springboot

import com.fasterxml.jackson.databind.ObjectMapper
import group.learn.springboot.domain.dto.request.ReqBrandApiDto
import group.learn.springboot.domain.dto.request.ReqBrandDetailDto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SerializationTest {
    @Test
    fun testSerialization() {
        val objectMapper = ObjectMapper()

        // Buat objek DTO
        val requestDto = ReqBrandApiDto(
            ReqBrandDetailDto(
                pSearch = "BANDUNG II",
                pLimit = "1"
            )
        )

        // Serialisasi DTO ke JSON
        val serializedJson = objectMapper.writeValueAsString(requestDto)

        // JSON yang diharapkan
        val expectedJson = "{\"doGetBranch\":{\"P_SEARCH\":\"BANDUNG II\",\"P_LIMIT\":\"1\"}}"

        // Bandingkan hasil serialisasi dengan yang diharapkan
        Assertions.assertEquals(expectedJson, serializedJson)
    }

}