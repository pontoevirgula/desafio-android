package com.chsl.desafioconcrete.model

import com.chsl.desafioconcrete.data.entity.pullrequests.PullsRequestsResponse
import io.reactivex.Observable

interface PullsRequestsModelContract {

    interface PullsRequestsUseCase {
        fun getAllPullsRequests(creator: String, repository: String) : Observable<List<PullsRequestsResponse>>
    }
}