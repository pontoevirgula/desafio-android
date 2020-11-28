package com.chsl.desafioconcrete.model.usecase

import com.chsl.desafioconcrete.data.entity.repositories.RepositoryResponse
import com.chsl.desafioconcrete.data.service.Api
import com.chsl.desafioconcrete.data.service.Requester
import com.chsl.desafioconcrete.model.RepositoryModelContract
import io.reactivex.Observable

class RepositoryUseCase : RepositoryModelContract.RepositoryUseCase {

    override fun getRepository(page: Int): Observable<RepositoryResponse> {
        return Requester.service.create(Api::class.java).allRepository(page)
    }

}