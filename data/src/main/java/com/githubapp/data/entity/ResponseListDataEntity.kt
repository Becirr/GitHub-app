package com.githubapp.data.entity

class ResponseListDataEntity<T> {
    var items: List<T>? = null
        private set

    fun setData(items: List<T>) {
        this.items = items
    }
}