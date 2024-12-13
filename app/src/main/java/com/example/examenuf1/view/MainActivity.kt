package com.example.examenuf1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
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

class SharedViewModel : ViewModel() {
    var name by mutableStateOf("")
    var images by mutableStateOf<Int?>(null)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examenuf1Theme {
                val navController = rememberNavController()
                val sharedViewModel = viewModel<SharedViewModel>() // Instancia compartida del ViewModel

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
                            Pantalla1Screen(navController, viewModel(), sharedViewModel)
                        }
                        composable(Routes.Pantalla2.route) {
                            Pantalla2Screen(navController, sharedViewModel) // ViewModel compartido
                        }
                        composable(Routes.Pantalla3.route) {
                            Pantalla3Screen(navController, sharedViewModel) // ViewModel compartido
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
        Image(
            painter = painterResource(id = R.drawable.dragonball_daima_logo),
            contentDescription = "Dragon logo",
            modifier = Modifier
                .size(600.dp)
                .padding(bottom = 5.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = {
            navController.navigate(Routes.Pantalla1.route)
        }) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
fun Pantalla1Screen(navController: NavController, pantalla1ViewModel: Pantalla1ViewModel, sharedViewModel: SharedViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.dragonball_daima_logo),
            contentDescription = "Dragon logo",
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = 5.dp)
                .align(Alignment.CenterHorizontally) // Centrado horizontalmente
        )

        val images = listOf(
            R.drawable.goku,
            R.drawable.gomah,
            R.drawable.masked_majin,
            R.drawable.piccolo,
            R.drawable.supreme,
            R.drawable.vegeta
        )

        val selectedImage = remember { mutableStateOf<Int?>(null) }

        Column(
            modifier = Modifier
                .weight(1f) // Ocupa todo el espacio disponible
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Selecciona una imagen",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3), // Tres columnas
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(images) { image ->
                    val isSelected = selectedImage.value == image

                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(4.dp)
                            .animateContentSize() // Añade animación
                            .let {
                                if (isSelected) it.size(120.dp) else it // Agranda si está seleccionada
                            }
                            .clickable {
                                selectedImage.value = if (isSelected) null else image
                            }
                            .then(if (isSelected) Modifier.border(2.dp, MaterialTheme.colorScheme.primary) else Modifier)
                    )
                }
            }
        }

        Button(
            onClick = {
                sharedViewModel.images = selectedImage.value // Guarda la imagen seleccionada
                navController.navigate(Routes.Pantalla2.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Continuar")
        }
    }
}

@Composable
fun Pantalla2Screen(navController: NavController, sharedViewModel: SharedViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.dragonball_daima_logo),
            contentDescription = "Dragon logo",
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = 5.dp)
        )

        var inputName by remember { mutableStateOf("") }

        Text(
            text = "Ingresa tu nombre:",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        TextField(
            value = inputName,
            onValueChange = { inputName = it },
            placeholder = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                sharedViewModel.name = inputName
                navController.navigate(Routes.Pantalla3.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Continuar")
        }
    }
}

@Composable
fun Pantalla3Screen(navController: NavController, sharedViewModel: SharedViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.dragonball_daima_logo),
                contentDescription = "Dragon logo",
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = 5.dp)
            )

            sharedViewModel.images?.let { imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Imagen seleccionada",
                    modifier = Modifier
                        .size(350.dp)
                        .padding(16.dp)
                )
            }

            Text(
                text = "${sharedViewModel.name}!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.popBackStack(Routes.Pantalla1.route, false)
            }) {
                Text("Regresar a Selección")
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name!",
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
