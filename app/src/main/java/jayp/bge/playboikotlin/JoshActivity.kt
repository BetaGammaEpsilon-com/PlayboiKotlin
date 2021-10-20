package jayp.bge.playboikotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class JoshActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_josh)

        val button: Button = findViewById(R.id.joshButton)

        button.setOnClickListener {
            finish()
        }
    }
}