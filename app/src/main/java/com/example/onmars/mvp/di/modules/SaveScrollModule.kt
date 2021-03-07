package com.example.onmars.mvp.di.modules

import com.example.onmars.mvp.model.entity.scroll.ISaveScroll
import com.example.onmars.mvp.model.entity.scroll.SaveScroll
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SaveScrollModule {

    @Singleton
    @Provides
    fun position(): ISaveScroll = SaveScroll()

}