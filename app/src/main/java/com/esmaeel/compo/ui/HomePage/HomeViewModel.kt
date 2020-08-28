package com.esmaeel.compo.ui.HomePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esmaeel.compo.data.models.PopularPersonsResponse
import com.esmaeel.composepalygroundtwo.Contract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var _personsData: MutableLiveData<Contract<PopularPersonsResponse?>> = MutableLiveData()

    val personsData: LiveData<Contract<PopularPersonsResponse?>>
        get() = _personsData


    @InternalCoroutinesApi
    fun fetchPersons(page: Int = 1) {
        viewModelScope.launch {
            getPersonsData(page)
                .flowOn(Dispatchers.IO)
                .collect {
                    _personsData.value = it
                }

        }
    }

}