package com.example.ik_2dm3.juegoproyecto2

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
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mp = MediaPlayer.create(this, R.raw.kurutziaga)
        mp.start()
        textView.text = "Kurutziaga kaleko honetako etxe batzuek bonbardaketaren metraila markak dituzte. Zeintzuk dira zenbakiak?(Bi aukeratu)"
        checkBox.text = "A. 11"
        checkBox1.text = "B. 28"
        checkBox2.text = "C. 30"
        checkBox3.text = "D. 14"
        btn.text = "Aurrera"

        btn.setOnClickListener {

            if (checkBox.isChecked && checkBox1.isChecked)
                popup(null)
            if (checkBox2.isChecked && checkBox3.isChecked)
                Toast.makeText(this@MainActivity, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox.isChecked)
                Toast.makeText(this@MainActivity, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox2.isChecked)
                Toast.makeText(this@MainActivity, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox3.isChecked)
                Toast.makeText(this@MainActivity, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox1.isChecked)
                Toast.makeText(this@MainActivity, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox.isChecked && checkBox2.isChecked)
                Toast.makeText(this@MainActivity, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox.isChecked && checkBox3.isChecked)
                Toast.makeText(this@MainActivity, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox1.isChecked && checkBox2.isChecked)
                Toast.makeText(this@MainActivity, "Txarto dago", Toast.LENGTH_SHORT).show()
            if (checkBox1.isChecked && checkBox3.isChecked)
                Toast.makeText(this@MainActivity, "Txarto dago", Toast.LENGTH_SHORT).show()

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
            /*val salir = Intent()
            setResult(Activity.RESULT_OK, salir)*/
            finish()
        }
        dialogo.show()
    }

    }

