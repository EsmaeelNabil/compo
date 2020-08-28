package com.esmaeel.compo.ui.HomePage

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.esmaeel.compo.data.models.PopularPersonsResponse
import com.esmaeel.compo.data.models.Results
import com.esmaeel.compo.ui.res.CompoTheme
import com.esmaeel.compo.ui.res.blueme
import com.esmaeel.compo.ui.res.materialGreen
import com.esmaeel.compo.ui.res.materialRed
import com.esmaeel.composepalygroundtwo.Status
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    val context = ContextAmbient.current
    Surface(color = MaterialTheme.colors.background) {
        val drawerStat = rememberDrawerState(initialValue = DrawerValue.Closed)

        Scaffold(
            scaffoldState = ScaffoldState(
                drawerState = drawerStat,
                isDrawerGesturesEnabled = true,
                snackbarHostState = SnackbarHostState()
            ),
            topBar = {
                topAppBarUi(
                    onDrawerIconClicked = { drawerStat.isOpen },
                    onSearchClicked = {
                        Toast.makeText(
                            context,
                            "Search Clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            },

            floatingActionButtonPosition = FabPosition.End,

            floatingActionButton = {
                ExtendedFloatingActionButton(
                    backgroundColor = Color.Black,
                    text = { Text(text = "Refresh", color = Color.White) },
                    onClick = { viewModel.fetchPersons() })
            },

            drawerContent = {
                DrawerUi()
            },

            ) {

            Box(
                modifier = Modifier.fillMaxSize(),
                gravity = ContentGravity.Center
            ) {

                val apiData by viewModel.personsData.observeAsState()

                when (apiData!!.status) {
                    Status.LOADING -> CircularProgressIndicator(strokeWidth = 20.dp)
                    Status.SUCCESS -> moviesList(apiData!!.data)
                    Status.ERROR -> ErrorUi(apiData!!.message)
                }

            }


        }

    }
}

@Composable
fun topAppBarUi(onDrawerIconClicked: () -> Unit, onSearchClicked: () -> Unit) {

    Surface(
        shape = RoundedCornerShape(10),
        elevation = 10.dp,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Row(verticalGravity = Alignment.CenterVertically) {
            IconButton(onClick = {
                onDrawerIconClicked()
            }) {
                Icon(asset = Icons.Filled.Dehaze)
            }

            Text(
                "Popular People",
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {}) {
                Icon(asset = Icons.Filled.ImageSearch)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun appBarPreviewDark() {
    CompoTheme(darkTheme = true) {
        topAppBarUi({}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun appBarPreviewLight() {
    CompoTheme(darkTheme = false) {
        topAppBarUi({}, {})
    }
}

@Composable
fun DrawerUi() {
    Text(text = "drawerContent")
}

@Composable
fun ErrorUi(error: String? = "Error") {
    error?.let {
        Box(gravity = ContentGravity.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                text = error,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 20.sp, color = blueme)
            )
        }
    }
}

@Composable
fun moviesList(data: PopularPersonsResponse? = PopularPersonsResponse()) {
    data?.let {
        ScrollableColumn() {
            for (item in data.results!!) {
                ListElement(item)
            }
        }
    }
}


@Composable
fun ListElement(item: Results? = Results()) {

    var currentColor = remember { materialRed }

    Box(modifier = Modifier.clickable(enabled = true, onClick = {
        currentColor = if (currentColor == materialRed) materialGreen else materialRed
    })) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 16.dp,
            backgroundColor = currentColor,
            modifier = Modifier.padding(10.dp).gravity(Alignment.CenterHorizontally).fillMaxWidth()
        ) {

            Column {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CoilImageWithCrossfade(
                        "https://image.tmdb.org/t/p/w185${item!!.profile_path!!}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(250.dp).fillMaxWidth()
                    )
                }

                Text(text = item!!.name!!, fontSize = 22.sp, modifier = Modifier.padding(10.dp))

            }

        }
    }
}
