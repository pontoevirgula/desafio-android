package com.chsl.desafioconcrete.data.entity.repositories

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    val pushedAt: String? = null,
    val subscriptionUrl: String? = null,
    val language: String? = null,
    val branchesUrl: String? = null,
    val issueCommentUrl: String? = null,
    val labelsUrl: String? = null,
    val score: Int? = null,
    val subscribersUrl: String? = null,
    val releasesUrl: String? = null,
    val svnUrl: String? = null,
    val id: Int? = null,
    val forks: Int? = null,
    val archiveUrl: String? = null,
    val gitRefsUrl: String? = null,
    val forksUrl: String? = null,
    val statusesUrl: String? = null,
    val sshUrl: String? = null,
    val license: Any? = null,
    @SerializedName("full_name")
    val fullName: String,
    val size: Int? = null,
    val languagesUrl: String? = null,
    val htmlUrl: String? = null,
    val collaboratorsUrl: String? = null,
    val cloneUrl: String? = null,
    @SerializedName("name")
    val name: String,
    val pullsUrl: String? = null,
    val defaultBranch: String? = null,
    val hooksUrl: String? = null,
    val treesUrl: String? = null,
    val tagsUrl: String? = null,
    val jsonMemberPrivate: Boolean? = null,
    val contributorsUrl: String? = null,
    val hasDownloads: Boolean? = null,
    val notificationsUrl: String? = null,
    val openIssuesCount: Int? = null,
    @SerializedName("description")
    val description: String,
    val createdAt: String,
    val watchers: Int? = null,
    val keysUrl: String? = null,
    val deploymentsUrl: String? = null,
    val hasProjects: Boolean? = null,
    val archived: Boolean? = null,
    val hasWiki: Boolean? = null,
    val updatedAt: String? = null,
    val commentsUrl: String? = null,
    val stargazersUrl: String? = null,
    val disabled: Boolean? = null,
    val gitUrl: String? = null,
    val hasPages: Boolean? = null,
    @SerializedName("owner")
    val owner: Owner,
    val commitsUrl: String? = null,
    val compareUrl: String? = null,
    val gitCommitsUrl: String? = null,
    val blobsUrl: String? = null,
    val gitTagsUrl: String? = null,
    val mergesUrl: String? = null,
    val downloadsUrl: String? = null,
    val hasIssues: Boolean? = null,
    val url: String? = null,
    val contentsUrl: String? = null,
    val mirrorUrl: Any? = null,
    val milestonesUrl: String? = null,
    val teamsUrl: String? = null,
    val fork: Boolean? = null,
    val issuesUrl: String? = null,
    val eventsUrl: String? = null,
    val issueEventsUrl: String? = null,
    val assigneesUrl: String? = null,
    val openIssues: Int? = null,
    val watchersCount: Int? = null,
    val nodeId: String? = null,
    val homepage: String? = null,
    @SerializedName("forks_count")
    val forksCount: Int
)

