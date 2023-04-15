package dev.gafilianog.insorma.data.remote

import dev.gafilianog.insorma.data.model.JSONResponse
import dev.gafilianog.insorma.data.model.User
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService {

    @GET("home")
    suspend fun getHome(): User

    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("v1/5f379081-2473-4494-9cc3-9e808772dc54")
    suspend fun getProducts(): JSONResponse
}