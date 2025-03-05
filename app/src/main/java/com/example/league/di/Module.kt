package com.example.league.di

import com.example.league.data.network.KtorClient
import com.example.league.data.network.KtorRemoteSource
import com.example.league.data.repository.LeagueRepositoryImpl
import com.example.league.domain.repository.LeagueRepository
import com.example.league.domain.use_cases.GetChampionDetailsUseCase
import com.example.league.domain.use_cases.GetChampionListUseCase
import com.example.league.presentation.championList.ChampionListViewModel
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val module = module {
    //single { KtorRemoteSource() }

    single<HttpClientEngine> { OkHttp.create() }
    single { KtorClient.create(get()) }
    singleOf(::KtorRemoteSource)
    //factory<KtorRemoteSource> { KtorRemoteSource(get()) }
    singleOf(::LeagueRepositoryImpl) { bind<LeagueRepository>() }
    //singleOf(::GetChampionListUseCase)
    //singleOf(::GetChampionDetailsUseCase)
    //factory<LeagueRepository> { LeagueRepositoryImpl(get()) }
    //factory { GetChampionListUseCase(get()) }


    viewModelOf(::ChampionListViewModel)
}