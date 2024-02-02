package com.example.testtest.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(listOf(Color.Magenta, Color.Cyan)))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(text = "Drink Recipes")
            HorizontalDivider(thickness = 2.dp, color = Color.Black)



            if (cocktailState.isNotEmpty()) {
                // Display the list of drinks
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Cocktails:")
                    cocktailState.forEach { drink ->
                        Text(text = drink.name)
                        Text(text = drink.instructions)
                        // Add more details as needed
                    }
                }
            }
            Button(
                onClick = { apiViewModel.fetchRandomCocktail() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Fetch Random Cocktail")
            }
        }
    }
}

data class Drink(
    @SerializedName("idDrink") val id: String,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strInstructions") val instructions: String
    // Add more properties as needed
)

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




