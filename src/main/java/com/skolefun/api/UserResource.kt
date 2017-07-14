package com.skolefun.api

import com.skolefun.config.jwt.JwtTokenUserData
import com.skolefun.config.jwt.JwtTokenUserDataService
import com.skolefun.config.jwt.JwtTokenUtil
import com.skolefun.model.Role
import com.skolefun.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserResource(
        val jwtTokenUtil: JwtTokenUtil,
        val authenticationManager: AuthenticationManager,
        val userDetailsService: JwtTokenUserDataService,
        val passwordEncoder: PasswordEncoder,
        val userRepository: UserRepository) {

    data class Credentials(val username: String, val password: String)

    data class CreateUserData(val username: String, val password: String, val grade: Int, val classLetter: String, val role: Role)


    @RequestMapping("/api/user", method = arrayOf(RequestMethod.POST))
    fun createUser(@RequestBody createUserData: CreateUserData): ResponseEntity<User> {
        val user = com.skolefun.model.User(createUserData.username, passwordEncoder.encode(createUserData.password), hashSetOf(createUserData.role), createUserData.grade, createUserData.classLetter)
        userRepository.save(user)
        val token = jwtTokenUtil.generateToken(JwtTokenUserData(1L, createUserData.username, setOf(createUserData.role)))
        return ResponseEntity.ok().header("Authentication", token).body(getMockUser(token))
    }

    @RequestMapping("/api/user/login", method = arrayOf(RequestMethod.POST))
    fun login(@RequestBody credentials: Credentials): ResponseEntity<User> {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(credentials.username, credentials.password))
        val jwtTokenUserData = userDetailsService.createFromUsername(credentials.username)
        val token = jwtTokenUtil.generateToken(jwtTokenUserData)
        return ResponseEntity.ok().header("Authentication", token).body(getMockUser(token))
    }

    fun getMockUser(token: String) = com.skolefun.api.User(
            "Lille Ib",
            UserType.STUDENT,
            Environment(
                    "Hareskov skole",
                    getMockGroupd(),
                    EnvironmentType.SCHOOL,
                    300,
                    randomPicture(),
                    randomPicture(),
                    "#e60000",
                    "#b30000",
                    "#66ff66"
            ),
            listOf()
    )

    fun getMockGroupd() = listOf<Group>(
            Group("ABCVærksted", true, true),
            Group("Kantine", true, true),
            Group("3C", false, true)
    )

    fun randomPicture() =
            listOf(
                    "http://hareskovskole.mrburns.webhot.dk/billeder/7C_small.JPG",
                    "http://hareskovskole.mrburns.webhot.dk/billeder/Haandbold2015_small.jpg",
                    "http://hareskovskole.mrburns.webhot.dk/haresk4.jpg"
            )[Random().nextInt(3)]

}
