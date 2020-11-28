package com.chsl.desafioconcrete.model

import com.chsl.desafioconcrete.data.entity.repositories.RepositoryResponse
import io.reactivex.Observable

interface RepositoryModelContract {

    interface RepositoryUseCase {
        fun getRepository(page: Int = 1) :Observable<RepositoryResponse>
    }
}