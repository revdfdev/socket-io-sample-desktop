package com.merkmod.sportika.app

import com.merkmod.sportika.view.MainView
import javafx.application.Application
import tornadofx.App

fun main(args: Array<String>) {
    Application.launch(MyApp::class.java, *args)
}

class MyApp: App(MainView::class, Styles::class)