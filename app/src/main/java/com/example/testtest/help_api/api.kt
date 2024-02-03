package com.example.testtest.help_api

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/*data class Drink(
    @SerializedName("idDrink") val id: String,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strInstructions") val instructions: String?,
    @SerializedName("strIngredient1") val ingredient1: String?,
    @SerializedName("strIngredient2") val ingredient2: String?,
    @SerializedName("strIngredient3") val ingredient3: String?,
    @SerializedName("strIngredient4") val ingredient4: String?,
    @SerializedName("strIngredient5") val ingredient5: String?,
    @SerializedName("strIngredient6") val ingredient6: String?,
    @SerializedName("strIngredient7") val ingredient7: String?,
    @SerializedName("strIngredient8") val ingredient8: String?,
    @SerializedName("strIngredient9") val ingredient9: String?,
    @SerializedName("strIngredient10") val ingredient10: String?,
    @SerializedName("strIngredient11") val ingredient11: String?,
    @SerializedName("strIngredient12") val ingredient12: String?,
    //@SerializedName("strIngredient1") val ingredientsMap: Map<String, String?>?
    //val ingredients: List<String>?
){
    fun getIngredients(): List<String> {
        val ingredients = mutableListOf<String>()
        if (!ingredient1.isNullOrBlank()) ingredients.add(ingredient1)
        if (!ingredient2.isNullOrBlank()) ingredients.add(ingredient2)
        if (!ingredient3.isNullOrBlank()) ingredients.add(ingredient3)
        if (!ingredient4.isNullOrBlank()) ingredients.add(ingredient4)
        if (!ingredient5.isNullOrBlank()) ingredients.add(ingredient5)
        if (!ingredient6.isNullOrBlank()) ingredients.add(ingredient6)
        if (!ingredient7.isNullOrBlank()) ingredients.add(ingredient7)
        if (!ingredient8.isNullOrBlank()) ingredients.add(ingredient8)
        if (!ingredient9.isNullOrBlank()) ingredients.add(ingredient9)
        if (!ingredient10.isNullOrBlank()) ingredients.add(ingredient10)
        if (!ingredient11.isNullOrBlank()) ingredients.add(ingredient11)
        if (!ingredient12.isNullOrBlank()) ingredients.add(ingredient12)
        //val ingredients = ingredientsMap?.values?.filterNotNull() ?: emptyList()

        return ingredients
    }
}

fun parseDrink(jsonResponse: String): Drink {
    val jsonObject = JSONObject(jsonResponse)
    val id = jsonObject.getString("idDrink")
    val name = jsonObject.getString("strDrink")
    val instructions = jsonObject.getString("strInstructions")

    // Extract ingredients dynamically
    val ingredients = mutableListOf<String>()
    for (i in 1..15) {
        val ingredient = jsonObject.optString("strIngredient$i")
        if (ingredient.isNotBlank()) {
            ingredients.add(ingredient)
        }
    }

    return Drink(id, name, instructions, ingredients)
}

class ApiViewModel(private val api: Api) : ViewModel() {
    val cocktailState: MutableState<List<Drink>> = mutableStateOf(emptyList())
    @OptIn(DelicateCoroutinesApi::class)
    fun fetchRandomCocktail() {
        GlobalScope.launch(Dispatchers.IO) {
            api.getRandomCocktail(object : Api.NetworkCallback {
                override fun onSuccess(response: List<Drink>) { // Corrected signature
                    cocktailState.value = response
                }
                override fun onFailure(error: String) {
                    // Handle failure
                }
            })
        }
    }
}

data class DrinksResponse(val drinks: List<Drink>)

class ViewModelFactory(private val api: Api) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiViewModel::class.java)) {
            return ApiViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class Api {

    private val client = OkHttpClient()
    interface NetworkCallback {
        fun onSuccess(response: List<Drink>)
        fun onFailure(error: String)
    }
    fun getRandomCocktail(callback: NetworkCallback) {
        val request = Request.Builder()
            .url("https://the-cocktail-db.p.rapidapi.com/random.php")
            .get()
            .addHeader("X-RapidAPI-Key", "250f3ee10dmsh50cfd7fdace17b0p1e38c8jsn6fa37784bc5c")
            .addHeader("X-RapidAPI-Host", "the-cocktail-db.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback.onFailure(e.message ?: "Unknown error")
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                val responseBody = response.body?.string()
                if (response.isSuccessful && responseBody != null) {
                    val drinks = Gson().fromJson(responseBody, DrinksResponse::class.java)
                    callback.onSuccess(drinks.drinks)
                } else {
                    callback.onFailure("Request failed with code ${response.code}")
                }
                response.close()
            }
        })
    }
}

 */