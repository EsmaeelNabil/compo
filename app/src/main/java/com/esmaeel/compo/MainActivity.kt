package com.esmaeel.compo


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.ui.tooling.preview.Preview
import com.esmaeel.compo.data.models.PopularPersonsResponse
import com.esmaeel.compo.ui.HomePage.HomeScreen
import com.esmaeel.compo.ui.HomePage.HomeViewModel
import com.esmaeel.compo.ui.res.CompoTheme
import com.esmaeel.composepalygroundtwo.Contract
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import kotlinx.coroutines.InternalCoroutinesApi


class MainActivity : AppCompatActivity() {


    val viewModel by viewModels<HomeViewModel>()

    @InternalCoroutinesApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompoTheme {
                HomeScreen(viewModel)
            }
        }

        viewModel.fetchPersons()

    }
}


@Composable
fun Greeting(name: String) {
    Column {
        Text(text = "Hello $name!")
        CoilImageWithCrossfade(
            data = "https://images.unsplash.com/photo-1580478654182-8bd34b9c0b8d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
            modifier = Modifier.size(100.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompoTheme {
        Greeting("Android")
    }
}
