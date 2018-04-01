package com.merkmod.sportika.view

import com.merkmod.sportika.controllers.MainController
import com.merkmod.sportika.controllers.NotificationController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class MainView : View("Register User") {
    val model = ViewModel()
    val userId =  model.bind { SimpleStringProperty() }
    val connectionId = model.bind { SimpleStringProperty() }
    val mainController: MainController by inject()

    override val root = form {
        fieldset(labelPosition = Orientation.VERTICAL) {
            fieldset("User Id") {
                textfield(userId).required()
            }

            fieldset("Connection Id") {
                textfield (connectionId).required()
            }

            button("Register") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxHeight = true
                action {
                    runAsyncWithProgress {
                        mainController.registerUser(userId.value, connectionId.value, this@MainView)
                    }
                }
            }
        }

        label(mainController.statusProperty) {
            style {
                paddingTop = 10
                textFill = Color.RED
                fontWeight = FontWeight.BOLD
            }
        }
    }
}

class NotificationView: View(title = "Send Notification") {
    val model = ViewModel()
    val notificationMessage = model.bind {SimpleStringProperty()}
    val userId = model.bind { SimpleStringProperty()}
    val notificationController: NotificationController by inject()

    override val root = form {
        fieldset(labelPosition = Orientation.VERTICAL) {
            fieldset("User Id") {
                textfield(userId).required()
            }

            fieldset("Notification Message") {
                textfield(notificationMessage).required()
            }

            button("Send message") {
                enableWhen { model.valid }
                isDefaultButton = true
                useMaxHeight = true
                action {
                    runAsyncWithProgress {
                        notificationController.sendMessage(userId.value, notificationMessage.value)
                    }
                }
            }
        }

        label(notificationController.statusProperty) {
            style {
                paddingTop = 10
                textFill = Color.RED
                fontWeight = FontWeight.BOLD
            }
        }

    }
}