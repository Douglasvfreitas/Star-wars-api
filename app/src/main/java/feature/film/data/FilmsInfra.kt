package feature.film.data

import feature.StarWarsGateway
import feature.film.domain.FilmsMapper
import feature.film.domain.models.FilmsPresentation
import feature.utils.RetrofitClient
import kotlinx.serialization.ExperimentalSerializationApi


@OptIn(ExperimentalSerializationApi::class)
internal class FilmsInfra {
    private val api = RetrofitClient.createService(StarWarsGateway::class.java)

    suspend fun listMovies(): FilmsPresentation {
        val response = api.listMovies()
        return FilmsMapper.toDomain(response)
    }
}