package com.example.trab
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val btnCreate = findViewById<Button>(R.id.btnForm)
        val btnRead = findViewById<Button>(R.id.btnList)
        val btnCredits = findViewById<Button>(R.id.btnCredits)

        // Navegar para o Formulário
        btnCreate.setOnClickListener { v: View? ->
            val intent =
                Intent(
                    this@MainActivity,
                    CreateActivity::class.java
                )
            startActivity(intent)
        }

        // Navegar para a Lista de Itens
        btnRead.setOnClickListener { v: View? ->
            val intent =
                Intent(
                    this@MainActivity,
                    ReadActivity::class.java
                )
            startActivity(intent)
        }

        // Navegar para os Créditos
        btnCredits.setOnClickListener { v: View? ->
            val intent =
                Intent(
                    this@MainActivity,
                    CreditsActivity::class.java
                )
            startActivity(intent)
        }
    }
}