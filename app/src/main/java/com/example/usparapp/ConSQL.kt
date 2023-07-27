/*import java.sql.Connection  //ms sql kullandıgım icin java.sql olan connection sınıfını import ettim
import java.sql.DriverManager
import java.sql.SQLException

class ConSQL {
    fun conClass(): Connection? {
        val ip = "192.168.1.141"
        val port = "1433"
        val db = "mytestdb"
        val username = "sa"
        val password = "password1"

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
            val connectionString = "jdbc:sqlserver://$ip:$port;databaseName=$db;user=$username;password=$password;"
            return DriverManager.getConnection(connectionString)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }
}*/
package com.example.usparapp

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
        } catch (e: Exception) {
            // Hata durumunda hata mesajını loglayın
            Log.e("Error is", "Error occurred: ${e.message}")
        }

        // Oluşturulan bağlantıyı döndürün
        return con
    }
}



//servillance disk