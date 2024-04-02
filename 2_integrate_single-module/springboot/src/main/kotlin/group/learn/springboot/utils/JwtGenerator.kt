package group.learn.springboot.utils


import group.learn.springboot.domain.dto.request.ReqEncodeJwtDto

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import java.util.*
import javax.crypto.spec.SecretKeySpec


class JwtGenerator (){
    companion object{
        private const val SECRET_KEY = "eyJhbGciOiJIUzI1NiJ9.ew0KICAic3ViIjogIjEyMzQ1Njc4OTAiLA0KICAibmFtZSI6ICJBbmlzaCBOYXRoIiwNCiAgImlhdCI6IDE1MTYyMzkwMjINCn0.Ccln-2-B31N0HLMzU7RLXj_hWMk6TaPLbzKPxqZ1Hr0"

    }

//    @get:Value("\${name.token}")
//    var SECRET_KEY : String? = null


    fun createJWT(req : ReqEncodeJwtDto) :String{
        val signatureAlgorithm : SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMillis : Long = System.currentTimeMillis()
        val now = Date(nowMillis)

        val apiKeySecretBytes = SECRET_KEY!!.toByteArray()
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)
        val builder : JwtBuilder = Jwts.builder().setId(req.id)
            .setIssuedAt(now)
            .claim("id", req.id)
            .claim("role", req.role)
            .claim("name", req.name)
            .claim("username", req.username)
            .claim("email", req.email)
            .claim("password", req.password)
            .setIssuer("technocenter")
            .setAudience("technocenter")
            .signWith(signingKey,signatureAlgorithm)

        val expMills = nowMillis + 3600_000L
        val exp = Date(expMills)
        builder.setExpiration(exp)
        return builder.compact()
    }

    fun decodeJwt(jwt : String?) : Claims{
        try{
            val claim : Claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY!!.toByteArray())
                .build()
                .parseClaimsJws(jwt).body
            return claim
        }catch (e : JwtException){
            e.printStackTrace()
            throw e
        }
    }
}