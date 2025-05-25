package com.solo.mymovies.data

import com.solo.mymovies.data.remote.dto.MovieCrewDTO
import com.solo.mymovies.data.remote.dto.MovieDetailsDTO
import com.solo.mymovies.data.remote.dto.PopularMovieDTO
import com.solo.mymovies.domain.model.MovieCrew
import com.solo.mymovies.domain.model.MovieDetails
import com.solo.mymovies.domain.model.PopularMovie

fun PopularMovieDTO.toPopularMovie() = PopularMovie(
    id = id,
    overview = overview,
    backdropPath = backdropPath,
    originalTitle = originalTitle,
    title = title,
    genreIds = genreIds,
    posterPath = posterPath
)

fun MovieDetailsDTO.toMovieDetails() = MovieDetails(
    id = id,
    overview = overview,
    backdropPath = backdropPath,
    originalTitle = originalTitle,
    title = title,
    genres = genres,
    status = status,
    budget = budget,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount,
    popularity = popularity,
    tagline = tagline,
    homepage = homepage,
    posterPath = posterPath
)

fun MovieCrewDTO.toMovieCrew() = MovieCrew(
    job = job,
    originalName = originalName,
    popularity = popularity,
    knownForDepartment = knownForDepartment,
    department = department,
    profilePath = profilePath,
    name = name,
    creditId = creditId
)