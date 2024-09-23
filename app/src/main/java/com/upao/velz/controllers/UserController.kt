package com.upao.velz.controllers

import android.content.Context
import com.upao.velz.models.LoginModel
import com.upao.velz.models.User
import com.upao.velz.repositories.UserRepository
import com.upao.velz.services.UserService

class UserController(context: Context) {

    private val userService = UserService(context)

    fun registerUser(context: Context, user: User){
        userService.registerUser(context, user)
    }
    fun loginUser(context: Context, email: String, password: String){
        userService.loginUser(context, email, password)
    }

}