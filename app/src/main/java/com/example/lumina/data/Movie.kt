package com.example.lumina.data

data class Movie(
    val id: Int,
    val title: String,
    val genre: String,
    val year: Int,
    val description: String,
    val imageRes: Int
) : Displayable {
    override fun displayName(): String = "$title ($year)"

    // Función de extensión
    fun Movie.shortDescription(): String = "$title - $genre"

    // Propiedad de extensión
    val Movie.isRecent: Boolean
        get() = year > 2015
}