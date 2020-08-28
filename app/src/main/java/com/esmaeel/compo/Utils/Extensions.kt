package com.esmaeel.composepalygroundtwo

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity


//fun combineClickListeners(vararg views: View, function: () -> Unit) {
//    views.forEach {
//        it.setOnClickListener {
//            function.invoke()
//        }
//    }
//}
//
//fun TextView.GoneIfEmpty() {
//    print("FUCK" + this.text.toString())
//    if (this.text.toString().isNullOrEmpty())
//        this.Gone()
//}
//
//fun View.Gone() {
//    this.visibility = View.GONE
//}
//
//fun View.Visible() {
//    this.visibility = View.VISIBLE
//}
//
//fun View.Hide() {
//    this.visibility = View.INVISIBLE
//}
//
//fun String.isJson(): Boolean {
//    return this.startsWith("{") || this.startsWith("[")
//}
//
//fun String?.buildProfilePath(base: String?): String {
//    print("$base$this")
//    return "$base$this"
//}
//
//fun doSafely(success: () -> Unit, error: (message: String?) -> Unit) {
//    try {
//        success.invoke()
//    } catch (e: Exception) {
//        error(e.localizedMessage)
//    }
//}
//
//
////fun FragmentActivity.showSnackMessage(message: String?, view: View) =
////    message?.let {
////        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
////            .show()
////    }
////
////fun FragmentActivity.showSnackMessageWithAction(
////    message: String?,
////    view: View,
////    actionButton: String? = "",
////    function: () -> Unit?
////) =
////    message?.let {
////        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
////            .setAction(actionButton) {
////                function.invoke()
////            }
////            .show()
////    }
//
//
//fun FragmentActivity.isNetworkAvailable(): Boolean {
//    if (applicationContext == null) return false
//    val connectivityManager =
//        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//        val capabilities =
//            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//        if (capabilities != null) {
//            when {
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                    return true
//                }
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                    return true
//                }
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
//                    return true
//                }
//            }
//        }
//    } else {
//        val activeNetworkInfo = connectivityManager.activeNetworkInfo
//        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
//            return true
//        }
//    }
//    return false
//}