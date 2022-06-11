package com.kproject.programmingjokes.domain.provider

interface StringResourceProvider {
    fun getString(stringResId: Int, formatArgs: Array<Any>? = null): String
}