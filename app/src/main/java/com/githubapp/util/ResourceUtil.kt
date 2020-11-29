package com.githubapp.util

import android.app.Application
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceUtil @Inject constructor(private val application: Application) {

    fun getString(resource: Int): String {
        return application.resources.getString(resource)
    }

    fun getString(resource: Int, vararg formatArgs: Any?): String {
        return application.resources.getString(resource, *formatArgs)
    }

    fun getColor(resource: Int): Int {
        return if (resource <= 0) {
            0
        } else ContextCompat.getColor(application, resource)
    }

    fun getDrawable(resource: Int): Drawable? {
        return ContextCompat.getDrawable(application, resource)
    }

    fun getFont(resource: Int): Typeface? {
        return ResourcesCompat.getFont(application, resource)
    }

    fun getDimension(resource: Int): Int {
        return application.resources.getDimensionPixelSize(resource)
    }

}