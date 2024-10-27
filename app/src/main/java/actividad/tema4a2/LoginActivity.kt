package actividad.tema4a2

import actividad.tema4a2.databinding.ActivityLoginBinding
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIn.setOnClickListener {
            val user = binding.textUser.text.toString()
            val password = binding.textPassword.text.toString()

            binding.textUser.setOnFocusChangeListener{v, hasFocus ->
                if(!hasFocus){
                    if(user.isEmpty()){
                        binding.textUser.error="Este campo no puede estar vacio"
                    }
                }
            }

            binding.textPassword.setOnFocusChangeListener{v, hasFocus ->
                if(!hasFocus){
                    if(password.isEmpty()){
                        binding.textPassword.error="Este campo no puede estar vacio"
                    }
                }
            }

            if (user.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("User", user)
                }
                startActivity(intent)
            }


        }



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}