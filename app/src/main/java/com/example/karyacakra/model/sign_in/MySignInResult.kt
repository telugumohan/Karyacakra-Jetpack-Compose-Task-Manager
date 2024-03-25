package com.example.karyacakra.model.sign_in

data class MySignInResult(
    val data: MyUserData?,
    val errorMessage: String?
)

data class MyUserData(
    val userId: String,
    val userName: String?,
    val profilePictureUrl: String?,
    val isAnonymous: Boolean = true
)