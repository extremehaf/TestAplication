package br.com.testaplication.ui.Todos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.testaplication.R
import br.com.testaplication.data.model.Todo
import br.com.testaplication.di.compose.ViewModelFragment
import kotlinx.android.synthetic.main.fragment_todos.*

class TodosFragment : ViewModelFragment() {

    private val viewModel: TodosViewModel by injectActivityVIewModels()
    private val todosAdapter = TodosRecyclerViewAdapter(arrayListOf<Todo>())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_todos, container, false)
        val locationsList: RecyclerView = root.findViewById(R.id.todosList)
        val layout = LinearLayoutManager(this.context)
        locationsList.apply {
            layoutManager = layout
            adapter = todosAdapter
        }
        observeViewModel()
        return root
    }

    private fun observeViewModel() {
        viewModel.todos.observe(viewLifecycleOwner, Observer { todos ->
            todos?.let {
                todosList.visibility = View.VISIBLE
                todosAdapter.updateLocations(it)
            }
        })
        viewModel.locationsLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                list_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    todosList.visibility = View.GONE
                }
            }
        })
    }
}
