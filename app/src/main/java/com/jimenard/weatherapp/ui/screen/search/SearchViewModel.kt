package com.jimenard.weatherapp.ui.screen.search

import android.view.View
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimenard.weatherapp.data.network.api.NetworkHandler
import com.jimenard.weatherapp.domain.SearchWeatherUseCase
import com.jimenard.weatherapp.domain.model.LocationUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val EMPTY_STRING = ""

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchWeatherUseCase) :
    ViewModel() {

    var text = mutableStateOf(EMPTY_STRING)
    var errorMessage = mutableStateOf(EMPTY_STRING)
    val locationsList = mutableStateOf<List<LocationUi>>(emptyList())
    val loader = mutableIntStateOf(View.GONE)

    fun searchByWord(textToSearch: String) {
        text.value = textToSearch
        loader.intValue = View.VISIBLE
        viewModelScope.launch {
            searchUseCase.searchLocation(wordToSearch = textToSearch).collect { locations ->
                when (locations) {
                    is NetworkHandler.Error -> {
                        loader.intValue = View.GONE
                        locationsList.value = emptyList()
                        errorMessage.value = locations.message.orEmpty()
                    }

                    NetworkHandler.Loading -> {
                        errorMessage.value = EMPTY_STRING
                        locationsList.value = emptyList()
                        loader.intValue = View.VISIBLE
                    }

                    is NetworkHandler.Success -> {
                        errorMessage.value = EMPTY_STRING
                        loader.intValue = View.GONE
                        locationsList.value = locations.data.orEmpty()
                    }
                }
            }
        }
    }
}
