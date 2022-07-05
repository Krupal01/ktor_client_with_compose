package com.example.ktor_with_compose.network

import android.util.Log
import com.example.ktor_with_compose.model.PostRequest
import com.example.ktor_with_compose.model.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

interface PostService {

    suspend fun getPost():List<PostResponse>

    suspend fun makePost(postRequest: PostRequest) : PostResponse?

    companion object{
        fun create() : PostService = PostServiceImpl(
            client = HttpClient(Android){
                install(Logging){
                    level = LogLevel.ALL
                }
                install(JsonFeature){
                    serializer = KotlinxSerializer()
                }
            }
        )
    }
}

class PostServiceImpl(
    private val client : HttpClient
) : PostService{
    override suspend fun getPost(): List<PostResponse> {
        return try {
            client.get{
                url(NetworkConstants.POSTS)
            }
        }catch (e : Exception){
            Log.i("Client Exception : ",e.toString())
            emptyList()
        }
    }

    override suspend fun makePost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse> {
                url(NetworkConstants.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        }catch (e : Exception){
            Log.i("Client Exception :",e.toString())
            null
        }
    }

}