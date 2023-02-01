package com.shaya.githubrepository.network.responses

data class Item(
    val description: String?,
    val html_url: String?,
    val id: Int?,
    val name: String?,
    val owner: Owner?
)