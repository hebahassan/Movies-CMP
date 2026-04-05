package com.example.feature.home.data.mapper

import com.example.feature.home.data.model.GenreDto
import com.example.feature.home.domain.model.Genre

fun GenreDto.toDomain(): Genre = Genre(id = id, name = name)