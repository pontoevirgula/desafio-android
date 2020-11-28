package com.chsl.desafioconcrete.presentation

import com.chsl.desafioconcrete.core.bases.BaseContract
import com.chsl.desafioconcrete.data.entity.pullrequests.PullsRequestsResponse

interface PullsRequestsPresentationContract {

    interface PullsRequestsListView {
        fun populatePullsRequests(itemList: List<PullsRequestsResponse>)
        fun success()
        fun loading()
        fun error()
    }

    interface PullsRequestsPresenter : BaseContract.Presenter {
        fun fetchPullsRequests(creator: String, repository: String)
    }
}