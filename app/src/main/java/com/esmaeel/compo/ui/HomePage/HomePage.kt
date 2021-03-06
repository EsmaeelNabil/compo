package com.esmaeel.compo.ui.HomePage

import android.content.Context
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
import androidx.compose.runtime.mutableStateOf
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
import com.esmaeel.compo.ui.customComposables.StaggeredVerticalGrid
import com.esmaeel.compo.res.CompoTheme
import com.esmaeel.compo.res.blueme
import com.esmaeel.compo.res.materialGreen
import com.esmaeel.compo.res.materialRed
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

        var drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
        var pageNumber = remember { 0 }


        Scaffold(
            scaffoldState = ScaffoldState(
                drawerState = drawerState,
                isDrawerGesturesEnabled = true,
                snackbarHostState = SnackbarHostState()
            ),
            floatingActionButtonPosition = FabPosition.End,
/*            topBar = {
                topAppBarUi(
                    onDrawerIconClicked = { drawerState.open {} },
                    onSearchClicked = {
                        Toast.makeText(
                            context,
                            "Search Clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            },*/
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    backgroundColor = Color.Black,
                    text = { Text(text = "Refresh", color = Color.White) },
                    onClick = {
                        // fetch a new page from the API
                        ++pageNumber
                        viewModel.fetchPersons(page = pageNumber)
                    })
            },

            drawerContent = {
                DrawerUi()
            },

            ) {

            HomeScreenContent(
                viewModel,
                context,
                onDrawerIconClicked = { drawerState.open() }
            )

        }

    }
}

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel,
    context: Context,
    onDrawerIconClicked: () -> Unit
) {
    ScrollableColumn {
        // for making the topBarUi on top of the list (like a FramLayout )
        Stack {

            // custom view as a top bar
            topAppBarUi(
                onDrawerIconClicked = { onDrawerIconClicked.invoke() },
                onSearchClicked = {
                    Toast.makeText(
                        context,
                        "Search Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )

            // body content
            // like a viewGroup
            Box(
                modifier = Modifier.fillMaxSize(),
                gravity = ContentGravity.Center
            ) {

                // observe the changes on the data from the viewModel as a state ( for recompositions )
                val apiData by viewModel.personsData.observeAsState()

                // deal with type of states and show different composables based on the scenario.
                when (apiData!!.status) {
                    Status.LOADING -> CircularProgressIndicator(modifier = Modifier.fillMaxSize(),strokeWidth = 20.dp)
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
        shape = RoundedCornerShape(20),
        elevation = 15.dp,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        // Like LinearLayout ( Horizontal )
        Row(verticalGravity = Alignment.CenterVertically) {

            IconButton(onClick = {
                onDrawerIconClicked.invoke()
            }) {
                Icon(asset = Icons.Filled.Dehaze)
            }

            Text(
                "Popular People",
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = { onSearchClicked.invoke() }) {
                Icon(asset = Icons.Filled.ImageSearch)
            }
        }
    }
}


// viewing the item with dark mode enabled
@Preview(showBackground = true)
@Composable
fun appBarPreviewDark() {
    CompoTheme(darkTheme = true) {
        topAppBarUi({}, {})
    }
}


// viewing the item with light mode enabled
@Preview(showBackground = true)
@Composable
fun appBarPreviewLight() {
    CompoTheme(darkTheme = false) {
        topAppBarUi({}, {})
    }
}

@Composable
fun DrawerUi() {
    // TODO: 8/29/20 complete it
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
        // like a RecyclerViewer but we need to handle the state for it properly
//        LazyColumnFor(
//            contentPadding = InnerPadding(top = 70.dp, start = 16.dp, end = 16.dp),
//            items = data.results!!,
//        ) { item ->
//            ListElement(item)
//        }

        // like a scroll view with no recycling or caching the views
//        ScrollableColumn() {
//            for (item in data.results!!) {
//                ListElement(item)
//            }
//        }

        // a custom view for placing the views with a grid view style or lets say a staggeredGridViw ( recyclerview layout manager )
        StaggeredVerticalGrid(
            maxColumnWidth = 300.dp,
            modifier = Modifier.padding(top = 70.dp, start = 16.dp, end = 16.dp, bottom = 70.dp)
        ) {
            for (item in data.results!!) {
                ListElement(item)
            }
        }
    }
}


@Composable
fun ListElement(item: Results? = Results()) {

    // remembering the last selected color for the item for if the item is recomposed again it will remember it's previous state
    var currentColor = remember(item) { mutableStateOf(materialRed) }

    Box(modifier = Modifier.clickable(enabled = true, onClick = {
        currentColor.value = if (currentColor.value == materialRed) materialGreen else materialRed
    })) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 16.dp,
            backgroundColor = currentColor.value,
            modifier = Modifier.padding(10.dp).gravity(Alignment.CenterHorizontally).fillMaxWidth()
        ) {

            Column {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // a library that is made of Coil loading library for android kotlin
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


