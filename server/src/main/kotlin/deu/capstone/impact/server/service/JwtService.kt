package deu.capstone.impact.server.service

import deu.capstone.impact.server.entity.user.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class JwtService(
        @Value("\${application.jwt.secret}")
        private val secretKey: String,

        @Value("\${application.jwt.expiration}")
        private val expiration: Long,
) {
    // jwt 생성을 위한 메서드
    fun generateToken(user: User): String {
        val jwtpayload = JwtPayload(
                id = user.id,
                email = user.email,
                userName = user.userName,
                password = user.password,
                point = user.point,
        )

        return Jwts.builder()
                .setSubject("user")
                .claim("user", jwtpayload)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
    }

    data class JwtPayload(
            val id: Long,
            val email: String,
            val userName: String,
            val password: String,
            val point: Long,
    )
}