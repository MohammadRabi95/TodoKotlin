package com.rabi.todokotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabi.todokotlin.model.data.Todo
import com.rabi.todokotlin.view.CallbackList
import com.rabi.todokotlin.viewmodel.repo.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    fun getAll() : LiveData<List<Todo>> {
        return repository.getAll()
    }

    fun insert(todo: Todo) {
        viewModelScope.launch {
            repository.insert(todo)
        }
    }

    fun update(todo: Todo) {
        viewModelScope.launch {
            repository.update(todo)
        }
    }


}