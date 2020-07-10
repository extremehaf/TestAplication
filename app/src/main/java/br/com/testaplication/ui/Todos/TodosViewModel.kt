package br.com.testaplication.ui.Todos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.testaplication.data.model.Todo
import br.com.testaplication.data.network.ApiService
import br.com.testaplication.data.repository.todos.TodoRepository
import io.realm.Sort
import javax.inject.Inject

class TodosViewModel
@Inject constructor(
    var apiService: ApiService,
    todosRepository: TodoRepository
) : ViewModel() {

    var todos = MutableLiveData<List<Todo>>()
    val locationsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        todos.value = todosRepository.findAllSorted("id", sort = Sort.ASCENDING, detached = false)

        locationsLoadError.value = false
        loading.value = false
    }
}