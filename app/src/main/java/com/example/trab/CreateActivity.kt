import com.example.trab.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_layout)

        val btnBack = findViewById<Button>(R.id.btnBack)

        // Voltar para o Menu
        btnBack.setOnClickListener { v: View? ->
            val intent =
                Intent(
                    this@CreateActivity,
                    MainActivity::class.java
                )
            startActivity(intent)
            finish() // Fecha a atividade atual
        }
    }
}