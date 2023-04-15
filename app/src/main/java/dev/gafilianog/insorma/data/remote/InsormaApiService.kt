package dev.gafilianog.insorma.data.remote
//
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.http.Body
//import retrofit2.http.POST
//
//private const val BASE_URL = "http://api.gafi.social/"
//
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()

//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(BASE_URL)
//    .build()
//
//interface InsormaApiService {
//
//    @GET("v1/5f379081-2473-4494-9cc3-9e808772dc54")
//    suspend fun getProducts(): JSONResponse
//    @POST("login")
//    Call<LoginResponse> login(@Body)
////    suspend fun login(): LoginResponse
//}
//
//object InsormaApi {
//    val retrofitService: InsormaApiService by lazy {
//        retrofit.create(InsormaApiService::class.java)
//    }
//}
