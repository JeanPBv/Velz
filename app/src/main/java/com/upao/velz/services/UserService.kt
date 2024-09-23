package com.upao.velz.services

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import com.upao.velz.models.LoginModel
import com.upao.velz.models.User
import com.upao.velz.repositories.UserRepository

class UserService(context: Context) {

    private val userRepository = UserRepository(context)

    fun registerUser(context: Context, user: User){
        userRepository.registerUser(context, user)
    }

    fun loginUser(context: Context, email: String, password: String){
        userRepository.loginUser(context, email,password)
    }
}