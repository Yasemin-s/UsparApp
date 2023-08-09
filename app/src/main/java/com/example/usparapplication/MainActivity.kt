
package com.example.usparapplication

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
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
        val listView: ListView = findViewById(R.id.listView)
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val searchButton = findViewById<Button>(R.id.searchButton)

        val data = ArrayList<String>() // Veri listesi tanımlandı

        btnConnect.setOnClickListener {
            val c = ConSQL()
            connection = c.conclass()

            if (connection != null) {
                try {
                    Log.d("MainActivity", "Bağlantı başarılı.")
                    val sqlStatement = "SELECT HT.ID, HT.TAR, HT.TID, TT.TIP AS TIPID1, HT.TIPID2, HT.M3, HT.KAYTAR, HT.EID, HT.CARI " +
                            "FROM INFO_HAVA_TUKETIM HT " +
                            "JOIN INFO_TIP_TANIM TT ON HT.ID = TT.ID"
                    val smt: Statement = connection!!.createStatement()
                    val resultSet: ResultSet = smt.executeQuery(sqlStatement)

                    while (resultSet.next()) {
                        val id = resultSet.getInt("ID")
                        val tar = resultSet.getString("TAR")
                        val tid = resultSet.getString("TID")
                        val tipid1 = resultSet.getString("TIPID1")
                        val tipid2 = resultSet.getString("TIPID2")
                        val m3 = resultSet.getDouble("M3")
                        val kaytar = resultSet.getString("KAYTAR")
                        val eid = resultSet.getString("EID")
                        val cari = resultSet.getString("CARI")

                        val item = "ID: $id, TAR: $tar, TID: $tid, TIPID1: $tipid1, TIPID2: $tipid2, M3: $m3, KAYTAR: $kaytar, EID: $eid, CARI: $cari"
                        data.add(item)
                    }

                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
                    listView.adapter = adapter

                    connection!!.close()
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error: ${e.message}")
                }
            }
        }

        searchButton.setOnClickListener {
            val searchText = searchEditText.text.toString().trim()

            if (searchText.isNotEmpty()) {
                val filteredData = data.filter { item ->
                    item.contains(searchText, ignoreCase = true)
                }.toMutableList()

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredData)
                listView.adapter = adapter
            } else {
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
                listView.adapter = adapter
            }
        }
    }
}
