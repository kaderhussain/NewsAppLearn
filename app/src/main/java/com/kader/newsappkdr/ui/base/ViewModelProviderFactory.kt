package com.kader.newsappkdr.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelProviderFactory<L : ViewModel>(
    private val kClass: KClass<L>,
    private val creator: () -> L
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <L : ViewModel> create(modelClass: Class<L>): L {
        if (modelClass.isAssignableFrom(kClass.java)) return creator() as L
        throw IllegalArgumentException("Unknown class name")
    }

}