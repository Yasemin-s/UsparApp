package com.example.usparapplication
import android.annotation.SuppressLint
import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager


class ConSQL {  // Veritabanı bağlantısı için Connection nesnesini tutacak değişken.
    private var con: Connection? = null
    private lateinit var connectURL: String


    @SuppressLint("NewApi")
    fun conclass(): Connection? {   // Bağlantıyı oluşturan fonksiyon.
        val ip = "192.168.2.210"//DESKTOP-2JP7ACI\SQLEXPRESS2 isimle girince olmuyor
        val port = "1433"
        val db = "ARAKNE"
        val username = "Sa"
        val password = "Sa782455"

        val a: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()  // Bu bölümde, ThreadPolicy izinleri vererek ağ işlemlerini ana iş parçacığından yapma izni veriyor.
        StrictMode.setThreadPolicy(a)
        println("a ifadesi : ${a}")
        try {
            // SQL Server JDBC sürücüsünü yükler.
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connectURL = "jdbc:jtds:sqlserver://$ip:$port;databaseName=$db;user=$username;password=$password;"  // Veritabanı bağlantı dizesini oluşturur.
            con = DriverManager.getConnection(connectURL)   // Bağlantıyı oluşturur.
            println("url  : ${connectURL}") // Bağlantı dizesini loglar.
        } catch (e: Exception) {
            Log.e("ConSQL", "Error: ${e.message}")
        }
        println("url : ${connectURL} burdaaaa")
        println("con : ${con}") // Bağlantı nesnesini döndürür.
        return con
    }
}
