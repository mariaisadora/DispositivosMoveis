package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sh = getSharedPreferences("cadastroproduto", Context.MODE_PRIVATE)

        btnLimpar.setOnClickListener { v: View? ->
            txtNome.text.clear()
            txtPrecoC.text.clear()
            txtPrecoV.text.clear()

        }
        btnSalvar.setOnClickListener { v: View? ->
          if(txtNome.text.isNotEmpty()){
              sh.edit().putString(txtNome.text.toString(), txtPrecoC.text.toString()).apply()
              sh.edit().putString(txtNome.text.toString(), txtPrecoV.text.toString()).apply()
              Toast.makeText(this,"Produto cadastrado!", Toast.LENGTH_SHORT)
          } else{
              Toast.makeText(this,"É necessário preencher o nome", Toast.LENGTH_SHORT)
          }
        }
        btnAbrir.setOnClickListener { v: View? ->
            if (txtNome.text.isNotEmpty()) {
                var produto = sh.getString(txtNome.text.toString(), "")
                if (produto!!.isNotEmpty()) {
                    txtPrecoC.setText(produto)
                    txtPrecoV.setText(produto)
                    Toast.makeText(this, "Cadastro aberto", Toast.LENGTH_SHORT)
                }
            } else {
                Toast.makeText(this, "É necessário preencher o nome", Toast.LENGTH_SHORT)
            }

        }
        btnCalcular.setOnClickListener { v: View? ->
            if(txtPrecoC.text.toString() != "" && txtPrecoV.text.toString() != ""){
                val lucro = txtPrecoV.text.toString().toDouble() - txtPrecoC.text.toString().toDouble()
                if(lucro>0){
                    Toast.makeText(this, "Lucro", Toast.LENGTH_SHORT)
                } else{
                    Toast.makeText(this, "Prejuízo", Toast.LENGTH_SHORT)
                }
            }

        }
    }

}