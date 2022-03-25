package ao.com.dio.portfoliogithub.domain

import ao.com.dio.portfoliogithub.core.UseCase
import ao.com.dio.portfoliogithub.data.model.Repo
import ao.com.dio.portfoliogithub.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoryUserCase(private val repoRepository: RepoRepository) :
    UseCase<String, List<Repo>>() {
    override suspend fun execute(param: String): Flow<List<Repo>> {
    return repoRepository.listRepositories(param)
    }
}