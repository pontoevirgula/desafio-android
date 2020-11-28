package com.chsl.desafioconcrete.data.entity.pullrequests

import com.google.gson.annotations.SerializedName

data class Base(

    @field:SerializedName("ref")
    val ref: String? = null,

    @field:SerializedName("repo")
    val repo: Repo? = null,

    @field:SerializedName("label")
    val label: String? = null,

    @field:SerializedName("sha")
    val sha: String? = null,

    @field:SerializedName("user")
    val user: User? = null
)