package deu.capstone.impact.server.controller

import deu.capstone.impact.server.dto.user.LoginRequestDto
import deu.capstone.impact.server.dto.user.LoginResponseDto
import deu.capstone.impact.server.service.JwtService
import deu.capstone.impact.server.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
        private val userService: UserService,
        private val jwtService: JwtService,
) {
    /**
     * 로그인을 위한 api
     * @param 로그인을 시도한 유저 정보
     * @return jwt / 실패 시 에러 메시지
     */
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequestDto): LoginResponseDto {
        val user = userService.login(request)

        val token = jwtService.generateToken(user)

        return LoginResponseDto(
                token = token
        )
    }
}