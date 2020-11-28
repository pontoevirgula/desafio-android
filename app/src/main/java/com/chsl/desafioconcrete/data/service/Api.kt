package com.chsl.desafioconcrete.data.service

import com.chsl.desafioconcrete.data.entity.pullrequests.PullsRequestsResponse
import com.chsl.desafioconcrete.data.entity.repositories.RepositoryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {

    @GET("search/repositories?q=language:Java&sort=stars&")
    fun allRepository(@Query("page") page: Int = 1): Observable<RepositoryResponse>

    @GET("repos/{criador}/{repositorio}/pulls")
    fun allPullsRequests(@Path("criador") creator: String,
                         @Path("repositorio") repository: String): Observable<List<PullsRequestsResponse>>
}