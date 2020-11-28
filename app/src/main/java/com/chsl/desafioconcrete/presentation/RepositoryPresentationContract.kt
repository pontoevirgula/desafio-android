package com.chsl.desafioconcrete.presentation

import com.chsl.desafioconcrete.core.bases.BaseContract
import com.chsl.desafioconcrete.data.entity.repositories.Items

interface RepositoryPresentationContract {

    interface RepositoryListView {
        fun populateRepository(itemList: List<Items>)
        fun success()
        fun loading()
        fun error()
    }

    interface RepositoryListPresenter : BaseContract.Presenter {
        fun fetchRepository(page: Int = 1)
    }
}