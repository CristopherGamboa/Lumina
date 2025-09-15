package com.example.lumina.data

import com.example.lumina.R

object MovieRepository {
    private val movies = listOf(
        Movie(1, "Inception", "Sci-Fi", 2010, "Un ladrón roba secretos a través de los sueños.", R.drawable.inception),
        Movie(2, "Parasite", "Thriller", 2019, "Una sátira social sobre clases en Corea.", R.drawable.parasite),
        Movie(3, "The Dark Knight", "Action", 2008, "Batman enfrenta al Joker en Gotham.", R.drawable.dark_knight),
        Movie(4, "Spirited Away", "Fantasy", 2001, "Una niña entra en un mundo mágico.", R.drawable.spirited_away),
        Movie(5, "Interstellar", "Sci-Fi", 2014, "Viaje espacial para salvar a la humanidad.", R.drawable.interstellar)
    )

    fun loadMovies(): List<Movie> {
        return try {
            return movies
        } catch (e: Exception) {
            println("Error al cargar películas: ${e.message}")
            emptyList() // Devuelve lista vacía si falla
        }
    }


    fun findMovieByYear(year: Int): Movie? {
        var index = 0
        while (index < movies.size) {
            if (movies[index].year == year) return movies[index]
            index++
        }
        return null
    }

    fun listMoviesWithDoWhile(): List<Movie> {
        val result = mutableListOf<Movie>()
        var index = 0
        do {
            result.add(movies[index])
            index++
        } while (index < movies.size)
        return result
    }

    fun getMoviesByGenre(genre: String): List<Movie> {
        return movies.filter { it.genre == genre } // Lambda
    }

    fun processMovies(action: (Movie) -> Unit) {
        for (movie in movies) {
            action(movie) // Ejecuta la función pasada
        }
    }

    fun getFirstSciFi(): Movie? {
        movies.forEach label@{ movie ->
            if (movie.genre == "Sci-Fi") return movie // Sale del bucle con etiqueta
        }
        return null
    }
}
