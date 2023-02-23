package com.example.motionhack_2023_kel_5

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var editEmail : EditText
    lateinit var editPassword : EditText
    lateinit var btnRegister : TextView
    lateinit var btnLogin : Button

    //Animasi Loading
    lateinit var progressDialog: ProgressDialog

    var firebaseAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser!=null){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editEmail = findViewById(R.id.email)
        editPassword = findViewById(R.id.password)
        btnRegister = findViewById(R.id.btn_register)
        btnLogin = findViewById(R.id.btn_login)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Logging")
        progressDialog.setMessage("Sedang memproses informasi akun")

        btnLogin.setOnClickListener {
            if (editEmail.text.isNotEmpty() && editPassword.text.isNotEmpty()) {
                //Memulai proses Login
                processLogin()
            } else {
                Toast.makeText(this, "Silahkan isi email dan password terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        btnRegister.setOnClickListener(){
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    private fun processLogin(){
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()

        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, MainActivity::class.java))
            } .addOnFailureListener { error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            } .addOnCompleteListener {
                progressDialog.dismiss()
            }
    }
}