package com.chsl.desafioconcrete.data.entity.repositories

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    val totalCount: Int? = null,
    val incompleteResults: Boolean? = null,
    @SerializedName("items")
    val items: List<Items>
)
