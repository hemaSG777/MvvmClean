package com.example.mvvmclean.utils

data class Resource<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: Any? = null
)