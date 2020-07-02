package com.example.oficinaav3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastrar_carro.*

class CadastrarCarro : AppCompatActivity(), View.OnClickListener {

    private lateinit var chassi: EditText
    private lateinit var cliente: EditText
    private lateinit var telefone: EditText
    private lateinit var placa: EditText
    private lateinit var marca: EditText
    private lateinit var modelo: EditText
    private lateinit var problema: EditText

    private lateinit var buttonCancelar: Button
    private lateinit var buttonConfirmar: Button

    private lateinit var lista:Array<EditText>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_carro)

        chassi = chassiRegistrarText
        cliente = clienteRegistrarText
        telefone = telefoneRegistrarText
        placa = placaRegistrarText
        marca = marcaRegistrarText
        modelo = modeloRegistrarText
        problema = problemaRegistrarText

        lista = arrayOf(chassi, cliente, telefone, placa, marca, modelo, problema)

        buttonCancelar = btnCancelar
        buttonConfirmar = btnConfirmar


        buttonConfirmar.setOnClickListener(this)
        buttonCancelar.setOnClickListener(this)
    }

    fun checarCampos(campos: Array<EditText>):Boolean {
        var a:Boolean = true
        for (campo: EditText in campos) {
            if (campo.text.isNullOrBlank()) {
                campo.setError("Campo vazio ou incorreto")
                a = false
            }
        }
        return a
    }

    override fun onClick(p0: View?) {
        if(p0!!.equals(buttonConfirmar)){
            if(checarCampos(lista)){
                Toast.makeText(applicationContext, "Confirmar", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(applicationContext, "Voltar", Toast.LENGTH_SHORT).show()
        }
    }
}