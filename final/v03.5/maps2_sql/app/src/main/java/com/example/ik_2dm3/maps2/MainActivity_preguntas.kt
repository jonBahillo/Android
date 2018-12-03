package com.example.ik_2dm3.maps2

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.ik_2dm3.maps2.R.id.textView
import kotlinx.android.synthetic.main.activity_main_pregun.*
import java.util.*

class MainActivity_preguntas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main_pregun)
        val mp = MediaPlayer.create(this, R.raw.kurutziaga)
        mp.start()
        (findViewById<View>(R.id.textView) as TextView).text = "Kurutziaga kaleko honetako etxe batzuek bonbardaketaren metraila markak dituzte. Zeintzuk dira zenbakiak?(Bi aukeratu)"


        var texto0: Boolean? = false
        var texto1: Boolean? = false
        var texto2: Boolean? = false
        var texto3: Boolean? = false
        var i = 1
        //for (i in 0..3){
        while (i <= 4) {
            val valor = (Math.random() * 4 + 1).toInt()
            var texto = ""

            if (valor == 1) {
                if (texto0!!) {
                    i--
                    Log.d("mytag2", "pene1")
                } else if (!texto0) {
                    texto0 = true
                    texto = "A. 11"
                }

            }
            if (valor == 2) {
                if (texto1!!) {
                    i--
                    Log.d("mytag2", "pene2")
                } else if (!texto1) {
                    texto1 = true
                    texto = "B. 28"
                }
            }
            if (valor == 3) {
                if (texto2!!) {
                    i--
                    Log.d("mytag2", "pene3")
                } else if (!texto2) {
                    texto2 = true
                    texto = "C. 30"

                }

            }
            if (valor == 4) {
                if (texto3!!) {
                    i--
                    Log.d("mytag2", "pene4")

                } else if (!texto3) {
                    texto3 = true
                    texto = "D. 14"
                }
            }


            when (i) {
                1 -> (findViewById<View>(R.id.checkBox) as CheckBox).text = texto
                2 -> (findViewById<View>(R.id.checkBox1) as CheckBox).text = texto
                3 -> (findViewById<View>(R.id.checkBox2) as CheckBox).text = texto
                4 -> (findViewById<View>(R.id.checkBox3) as CheckBox).text = texto
            }
            i++

        }
        btn.setOnClickListener {

            if (checkBox.isChecked && checkBox1.isChecked)
                popup(null)
            if (checkBox2.isChecked && checkBox3.isChecked)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox2.isChecked)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox3.isChecked)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox.isChecked && checkBox2.isChecked)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox.isChecked && checkBox3.isChecked)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox1.isChecked && checkBox2.isChecked)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox1.isChecked && checkBox3.isChecked)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()

        }
    }



    fun popup(v: View?) {
        var dialogo: Dialog
        dialogo = Dialog(this)
        val Errepikatu: Button
        val Aurrera: Button
        dialogo.setContentView(R.layout.popup_juego)
        Errepikatu = dialogo.findViewById(R.id.btnErrepikatu)
        Aurrera = dialogo.findViewById(R.id.btnAurrera)
        dialogo.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Errepikatu.setOnClickListener {
            dialogo.dismiss()

        }
        Aurrera.setOnClickListener {
            val extras = intent.extras

            val index = extras!!.getInt("index")

            val salir = Intent()
            salir.putExtra("index", index.toString())
            setResult(Activity.RESULT_OK, salir)
            finish()
        }
        dialogo.show()
    }


    }

