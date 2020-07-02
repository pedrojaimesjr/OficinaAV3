package com.example.oficinaav3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.concurrent.thread


class login : AppCompatActivity() {

    private lateinit var databaseOficina: DatabaseReference

    var usuario = mutableListOf<Usuario>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_email_login.text.clear()
        et_senha_login.text.clear()


        databaseOficina = FirebaseDatabase.getInstance().getReference("usuarios")

        databaseOficina.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                usuario.clear()
                dataSnapshot.children.forEach {
                    usuario.add(it.getValue(Usuario::class.java) as Usuario)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("deu ruim", error.message)
            }
        })
    }

    fun bt_login (view: View) {

        var bool:Boolean = false

        val editTextEmail = findViewById<EditText>(R.id.et_email_login)
        val editTextSenha = findViewById<EditText>(R.id.et_senha_login)

        var email_usuario = ""
        var senha_usuario = ""

        usuario.forEach {
            email_usuario = it.email.toString()
            senha_usuario = it.senha.toString()

            if (editTextEmail.text.isNullOrBlank() || editTextSenha.text.isNullOrBlank())
            {
                if(editTextEmail.text.isNullOrBlank())
                    editTextEmail.setError("Digite corretamente")
                if(editTextSenha.text.isNullOrBlank())
                    editTextSenha.setError("Digite corretamente")
            }
            else
            {
                if (email_usuario == editTextEmail.text.toString())
                {
                    if (senha_usuario == editTextSenha.text.toString())
                    {
                        val intent = Intent(this, menu::class.java)

                        startActivity(intent)
                        et_senha_login.text.clear()
                        bool = true
                    }
                    else
                        Log.i("segundo if", "deu ruim")
                }
                else
                {
                    Log.i("primeiro if", "deu ruim")
                }
            }
        }

        if(!bool)
            Toast.makeText(applicationContext, "Conta não encontrada", Toast.LENGTH_SHORT).show()
    }
}
