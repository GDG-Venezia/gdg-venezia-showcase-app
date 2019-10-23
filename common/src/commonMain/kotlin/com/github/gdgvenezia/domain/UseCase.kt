package com.github.gdgvenezia.domain

interface UseCase<T, U> {

    suspend fun execute(params: T) : Result<U>
}
