package com.github.tisv2000.video_library.service;


import com.github.tisv2000.video_library.dao.MovieDao;
import com.github.tisv2000.video_library.dto.MovieDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MovieService {

    private static final MovieService INSTANCE = new MovieService();
    private final MovieDao movieDao = MovieDao.getInstance();

    public List<MovieDto> findAll() {

        return movieDao.findAll().stream()
                .map(movie -> new MovieDto(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getYear(),
                        movie.getCountry(),
                        movie.getGenre().getTitle()
                ))
                .collect(toList());
    }

    public static MovieService getInstance() {
        return INSTANCE;
    }
}
