package com.example.apicall


import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.GET
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


data class Joke(


//    @field:Json(name="joke")
//    var joke: String,
    val id: Int,
    val punchline: String,
    val setup: String,
    val type: String

)
private val moshi=Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

const val BASE_URL = "https://official-joke-api.appspot.com/jokes/programming/"
interface APIService{
    @GET("ten")
    suspend fun getTodos():List<Joke>
    companion object{
        var apiService:APIService?=null
        fun getInstance(): APIService{
            if(apiService==null){
                apiService= Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}