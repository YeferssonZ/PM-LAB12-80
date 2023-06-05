package com.miempresa.serviciowebv4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_registrar.*
import org.json.JSONObject

class Registrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        btnRegistrarse.setOnClickListener {
            val nuevoUsuario = txtUsuario.text.toString().trim()
            val nuevaClave = txtClave.text.toString().trim()
            val nuevoEstado = txtEstado.text.toString().trim()

            if (nuevoUsuario.isNotEmpty() && nuevaClave.isNotEmpty() && nuevoEstado.isNotEmpty()) {
                val queue = Volley.newRequestQueue(this)
                val url = "http://172.22.132.54:3000/usuarios"

                val params = HashMap<String, String>()
                params["usuario"] = nuevoUsuario
                params["clave"] = nuevaClave
                params["estado"] = nuevoEstado

                val jsonObject = JSONObject(params as Map<*, *>)

                val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST, url, jsonObject,
                    Response.Listener { response ->
                        Toast.makeText(
                            applicationContext,
                            "Nuevo usuario registrado con éxito",
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    Response.ErrorListener { error ->
                        Toast.makeText(
                            applicationContext,
                            "Error al registrar nuevo usuario",
                            Toast.LENGTH_LONG
                        ).show()
                    })

                queue.add(jsonObjectRequest)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Por favor, complete todos los campos",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        btnVolver.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}