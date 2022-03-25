package ao.com.dio.portfoliogithub.data.services

import ao.com.dio.portfoliogithub.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
 @GET("users/{user}/repos")
suspend fun listRepositories(@Path("user")user:String):List<Repo>
}