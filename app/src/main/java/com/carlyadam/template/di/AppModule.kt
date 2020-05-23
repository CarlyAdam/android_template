package com.carlyadam.template.di

import com.carlyadam.template.data.api.ApiService
import com.carlyadam.template.data.api.NetworkConnectionInterceptor
import com.carlyadam.template.repo.AlbumRepository
import com.carlyadam.template.viewmodel.AlbumViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val webServiceModule = module {
        single { ApiService(get()) }
        single {
            NetworkConnectionInterceptor(
                get()
            )
        }
    }
    val albumModule = module {
        single { AlbumRepository(get(), get()) }
        viewModel { AlbumViewModel(get()) }
    }
}