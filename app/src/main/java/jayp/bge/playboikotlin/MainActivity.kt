package jayp.bge.playboikotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zebButton: Button = findViewById(R.id.zebButton)
        zebButton.setOnClickListener {

            val intent = Intent(this, ZebActivity::class.java)

            startActivity(intent)
        }
    }
}