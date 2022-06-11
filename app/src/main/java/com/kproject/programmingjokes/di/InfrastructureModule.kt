package com.kproject.programmingjokes.di

import com.kproject.programmingjokes.domain.provider.StringResourceProvider
import com.kproject.programmingjokes.infrastructure.provider.StringResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val infrastructureModule = module {
    single<StringResourceProvider> { StringResourceProviderImpl(androidContext()) }
}