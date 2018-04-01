package com.merkmod.sportika.models
import com.google.gson.annotations.SerializedName



data class LoginStatus(
		@SerializedName("success") var success: Int = 0,
		@SerializedName("message") var message: String? = null,
		@SerializedName("login") var login: Login? = null
)

data class Login(
		@SerializedName("id") var id: Int,
		@SerializedName("userName") var userName: String,
		@SerializedName("password") var password: String,
		@SerializedName("designation") var designation: String,
		@SerializedName("reportingPerson") var reportingPerson: Any
)

data class RegisterResponse(
		@SerializedName("success") var success: Int = 0,
		@SerializedName("message") var message: String? = null
)

data class PushResponse(
		@SerializedName("success") var success: Int = 0,
		@SerializedName("message") var message: String? = null
)