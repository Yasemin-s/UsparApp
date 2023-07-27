package com.example.usparapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.util.Log
import android.widget.Toast
import java.sql.Connection
import java.sql.Statement

class MainActivity : AppCompatActivity() {
    // Connection tipinde bir değişken tanımlıyoruz
    var connection: Connection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  //xml dosyasını baglar ve ekrnda gosteririr

        // Button ve TextView elemanlarını XML dosyasındaki id'leri ile eşleştiriyoruz
        val btnConnect = findViewById<Button>(R.id.button)  //xml dosyasindakş buttın adli elemani degiskene atadim
        val name = findViewById<TextView>(R.id.textView)

        // Button'a tıklama olayını dinlemek için setOnClickListener kullanıyoruz
        btnConnect.setOnClickListener {
            Toast.makeText(
                baseContext,
                "butona tıklandı.",
                Toast.LENGTH_SHORT,
            ).show()
            val c = ConSQL()    //ConSQL sinifindan nesne olusturur.
            connection = c.conClass()   //veritabani baglantisini cagirir.
            if (connection != null) {
                try {
                    val sqlStatement = "Select * from StudentInfo_Tab"  //vt de calisacaak sql sorgusu
                    val smt: Statement = connection!!.createStatement() //statik sql ifadelerini çağırmada kullanır.vt baglantisi ustunden Statement olusturur.Kısacası, Statement nesnesi veritabanında statik SQL ifadelerini çalıştırmak için kullanılır ve executeQuery() metodu ile sorguların sonuçları elde edilebilir.
                    val set = smt.executeQuery(sqlStatement)    //sql sorgusu çalıştırılır ve tablodaki veriler çekilir.
                    while (set.next()) {    //her 1 satiri gezerek islem yapar ve textview e yazdirir.
                        name.text = set.getString(2)
                        Toast.makeText(
                            baseContext,
                            "mesajjj ${name.text} .",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                    connection!!.close()    //vt baglantisi kapatilir.
                } catch (e: Exception) {
                    Toast.makeText(
                        baseContext,
                        "baglantııda sorun oluştur catchh.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    Log.e("Error is", "Error occurred: ${e.message}")
                }
                finally{
                    Toast.makeText(
                        baseContext,
                        "baglantııda sorun oluştur finally.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    connection!!.close()    //vt baglantisi kapatilir.
                }
            }
        }
    }
}
