package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.dto.MovieDto;
import com.github.tisv2000.video_library.service.MovieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/movies")
public class MovieServlet extends HttpServlet {

    private final MovieService movieService = MovieService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MovieDto> movieDto = movieService.findAll();
        req.setAttribute("movies", movieDto);
        req.getRequestDispatcher("/WEB-INF/jsp/movies.jsp").forward(req, resp);
    }
}
