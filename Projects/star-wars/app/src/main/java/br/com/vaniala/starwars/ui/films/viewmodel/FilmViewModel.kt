package br.com.vaniala.starwars.ui.films.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import br.com.vaniala.starwars.domain.model.Films
import br.com.vaniala.starwars.domain.usecase.GetFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
@HiltViewModel
class FilmViewModel @Inject constructor(
    getFilmsUseCase: GetFilmsUseCase,
) : ViewModel() {

    private val _filterTitle = MutableStateFlow("")
    val filterTitle = _filterTitle.asStateFlow()

    val pagingDataFlow = getFilmsUseCase().cachedIn(viewModelScope)

    fun pagingFilter(title: String): Flow<PagingData<Films>> {
        _filterTitle.value = title
        if (title.isEmpty()) {
            return pagingDataFlow
        }
        return pagingDataFlow.map { pagingData ->
            pagingData.filter { character ->
                character.title?.lowercase()?.contains(title.lowercase()) ?: false
            }
        }.cachedIn(viewModelScope)
    }
}
