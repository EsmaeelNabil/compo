package com.esmaeel.compo.ui.HomePage

import com.esmaeel.composepalygroundtwo.Contract
import com.esmaeel.composepalygroundtwo.getService
import kotlinx.coroutines.flow.flow

fun getPersonsData(pageNumber: Int = 0) = flow {
    emit(Contract.onLoading(data = null))
    try {
        val response = getService().getPopularPersons(pageNumber = pageNumber)

        if (response.isSuccessful) {
            emit(Contract.onSuccess(data = response.body()))
        } else {
            response.errorBody()?.let { errorBody ->
                emit(
                    Contract.onError(
                        data = null,
                        message = "MyUtils.getErrorFromBody(errorBody)"
                    )
                )
            }
        }
    } catch (a: Exception) {
        a.localizedMessage?.let {
            emit(
                Contract.onError(
                    data = null,
                    message = it
                )
            )
        }
    }

}