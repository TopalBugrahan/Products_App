package com.example.bugrahan_topal_odev6

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bugrahan_topal_odev6.configs.ApiClient
import com.example.bugrahan_topal_odev6.models.User
import com.example.bugrahan_topal_odev6.models.UserData
import com.example.bugrahan_topal_odev6.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var kullaniciAdi:EditText
    lateinit var password:EditText
    lateinit var btnLogin:Button
    lateinit var dummyService:DummyService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kullaniciAdi=findViewById(R.id.kullaniciAdi)
        password=findViewById(R.id.password)
        btnLogin=findViewById(R.id.btnLogin)

        dummyService=ApiClient.getClient().create(DummyService::class.java)
        btnLogin.setOnClickListener(btnLoginOnClickListener)


    }

    val btnLoginOnClickListener= View.OnClickListener{
        val usernameText=kullaniciAdi.text.toString()
        val passwordText=password.text.toString()

        if(usernameText==""||passwordText==""){
            val toast = Toast.makeText(applicationContext, "Lütfen Kullanıcı Adı veya Şifreyi Boş Bırakmayın", Toast.LENGTH_LONG)
            toast.show()
            Log.d("hata","Lütfen boş giriş yapmayın")
        }
        else {
            val user= User(usernameText,passwordText)
            dummyService.login(user).enqueue(object:Callback<UserData>{
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                   Log.d("response",response.body().toString())
                    if(response.body()==null){
                        val toast = Toast.makeText(applicationContext, "Kullanıcı Adı veya Şifreniz Yanlış", Toast.LENGTH_LONG)
                        toast.show()
                    }
                    else{
                        val intent = Intent(this@MainActivity, ProductPage::class.java)
                        startActivity(intent)
                        finish();
                    }

                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Log.e("hata","server Hatası")
                }

            })
        }
    }
}