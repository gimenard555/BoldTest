package com.jimenard.weatherapp.ui.screen.locationDetail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jimenard.weatherapp.ui.screen.ContentWithLoader
import com.jimenard.weatherapp.ui.screen.ErrorBottomSheet
import com.jimenard.weatherapp.ui.theme.WeatherAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocationDetailScreen(
    navController: NavController,
    locationName: String,
    viewModel: LocationDetailViewModel = hiltViewModel()
) {
    val paddingValues = WindowInsets.systemBars.asPaddingValues()
    val bottomsSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true,
        )
    viewModel.getLocationDays(locationName)
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
            if (viewModel.errorMessage.value.isNotEmpty()) {
                LaunchedEffect(key1 = null) {
                    bottomsSheetState.show()
                }
            }
            Scaffold(
                backgroundColor = Color.White,
                topBar = {
                    TopAppBar(
                        modifier = Modifier.padding(paddingValues.calculateTopPadding()),
                        title = {
                            Text("Location Detail")
                        },
                        navigationIcon = if (navController.previousBackStackEntry != null) {
                            {
                                IconButton(onClick = { navController.navigateUp() }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        } else {
                            null
                        }
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
                                text = viewModel.placeDetail.value?.location?.name.orEmpty()
                                    .uppercase(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                textAlign = TextAlign.Center,
                                color = Color.DarkGray
                            )
                            viewModel.placeDetail.value?.days?.map { forecast ->
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .wrapContentHeight()
                                ) {
                                    ForecastItem(forecast)
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}