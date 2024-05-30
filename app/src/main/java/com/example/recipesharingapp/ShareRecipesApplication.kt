package com.example.recipesharingapp

import android.app.Application
import com.example.recipesharingapp.model.AppContainer
import com.example.recipesharingapp.model.AppDataContainer

class ShareRecipesApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}