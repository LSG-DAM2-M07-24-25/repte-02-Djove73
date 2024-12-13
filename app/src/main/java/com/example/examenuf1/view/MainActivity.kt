package com.example.examenuf1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenuf1.ui.theme.Examenuf1Theme
import com.example.examenuf1.ui.viewmodel.MainViewModel
import com.example.examenuf1.ui.viewmodel.Pantalla1ViewModel
import com.example.examenuf1.ui.viewmodel.Pantalla2ViewModel
import com.example.examenuf1.ui.viewmodel.Pantalla3ViewModel
import com.example.examenuf1.util.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examenuf1Theme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.MainActivity.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.MainActivity.route) {
                            MainScreen(navController, viewModel())
                        }
                        composable(Routes.Pantalla1.route) {
                            Pantalla1Screen(navController, viewModel())
                        }
                        composable(Routes.Pantalla2.route) {
                            Pantalla2Screen(navController, viewModel())
                        }
                        composable(Routes.Pantalla3.route) {
                            Pantalla3Screen(navController, viewModel())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting(name = "Android")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(Routes.Pantalla1.route)
        }) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Routes.Pantalla3.route)
        }) {
            Text("Ir a Pantalla 3")
        }
    }
}

@Composable
fun Pantalla1Screen(navController: NavController, pantalla1ViewModel: Pantalla1ViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenido a Pantalla 1")

        @Composable
        fun Pantalla2Screen(navController: NavController, pantalla2ViewModel: Pantalla2ViewModel) {
            val images = listOf(
                R.drawable.goku,
                R.drawable.gomah,
                R.drawable.masked_majin,
                R.drawable.piccolo,
                R.drawable.supreme,
                R.drawable.vegeta
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(images) { image ->
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(Routes.Pantalla2.route)
        }) {
            Text("Continuar")
        }
    }
}

@Composable
fun Pantalla2Screen(navController: NavController, pantalla2ViewModel: Pantalla2ViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Bienvenido a Pantalla 2")



            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.popBackStack(Routes.MainActivity.route, false)
            }) {
                Text("Regresar a MainActivity")
            }
        }
    }
}

@Composable
fun Pantalla3Screen(navController: NavController, pantalla3ViewModel: Pantalla3ViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Bienvenido a Pantalla 3")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.popBackStack(Routes.MainActivity.route, false)
            }) {
                Text("Regresar a MainActivity")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Examenuf1Theme {
        Greeting("Android")
    }
}
