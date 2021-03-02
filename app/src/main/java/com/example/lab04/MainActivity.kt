package com.example.lab04
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lab04.pokemon.Pokemon
import com.example.lab04.conexionAPIS.Network
import com.example.lab04.conexionAPIS.Utils
import com.google.gson.Gson
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var tvName:TextView? = null
    var tvExp:TextView? = null
    var tvHeight:TextView? = null
    var tvWeight:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSearch = findViewById<Button>(R.id.botonBusqueda)
        val etName = findViewById<EditText>(R.id.busquedaPokemon)
        tvName = findViewById(R.id.tvName)
        tvExp = findViewById(R.id.tvExp)
        tvHeight = findViewById(R.id.tvHeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSearch.setOnClickListener {
            var checkerS:CheckString = CheckString(etName?.text.toString())
            var flags:Int = 0
            var succes:Boolean = false
            var PokemonBusqueda: String = ""
            if(checkerS.checkChars() == 0){
                fullInfo("", "", "", "")
                flags++
            }
            else if(checkerS.checkChars() >= 50){
                //Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                fullInfo("", "", "", "")
                flags++
            }
            if(checkerS.valNumbers()){
                fullInfo("", "", "", "")
                flags++
            }
            if(checkerS.checkSpace() >= 1){
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                fullInfo("", "", "", "")
                flags++
            }
            if(flags == 0){
                succes = true
                PokemonBusqueda = checkerS.fixString()
            }
            if(succes){
                if(Network.avaliableRed(this)){
                    apiRest(Utils.URL_DEF_BASE + PokemonBusqueda)
                }else{
                }
            }
        }
    }
    private fun apiRest(url:String){
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url, {
            response ->
            try {
                Log.d("requestHTTPVolley", response)

                val gson = Gson()
                val pokemon = gson.fromJson(response, Pokemon::class.java)
                Log.d("GSON", pokemon.name)

                fullInfo(
                        "Tu pokemon encontrado fue: "+pokemon.name ,
                        "Base Experiencia: " + pokemon.base_experience.toString(),
                        "Su altura en metros llega a: " + (pokemon.height.toDouble() / 10).toString() + " metros",
                        "El peso que se registro: " + (pokemon.weight / 10).toString() + " kilogramos"
                )

            }catch (e: Exception){
            }
        }, Response.ErrorListener {
            Toast.makeText(this, "Oops! Parece que no hemos encontrado a tu pokemon!", Toast.LENGTH_SHORT).show()
            fullInfo("Oops! Parece que no hemos encontrado a tu pokemon!", "", "", "")
        })
        queue.add(request)
    }
    private fun fullInfo(name:String, exp:String, height:String, weight:String){
        tvName?.text = name
        //tvExp?.text = exp
        tvHeight?.text = height
        tvWeight?.text = weight
    }

}