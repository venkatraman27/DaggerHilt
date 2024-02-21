package com.dagger.daggerhilt.data.common

import com.dagger.daggerhilt.data.common.ResponseStatus.ERROR
import com.dagger.daggerhilt.data.common.ResponseStatus.SUCCESS

class AppResponse<T> private constructor(val status: String, val data: T?, val throwable: Throwable?) {

    companion object {

        fun <T> success(data: T): AppResponse<T> {
            return AppResponse(SUCCESS, data, null)
        }

        fun <T> error(error: Throwable): AppResponse<T> {
            return AppResponse(ERROR, null, error)
        }
    }

//    class success<T> (data: T): AppResponse<T>(SUCCESS,data,null)

}