package com.example.usparapplication
//2023-07-31 15:23:19.087 3916-3916/com.example.usparapplication E/BufferQueueProducer: Unable to open libpenguin.so: dlopen failed: library "libpenguin.so" not found.
import android.annotation.SuppressLint
import android.os.StrictMode
import java.sql.DriverManager
import java.sql.Connection
import android.util.Log

// Veritabanı bağlantı sınıfı
class ConSQL {
    var con: Connection? = null

    @SuppressLint("NewApi")
    fun conClass(): Connection? {
        val ip = "192.168.1.141"
        val port = "1433"
        val db = "mytestdb"
        val username = "sa"
        val password = "password1"

        // Sıkı kural modunu değiştirerek ağ bağlantısı izin verilir
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Bağlantı URL'sini oluşturmak için kullanılacak değişkenleri tanımlayın
        var connectUrl: String? = null

        try {
            // JDBC sürücüsünü yükleyin
            Class.forName("net.sourceforge.jtds.jdbc.Driver")

            // Bağlantı URL'sini oluşturun
            connectUrl = "jdbc:jtds:sqlserver://$ip:$port;databaseName=$db;user=$username;password=$password;"

            // Bağlantıyı oluşturun
            con = DriverManager.getConnection(connectUrl)
            println("url baglanttisi : ${connectUrl}")
        } catch (e: Exception) {
            // Hata durumunda hata mesajını loglayın
            Log.e("Error is", "Error occurred: ${e.message}")
        }

        // Oluşturulan bağlantıyı döndürün
        return con
    }
}