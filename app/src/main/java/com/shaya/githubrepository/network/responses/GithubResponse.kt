package com.shaya.githubrepository.network.responses

data class GithubResponse(
    val incomplete_results: Boolean?,
    val items: List<Item>?,
    val total_count: Int?
)