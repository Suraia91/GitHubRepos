package ao.com.dio.portfoliogithub.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ao.com.dio.portfoliogithub.data.model.Repo
import ao.com.dio.portfoliogithub.domain.ListUserRepositoryUserCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.lang.Error

class MainViewModel(private val listUserRepositoryUserCase: ListUserRepositoryUserCase) :
    ViewModel() {

    private val _repos =MutableLiveData<State>()
    val repos: LiveData<State> = _repos

    fun getRepoList(user: String) {
        viewModelScope.launch {
            listUserRepositoryUserCase(user)
                .onStart {
                    _repos.postValue(State.Loading)

                }.catch {
                _repos.postValue(State.Error(it))
                }.collect {
                _repos.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val list: List<Repo>) : State()
        data class Error(val error: Throwable) : State()
    }
}