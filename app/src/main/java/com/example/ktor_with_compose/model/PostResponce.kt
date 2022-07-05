package com.example.ktor_with_compose.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(

	@SerialName("id")
	val id: Int? = null,

	@SerialName("title")
	val title: String? = null,

	@SerialName("body")
	val body: String? = null,

	@SerialName("userId")
	val userId: Int? = null
)


@Serializable
data class PostRequest(

	@SerialName("body")
	val body: String,

	@SerialName("title")
	val title: String,

	@SerialName("userId")
	val userId: Int
)