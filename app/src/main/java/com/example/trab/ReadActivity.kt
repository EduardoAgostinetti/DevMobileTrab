package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trab.databinding.ReadLayoutBinding
import kotlinx.coroutines.launch
import retrofit2.Response

class ReadActivity : AppCompatActivity() {

    private lateinit var binding: ReadLayoutBinding // ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = ReadLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Voltar para o Menu
        binding.btnBack.setOnClickListener {
            navigateToMainActivity()
        }

        // Carregar os usuários via coroutines
        loadUsers()
    }

    private fun loadUsers() {
        lifecycleScope.launch {
            try {
                // Chama a API de forma assíncrona
                val response: Response<List<User>> = RetrofitInstance.apiService.getUsers()

                // Verifica se a resposta foi bem-sucedida
                if (response.isSuccessful) {
                    val users = response.body()
                    if (users != null) {
                        // Atualiza a RecyclerView com os usuários
                        val adapter = UserAdapter(users)
                        binding.recyclerView.adapter = adapter
                    }
                } else {
                    // Exibe erro se a resposta não for bem-sucedida
                    Toast.makeText(this@ReadActivity, "Erro ao carregar os dados", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Exibe erro em caso de falha de conexão
                Toast.makeText(this@ReadActivity, "Falha na conexão", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@ReadActivity, MainActivity::class.java)
        startActivity(intent)
        finish()  // Fecha a atividade atual
    }
}
