package com.devsahil.memeshare.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devsahil.memeshare.repository.MemeRepository

class MainViewModelFactory(private val repository: MemeRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return MainViewModel(repository) as T

    }


}