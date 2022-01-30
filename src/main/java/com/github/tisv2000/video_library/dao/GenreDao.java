package com.github.tisv2000.video_library.dao;

import com.github.tisv2000.video_library.entity.Genre;
import com.github.tisv2000.video_library.exception.DaoException;
import com.github.tisv2000.video_library.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreDao implements Dao<Integer, Genre> {
    private static final GenreDao INSTANCE = new GenreDao();

    public static GenreDao getInstance() {
        return INSTANCE;
    }

    private static Map<Integer, Genre> cache = new HashMap<>();

    // we don't need this anymore?
    private static final String FIND_BY_ID_SQL = """
            SELECT id, title
            FROM genre
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, title
            FROM genre
            """;

    @Override
    public void save(Genre entity) {
        throw new DaoException("This function should not be called for predefined table");
    }

    @Override
    public boolean update(Genre entity) {
        throw new DaoException("This function should not be called for predefined table");
    }

    @Override
    @SneakyThrows
    public Optional<Genre> findById(Integer id) {
        checkCache();
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    @SneakyThrows
    public List<Genre> findAll() {
        checkCache();
        return new ArrayList<>(cache.values());
    }

    @SneakyThrows
    private void checkCache() {
        if (cache.isEmpty()) {
            synchronized (this) {
                if (cache.isEmpty()) {
                    fillCache();
                }
            }
        }
    }

    @SneakyThrows
    private void fillCache() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var genre = build(resultSet);
                cache.put(genre.getId(), genre);
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        throw new DaoException("This function should not be called for predefined table");
    }

    private Genre build(ResultSet resultSet) throws SQLException {
        return Genre.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .build();
    }
}
