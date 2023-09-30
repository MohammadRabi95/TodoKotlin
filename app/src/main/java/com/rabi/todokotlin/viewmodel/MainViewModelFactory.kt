package com.rabi.todokotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rabi.todokotlin.model.data.Todo
import com.rabi.todokotlin.viewmodel.repo.MainRepository

class MainViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}