package com.example.usparapplication
import android.annotation.SuppressLint
import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager


class ConSQL {
    private var con: Connection? = null
    private lateinit var connectURL: String

    @SuppressLint("NewApi")
    fun conclass(): Connection? {
        val ip = "192.168.1.141"//DESKTOP-2JP7ACI\SQLEXPRESS2 isimle girince olmuyor
        val port = "1433"
        val db = "ARAKNE"
        val username = "sa"
        val password = "password1"

        val a: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(a)
        println("a ifadesi : ${a}")
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connectURL = "jdbc:jtds:sqlserver://$ip:$port;databaseName=$db;user=$username;password=$password;"
            con = DriverManager.getConnection(connectURL)
            println("url  : ${connectURL}")
        } catch (e: Exception) {
            Log.e("ConSQL", "Error: ${e.message}")
        }
        println("url : ${connectURL} burdaaaa")
        println("con : ${con}")
        return con
    }
}
