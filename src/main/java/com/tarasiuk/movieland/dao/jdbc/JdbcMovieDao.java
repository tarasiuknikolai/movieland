package com.tarasiuk.movieland.dao.jdbc;

import java.util.List;
import com.tarasiuk.movieland.dao.MovieDao;
import com.tarasiuk.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.tarasiuk.movieland.entity.Movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcMovieDao implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private String getMovieByIdSQL;

    @Autowired
    private String getAllMoviesSQL;


    @Override
    public List<Movie> getAll() {
        log.info("Start query to get all movies from DB");
        long startTime = System.currentTimeMillis();
        List<Movie> movieList = jdbcTemplate.query(getAllMoviesSQL,  new MovieRowMapper());
        log.info("Finish query to get all movies from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movieList;
    }


    @Override
    public Movie getById(int id) {
        log.info("Start query to get movie with id {} from DB", id);
        long startTime = System.currentTimeMillis();
        Movie movie = jdbcTemplate.queryForObject(getMovieByIdSQL, new Object[]{id}, new MovieRowMapper());
        log.info("Finish query to get city with id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return movie;
    }

}
