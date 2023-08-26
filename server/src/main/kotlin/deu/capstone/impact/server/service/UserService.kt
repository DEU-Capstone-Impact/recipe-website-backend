package deu.capstone.impact.server.service

import deu.capstone.impact.server.dto.user.LoginRequestDto
import deu.capstone.impact.server.entity.user.User
import deu.capstone.impact.server.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
        val userRepository: UserRepository,
) {
    // 로그인을 위한 메서드
    fun login(data: LoginRequestDto): User {
        val user = userRepository.findByEmail(data.email) ?: error("")
        check(user.password == data.password)
        return user
    }
}