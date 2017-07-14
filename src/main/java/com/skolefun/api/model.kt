package com.skolefun.api

class Group(val name: String, val allowPayments: Boolean, val allowMessages: Boolean)

class Environment(
        val name: String,
        val groups: List<Group>,
        val environmentType: EnvironmentType,
        val accountBalance: Int,
        val logo: String,
        val smallLogo: String,
        val primaryColor: String,
        val primaryColorDark: String,
        val accentColor: String
)

class User(
        val username: String,
        val type: UserType,
        val primaryEnvironment: Environment,
        val secondaryEnvironments: List<Environment>
)

enum class EnvironmentType {
    SCHOOL, CLUB
}

enum class UserType {
    STUDENT, PARENT, TEACHER
}

