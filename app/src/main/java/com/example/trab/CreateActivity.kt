package com.example.trab
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_layout)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val etName = findViewById<EditText>(R.id.edtCampo1)
        val etUsername = findViewById<EditText>(R.id.edtCampo2)

        // Voltar para o Menu
        btnBack.setOnClickListener { v: View? ->
            val intent = Intent(this@CreateActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Submeter os dados para a API
        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            val username = etUsername.text.toString()

            if (name.isNotEmpty() && username.isNotEmpty()) {
                val user = User(name = name, username = username)

                // Fazer a requisição para a API
                RetrofitInstance.apiService.createUser(user).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@CreateActivity, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show()
                            // Limpar campos
                            val intent = Intent(this@CreateActivity, ReadActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@CreateActivity, "Erro ao criar usuário", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(this@CreateActivity, "Falha na conexão", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this@CreateActivity, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}