package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.trab.databinding.CreateLayoutBinding
import kotlinx.coroutines.launch
import retrofit2.Response

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: CreateLayoutBinding  // ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = CreateLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Voltar para o Menu
        binding.btnBack.setOnClickListener { v: View? ->
            val intent = Intent(this@CreateActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Submeter os dados para a API
        binding.btnSubmit.setOnClickListener {
            val name = binding.edtCampo1.text.toString()
            val username = binding.edtCampo2.text.toString()

            if (name.isNotEmpty() && username.isNotEmpty()) {
                val user = User(name = name, username = username)

                // Usando corrotinas para fazer a requisição para a API
                lifecycleScope.launch {
                    try {
                        // Chama o método suspenso de forma assíncrona
                        val response: Response<User> = RetrofitInstance.apiService.createUser(user)

                        // Verifica se a resposta foi bem-sucedida
                        if (response.isSuccessful) {
                            Toast.makeText(this@CreateActivity, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show()
                            // Limpar campos e redirecionar
                            val intent = Intent(this@CreateActivity, ReadActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@CreateActivity, "Erro ao criar usuário", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@CreateActivity, "Falha na conexão", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this@CreateActivity, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
