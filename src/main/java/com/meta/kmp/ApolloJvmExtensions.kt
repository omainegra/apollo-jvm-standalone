package com.meta.kmp

import com.apollographql.apollo3.api.http.ByteStringHttpBody
import com.apollographql.apollo3.api.http.HttpBody
import okio.Buffer

object ApolloJvmExtensions {
    fun readBodyAsString(body: HttpBody): String =
        Buffer().also { body.writeTo(it) }.readUtf8()

    fun stringBody(contentType: String, string: String): HttpBody =
        ByteStringHttpBody(contentType = contentType, string = string)
}
