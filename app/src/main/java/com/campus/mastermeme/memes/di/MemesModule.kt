package com.campus.mastermeme.memes.di

import com.campus.mastermeme.memes.presentation.MemeListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val memesModule = module {
    viewModel { MemeListViewModel(get()) }
}