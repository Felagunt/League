package com.example.league.di

import com.example.league.data.network.KtorClient
import com.example.league.data.network.KtorRemoteSource
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpConfig
import org.koin.dsl.module

val ktorModule = module {
    //single<HttpClientEngine> { OkHttp.create() }
    //single { KtorClient }
    //single<HttpClientEngine> { OkHttp.create() }
    //single<HttpClientConfig<OkHttpConfig>> { HttpClientConfig() }
}