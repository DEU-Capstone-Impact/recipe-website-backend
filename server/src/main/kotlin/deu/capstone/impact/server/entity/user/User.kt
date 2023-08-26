package deu.capstone.impact.server.entity.user

import jakarta.persistence.*

@Entity(name = "users")
class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false, unique = true)
        val email: String,

        @Column(nullable = false, unique = true)
        val userName: String,

        @Column(nullable = false)
        val password: String,

        @Column(nullable = false)
        val point: Long = 0,
)