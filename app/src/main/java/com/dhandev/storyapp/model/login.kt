package com.dhandev.storyapp.model

data class login(
    val error : Boolean,
    val message : String,
    val loginResult : LoginResultItem
)
{
data class LoginResultItem(
    val userId : String,
    val name : String,
    val token : String
)
}
