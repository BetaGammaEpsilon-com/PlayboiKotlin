package jayp.bge.playboikotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.joshButton)

        button.setOnClickListener {
            val i = Intent(this@MainActivity, JoshActivity::class.java)
            startActivity(i)
        }



        val robbieButton: Button = findViewById(R.id.robbieButton)
        robbieButton.setOnClickListener {

            val intent = Intent(this, RobTivity::class.java)
            startActivity(intent)

        }


        val zebButton: Button = findViewById(R.id.zebButton)
        zebButton.setOnClickListener {

            val intent = Intent(this, ZebActivity::class.java)

            startActivity(intent)
        }
    }
}