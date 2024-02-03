package com.example.testtest.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testtest.help_api.Header
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DrinkScreen(apiViewModel: ApiViewModel = viewModel(factory = ViewModelFactory(Api()))) {
    val cocktailState = apiViewModel.cocktailState.value
    var textHeader by remember { mutableStateOf("Cocktail recipe") }
    fun action1() {
        apiViewModel.fetchRandomCocktail()
    }
    fun action2() {
        textHeader = "Enjoy your Cocktail"
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.horizontalGradient(listOf(Color.Cyan, Color.Magenta)))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(text = textHeader)
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            Spacer(modifier = Modifier.height(20.dp))

            if (cocktailState.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    cocktailState.forEach { drink ->
                        Text(
                            text = drink.name,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                fontFamily = FontFamily.Serif,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                drink.getIngredients().forEach { ingredient ->
                                    Text(
                                        text = "- $ingredient",
                                        style = TextStyle(
                                            fontSize = 18.sp
                                        )
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))

                            Column {
                                drink.getMeasures().forEach { measure ->
                                    Text(
                                        text = "- $measure",
                                        style = TextStyle(
                                            fontSize = 20.sp
                                        )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Instructions:",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline,
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Italic,
                                fontFamily = FontFamily.Serif,
                            ),
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        drink.instructions?.let { Text(
                            text = it,
                            style = TextStyle(
                                fontSize = 20.sp,

                            )
                        ) }
                        /*if (drink.ingredients != null) {
                            Text(text = "Ingredients:")
                            drink.ingredients.forEach { ingredient ->
                                Text(text = "- $ingredient")
                            }
                        }*/
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(1f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ElevatedButton(
                    onClick = {
                        action1()
                        action2()
                              },
                    modifier = Modifier.padding(36.dp),
                        colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(
                        text = "Get Random Recipe",
                        style = TextStyle(
                        color = Color.Magenta,
                            fontSize = 24.sp
                        )
                    )
                }
            }
        }
    }
}

data class Drink(
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
    @SerializedName("strMeasure1") val measure1: String?,
    @SerializedName("strMeasure2") val measure2: String?,
    @SerializedName("strMeasure3") val measure3: String?,
    @SerializedName("strMeasure4") val measure4: String?,
    @SerializedName("strMeasure5") val measure5: String?,
    @SerializedName("strMeasure6") val measure6: String?,
    @SerializedName("strMeasure7") val measure7: String?,
    @SerializedName("strMeasure8") val measure8: String?,
    @SerializedName("strMeasure9") val measure9: String?,
    @SerializedName("strMeasure10") val measure10: String?,
    @SerializedName("strMeasure11") val measure11: String?,
    @SerializedName("strMeasure12") val measure12: String?,

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

    fun getMeasures(): List<String> {
        val measures = mutableListOf<String>()
        if (!measure1.isNullOrBlank()) measures.add(measure1)
        if (!measure2.isNullOrBlank()) measures.add(measure2)
        if (!measure3.isNullOrBlank()) measures.add(measure3)
        if (!measure4.isNullOrBlank()) measures.add(measure4)
        if (!measure5.isNullOrBlank()) measures.add(measure5)
        if (!measure6.isNullOrBlank()) measures.add(measure6)
        if (!measure7.isNullOrBlank()) measures.add(measure7)
        if (!measure8.isNullOrBlank()) measures.add(measure8)
        if (!measure9.isNullOrBlank()) measures.add(measure9)
        if (!measure10.isNullOrBlank()) measures.add(measure10)
        if (!measure11.isNullOrBlank()) measures.add(measure11)
        if (!measure12.isNullOrBlank()) measures.add(measure12)

        return measures
    }
}

/*fun parseDrink(jsonResponse: String): Drink {
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
}*/

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
            .addHeader("X-RapidAPI-Key", "MY API KEY")
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


@Preview(showBackground = true)
@Composable
fun DrinkScreenPreview() {
    val apiViewModel = remember { ApiViewModel(api = Api()) }
    DrinkScreen(apiViewModel = apiViewModel)
}


