package com.kproject.programmingjokes.infrastructure.provider

import android.content.Context
import com.kproject.programmingjokes.domain.provider.StringResourceProvider

class StringResourceProviderImpl(private val context: Context) : StringResourceProvider {

    override fun getString(stringResId: Int, formatArgs: Array<Any>?): String {
        if (formatArgs != null) {
            return context.resources.getString(stringResId, formatArgs)
        }
        return context.resources.getString(stringResId)
    }
}