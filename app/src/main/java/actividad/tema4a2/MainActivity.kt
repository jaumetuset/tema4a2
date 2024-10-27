package actividad.tema4a2

import actividad.tema4a2.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureOperationButtons()
        configureExitButton()
        configureChangePasswordButton()
        showWelcomeMessage()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun configureOperationButtons() {
        binding.btnPosGlobal.setOnClickListener {
            showSnackbar("Posición Global seleccionada")
        }
        binding.btnMovements.setOnClickListener {
            showSnackbar("Movimientos seleccionados")
        }
        binding.btnTransfers.setOnClickListener {
            showSnackbar("Transferencias seleccionadas")
        }
        binding.btnPromotions.setOnClickListener {
            showSnackbar("Promociones seleccionadas")
        }
        binding.btnAtms.setOnClickListener {
            showSnackbar("Cajeros seleccionados")
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun configureExitButton() {
        binding.btnExit.setOnClickListener {
            finish()
        }
    }

    private fun configureChangePasswordButton() {
        binding.btnChangePassword.setOnClickListener {
            showChangePasswordDialog()
        }
    }

    private fun showChangePasswordDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cambiar Contraseña")

        val input = android.widget.EditText(this).apply {
            inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        builder.setView(input)

        builder.setPositiveButton("Confirmar") { _, _ ->
            val newPassword = input.text.toString()
            if (newPassword.isNotEmpty()) {
                showSnackbar("Contraseña cambiada")
            } else {
                showSnackbar("La contraseña no puede estar vacía")
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    private fun showWelcomeMessage() {
        val dni = intent.getStringExtra("DNI")
        val welcomeMessage = "Bienvenido/a, $dni"
        binding.welcomeMessage.text = welcomeMessage
    }
}
