package com.example.trab
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_layout)

        val btnBack = findViewById<Button>(R.id.btn_back)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Configurar RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Voltar para o Menu
        btnBack.setOnClickListener {
            val intent = Intent(this@ReadActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Fecha a atividade atual
        }

        // Fazer a requisição GET para listar usuários
        RetrofitInstance.apiService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    if (users != null) {
                        // Atualiza a RecyclerView com os usuários
                        val adapter = UserAdapter(users)
                        recyclerView.adapter = adapter
                    }
                } else {
                    Toast.makeText(this@ReadActivity, "Erro ao carregar os dados", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@ReadActivity, "Falha na conexão", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
