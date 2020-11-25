package com.githubapp.domain.model

import java.io.Serializable

class ResponseData<T> : Serializable {
    var data: T? = null
}