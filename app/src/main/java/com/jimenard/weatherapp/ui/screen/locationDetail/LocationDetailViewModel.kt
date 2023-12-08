package com.jimenard.weatherapp.ui.screen.locationDetail

import android.view.View
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimenard.weatherapp.data.network.api.NetworkHandler
import com.jimenard.weatherapp.domain.LocationDetailUseCase
import com.jimenard.weatherapp.domain.model.LocationDetailUi
import com.jimenard.weatherapp.ui.screen.search.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(private val searchUseCase: LocationDetailUseCase) :
    ViewModel() {

    var errorMessage = mutableStateOf(EMPTY_STRING)
    val placeDetail = mutableStateOf<LocationDetailUi?>(null)
    val loader = mutableIntStateOf(View.GONE)

    fun getLocationDays(currentPlace: String) {
        viewModelScope.launch {
            searchUseCase.getLocationDetail(currentPlace).collect { detail ->
                when (detail) {
                    is NetworkHandler.Error -> {
                        loader.intValue = View.GONE
                        placeDetail.value = null
                        errorMessage.value = detail.message.orEmpty()
                    }

                    NetworkHandler.Loading -> {
                        errorMessage.value = EMPTY_STRING
                        placeDetail.value = null
                        loader.intValue = View.VISIBLE
                    }

                    is NetworkHandler.Success -> {
                        errorMessage.value = EMPTY_STRING
                        loader.intValue = View.GONE
                        placeDetail.value = detail.data
                    }
                }
            }
        }
    }
}