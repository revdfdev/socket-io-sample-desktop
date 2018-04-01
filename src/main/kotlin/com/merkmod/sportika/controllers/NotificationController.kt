package com.merkmod.sportika.controllers

import com.merkmod.sportika.di.Properties
import com.merkmod.sportika.di.createWebService
import com.merkmod.sportika.service.ApiService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javafx.beans.property.SimpleStringProperty
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import tornadofx.*

class NotificationController() : Controller() {

    val statusProperty = SimpleStringProperty("")

    var status by statusProperty

    val api: ApiService = createWebService(Properties.NOTIFICATION_URL)

    fun sendMessage(userId: String, message: String) {
        runLater { status = "" }
        Observable.defer {
            api.pushNotification(userId, message)
        }.subscribeOn(Schedulers.io())
                .doOnNext {
                    runLater {
                        status = if (it.success == 0) {
                            it.message
                        } else {
                            it.message
                        }
                    }
                }.doOnError {
                    runLater {
                        status = it.message ?: "Unknow error Occured while sending message"
                    }
                }
                .subscribe()
    }
}