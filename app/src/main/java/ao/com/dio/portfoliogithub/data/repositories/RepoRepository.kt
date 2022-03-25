package ao.com.dio.portfoliogithub.data.repositories

import ao.com.dio.portfoliogithub.data.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun listRepositories(user: String): Flow<List<Repo>>
}