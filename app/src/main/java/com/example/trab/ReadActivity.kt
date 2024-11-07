import com.example.trab.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class ReadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_layout)

        val btnBack = findViewById<Button>(R.id.btn_back)

        // Voltar para o Menu
        btnBack.setOnClickListener { v: View? ->
            val intent =
                Intent(
                    this@ReadActivity,
                    MainActivity::class.java
                )
            startActivity(intent)
            finish() // Fecha a atividade atual
        }
    }
}
