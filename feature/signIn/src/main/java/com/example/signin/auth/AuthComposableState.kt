package com.example.signin.auth

data class AuthComposableState(
    var inputEmail: String,
    var inputPassword: String,
    var btnState: Boolean,
)
