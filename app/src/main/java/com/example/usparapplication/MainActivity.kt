package com.example.usparapplication

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class MainActivity : AppCompatActivity() {
    private var connection: Connection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConnect: Button = findViewById(R.id.button)

        btnConnect.setOnClickListener {
            val nameTextView: TextView = findViewById(R.id.textView)
            val c = ConSQL()
            connection = c.conclass()
            println("connection : ${connection}")
            if (connection != null) {
                try {

// Try bloğu içinde:
                    Log.d("MainActivity", "Bağlantı başarılı.")
                    val sqlStatement = "Select * from INFO_KALITE_PERSONEL"
                    val smt: Statement = connection!!.createStatement()
                    val resultSet: ResultSet = smt.executeQuery(sqlStatement)
                    val studentNames = ArrayList<String>()

                    while (resultSet.next()) {
                        val studentName: String? = resultSet.getString(3)
                        if (studentName != null) {
                            println("nameee : ${studentName}")
                            studentNames.add(studentName)
                        }
                    }
                    Log.d("MainActivity", "Veriler başarılı bir şekilde alındı.")

// studentNames listesi, tablodaki tüm öğrenci adlarını içerir.

                    // Try bloğunun sonunda:
                    val listView: ListView = findViewById(R.id.listView)
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentNames)
                    listView.adapter = adapter


                    connection!!.close()
                } catch (e: Exception) {
                    Log.e("error is", e.message.toString())
                }

            }
        }
    }
}
