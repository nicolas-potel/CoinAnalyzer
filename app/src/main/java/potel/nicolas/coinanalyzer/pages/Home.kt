package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import potel.nicolas.coinanalyzer.components.SectionTitle

@Composable
fun HomePage(navController: NavHostController) {
    Column {
        SectionTitle("Home page")
        Button(onClick = { navController.navigate("favorites") }) {
            Text("Go to favorites page")
        }
    }
}