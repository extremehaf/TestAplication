package br.com.testaplication.ui.Todos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.testaplication.data.model.Todo
import br.com.testaplication.databinding.ItemTodoBinding


class TodosRecyclerViewAdapter(var todos: ArrayList<Todo>) :
    RecyclerView.Adapter<TodosViewHolder>() {

    fun updateLocations(newPosts: List<Todo>) {
        todos.clear()
        todos.addAll(newPosts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = TodosViewHolder(
        ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = todos.size
    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        val todos: Todo = todos[position]

        holder.mIsInTheMiddle = position == (this.todos.size / 2)
        holder.bind(todos)
    }
}

class TodosViewHolder(val binding: ItemTodoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    // We'll use this field to showcase matching the holder from the test.
    var mIsInTheMiddle = false

    fun bind(todo: Todo) {
        binding.todo = todo
        binding.executePendingBindings()
    }
}