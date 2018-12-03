package com.example.ik_2dm3.maps2

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.ik_2dm3.maps2.R.id.textView
import kotlinx.android.synthetic.main.activity_main_pregun.*
import java.util.*


class MainActivity_preguntas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pregun)
        val mp = MediaPlayer.create(this, R.raw.kurutziaga)
        mp.start()
        textView.text = "Kurutziaga kaleko honetako etxe batzuek bonbardaketaren metraila markak dituzte. Zeintzuk dira zenbakiak?(Bi aukeratu)"
/*
        checkBox.text = "A. 11"
        checkBox1.text = "B. 28"
        checkBox2.text = "C. 30"
        checkBox3.text = "D. 14"*/


        var texto0:Boolean = false
        var texto1:Boolean = false
        var texto2:Boolean = false
        var texto3:Boolean = false
        for (i in 0..3){
        var r = Random()
        var valor = r.nextInt(3 - 0) + 0;
        var texto = ""

        when(valor) {

            0 ->if (texto0 == false){texto0 = true; texto = "A. 11"}else{i-1}
            1->if (texto1 == false){texto1 = true; texto = "B. 28"}else{i-1}
            2->if (texto2 == false){texto2 = true; texto = "C. 30"}else{i-1}
            3->if (texto3 == false){texto3 = true; texto = "D. 14"}else{i-1}

        }
            if (i == 0)
                checkBox.text = texto
            if (i == 1)
                checkBox1.text = texto
            else if (i == 2)
                checkBox2.text = texto
            else if (i == 3)
                checkBox3.text = texto
        }


        btn.text = "Aurrera"

        btn.setOnClickListener {

            if (texto0 && texto1)
                popup(null)
            if (texto2 && texto3)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (texto2)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (texto3)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (texto0 && texto3)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (texto1 && texto2)
                Toast.makeText(this@MainActivity_preguntas, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (texto1 && texto3)
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
            checkBox.isChecked = false
            checkBox1.isChecked = false
            checkBox2.isChecked = false
            checkBox3.isChecked = false
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

