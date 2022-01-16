package com.example.starwars.infra

import com.example.starwars.infra.helpers.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var retrofit: Retrofit
        private const val baseUrl = "https://swapi.dev/api/"
        private val contentType by lazy {
            "application/json".toMediaTypeOrNull()!!
        }

        @ExperimentalSerializationApi
        fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder().apply {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
            }.build()
            return if (!Companion::retrofit.isInitialized)
                with(Retrofit.Builder()) {
                    baseUrl(baseUrl)
                    client(httpClient)
                    addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    addConverterFactory(Json.parser.asConverterFactory(contentType))
                    build()
                } else retrofit
        }

        @ExperimentalSerializationApi
        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}