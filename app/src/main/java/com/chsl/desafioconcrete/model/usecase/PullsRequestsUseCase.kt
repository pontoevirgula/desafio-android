package com.chsl.desafioconcrete.model.usecase


import com.chsl.desafioconcrete.data.entity.pullrequests.PullsRequestsResponse
import com.chsl.desafioconcrete.data.service.Api
import com.chsl.desafioconcrete.data.service.Requester
import com.chsl.desafioconcrete.model.PullsRequestsModelContract
import io.reactivex.Observable

class PullsRequestsUseCase : PullsRequestsModelContract.PullsRequestsUseCase {

    override fun getAllPullsRequests(creator: String, repository: String): Observable<List<PullsRequestsResponse>> {
        return Requester.service.create(Api::class.java).allPullsRequests(creator, repository)
    }

}