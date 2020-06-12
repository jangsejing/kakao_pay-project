package com.jess.kakaopay.repository.datasource

import com.jess.kakaopay.di.DispatcherProvider

interface MainDataSource {
//    suspend fun getMovie(query: String): LiveData<MovieData?>
}

class MainDataSourceImpl(
    private val dispatcher: DispatcherProvider
) : MainDataSource {

//    override suspend fun getMovie(query: String): LiveData<MovieData?> = liveData(dispatcher.io()) {
//        val response = repository.getMovie(query).body()
//        emit(response)
//
//    }
}