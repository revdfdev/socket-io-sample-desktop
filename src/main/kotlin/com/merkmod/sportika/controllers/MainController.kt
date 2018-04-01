package com.merkmod.sportika.controllers

import com.merkmod.sportika.di.Properties
import com.merkmod.sportika.di.createWebService
import com.merkmod.sportika.service.ApiService
import com.merkmod.sportika.view.NotificationView
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javafx.beans.property.SimpleStringProperty
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import tornadofx.*

class MainController : Controller(), KoinComponent {

    val statusProperty = SimpleStringProperty("")

    var status by statusProperty

    val apiService: ApiService = createWebService(Properties.NOTIFICATION_URL)

    fun registerUser(userId: String?, connectionId: String?, view: View) {
        runLater { status = "" }
        Observable.defer {
            apiService.registerUser(userId ?: "", connectionId ?: "")
        }.subscribeOn(Schedulers.io())
                .doOnNext {
                    runLater {
                        status = if (it.success == 0) {
                            it.message
                        } else {
                            it.message
                        }
                        view.replaceWith(NotificationView::class)
                    }
                }
                .doOnError {

                    runLater {
                        status = it.message ?: "Login failed"
                    }
                }
                .subscribe()
    }

}