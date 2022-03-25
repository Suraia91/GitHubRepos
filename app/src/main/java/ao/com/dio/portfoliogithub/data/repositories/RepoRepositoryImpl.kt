package ao.com.dio.portfoliogithub.data.repositories


import ao.com.dio.portfoliogithub.core.RemoteException
import ao.com.dio.portfoliogithub.data.services.GitHubService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoryImpl(private val service: GitHubService) : RepoRepository {
    override suspend fun listRepositories(user: String) = flow {
        try {
            val repoList = service.listRepositories(user)
            emit(repoList)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Dados nao encontrados!")
        }
    }
}