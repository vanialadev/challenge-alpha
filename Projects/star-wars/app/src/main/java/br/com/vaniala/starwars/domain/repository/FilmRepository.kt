package br.com.vaniala.starwars.domain.repository

import br.com.vaniala.starwars.domain.model.ApiResponse
import br.com.vaniala.starwars.domain.model.Film

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
interface FilmRepository {

    suspend fun insertAll(films: List<Film>)
    suspend fun getFilms(page: Int): ApiResponse<Film>

    suspend fun filmsByTitle(query: String): List<Film>

    suspend fun getFilmByTitle(query: String): Film

    suspend fun updateIsFavorite(isFavorite: Boolean, title: String)

    suspend fun isUpdate(): Boolean
}