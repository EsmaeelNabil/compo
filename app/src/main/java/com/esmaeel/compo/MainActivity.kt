package com.esmaeel.compo


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.setContent
import com.esmaeel.compo.ui.HomePage.HomeScreen
import com.esmaeel.compo.ui.HomePage.HomeViewModel
import com.esmaeel.compo.res.CompoTheme
import kotlinx.coroutines.InternalCoroutinesApi


class MainActivity : AppCompatActivity() {


    private val viewModel by viewModels<HomeViewModel>()

    @InternalCoroutinesApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompoTheme {
                // home screen take the viewModel to observe and be able to get the data again
                HomeScreen(viewModel)
            }
        }

        // do the first call
        viewModel.fetchPersons()

    }
}



