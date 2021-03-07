package com.example.sunking_greenlight_assignment.base

abstract class BaseListDataHolder<T> constructor(var data: T) {
    abstract fun onItemClick()
}