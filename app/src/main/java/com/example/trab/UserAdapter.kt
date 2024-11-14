package com.example.trab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


// Adapter para exibir a lista de usuários
class UserAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // Cria a view de cada item da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    // Preenche os dados no ViewHolder
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.nameTextView.text = user.name
        holder.usernameTextView.text = user.username
    }

    // Retorna o número de itens da lista
    override fun getItemCount(): Int {
        return userList.size
    }

    // ViewHolder que referencia os elementos de cada item
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvName)
        val usernameTextView: TextView = itemView.findViewById(R.id.tvUsername)
    }
}
