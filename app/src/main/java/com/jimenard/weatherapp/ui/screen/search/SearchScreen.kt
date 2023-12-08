package com.jimenard.weatherapp.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jimenard.weatherapp.ui.screen.ContentWithLoader
import com.jimenard.weatherapp.ui.screen.ErrorBottomSheet
import com.jimenard.weatherapp.ui.theme.WeatherAppTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel()) {
    val paddingValues = WindowInsets.systemBars.asPaddingValues()
    val focusRequest = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val bottomsSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true,
        )
    WeatherAppTheme {
        ModalBottomSheetLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            sheetContentColor = Color.White,
            sheetBackgroundColor = Color.Transparent,
            sheetState = bottomsSheetState,
            sheetContent = { ErrorBottomSheet(viewModel.errorMessage.value) },
        ) {
            if (viewModel.errorMessage.value.isNotEmpty() && viewModel.text.value.length > 3) {
                LaunchedEffect(key1 = null) {
                    keyboardController?.hide()
                    bottomsSheetState.show()
                }
            }

            Scaffold(
                backgroundColor = Color.White,
                topBar = {
                    TopAppBar(
                        modifier = Modifier.padding(paddingValues.calculateTopPadding()),
                        title = {
                            Text("Search Screen")
                        },
                    )
                },
            ) {
                ContentWithLoader(
                    modifier = Modifier.padding(it),
                    loaderState = viewModel.loader,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .background(Color.White),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                        ) {
                            Text(
                                text = "Enter here your location name",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                color = DarkGray
                            )
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .focusRequester(focusRequest)
                                    .onGloballyPositioned { focusRequest.requestFocus() },
                                value = viewModel.text.value,
                                onValueChange = viewModel::searchByWord,
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Blue
                                ),
                                placeholder = { Text("Please enter a unless 3 letter to start") }
                            )
                            Text(
                                text = viewModel.errorMessage.value,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                textAlign = TextAlign.End,
                                color = Red
                            )
                            viewModel.locationsList.value.map { location ->
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .wrapContentHeight()
                                        .clickable() {
                                            navController.navigate("LocationDetailScreen/${location.region}")
                                        },
                                ) {
                                    LocationItemList(location = location)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
