package com.example.statehoistingpreperations.derivedstates

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

data class Product(val name: String, val price: Double)

@Composable
fun ProductList(
    products: List<Product>,
    searchQuery: String,
    modifier: Modifier = Modifier
) {
    // Derived state to filter products based on the search query
    val filteredProducts by remember(products, searchQuery) {
        derivedStateOf {
            if (searchQuery.isEmpty()) {
                products
            } else {
                products.filter { it.name.contains(searchQuery, ignoreCase = true) }
            }
        }

    }

    Log.d("Recomp","ListGenerated")
    LazyColumn(modifier) {
        items(filteredProducts) { product ->
            ProductItem(product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    // Customize the UI for each product item
    Column {
        Text(text = product.name)
        Text(text = "${product.price}")
    }
}

@Composable
fun ProductSearchScreen() {
    var searchQuery by remember { mutableStateOf("") }

    var counter by remember {
        mutableStateOf(0)
    }

    val products = listOf(
        Product("Laptop", 999.99),
        Product("Smartphone", 499.99),
        Product("Tablet", 299.99),
        Product("Smartwatch", 199.99)
    )

    Column {
        Button(onClick = { counter++ }) {
            Text(text = "$counter Click")
        }
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") }
        )
        ProductList(products = products, searchQuery = searchQuery)
    }
}