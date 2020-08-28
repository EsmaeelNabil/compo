package com.esmaeel.composepalygroundtwo



enum class Statuss {
    SUCCESS,
    ERROR,
    LOADING,
    IDLE
}



class PageStateContract<T>(
    var status: Statuss = Statuss.IDLE /*Enum to define if i should read from data or from message*/,
    var dataa: T? = null/*same*/,
    var messagee: String? = ""
) {

    fun onSuccess(data: T?) {
        status = Statuss.SUCCESS
        dataa = data
        messagee = null

    }

    fun onError(message: String) {
        status = Statuss.ERROR
        dataa = null
        messagee = message
    }

    fun onLoading() {
        status = Statuss.LOADING
        dataa = null
        messagee = null
    }

}