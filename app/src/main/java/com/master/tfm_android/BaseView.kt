package com.master.tfm_android

interface BaseView<T> {
    fun setPresenter(presenter: T)
    fun showError(error: String)
}