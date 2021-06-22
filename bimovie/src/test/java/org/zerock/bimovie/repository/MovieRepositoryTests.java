package org.zerock.bimovie.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.bimovie.entity.Movie;
import org.zerock.bimovie.entity.Poster;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testInsert() {

        log.info("testInsert..............");

        Movie movie = Movie.builder().title("극한직업").build();

        movie.addPoster(Poster.builder().fname("극한직업포스터1.jpg").build());
        movie.addPoster(Poster.builder().fname("극한직업포스터12jpg").build());

        movieRepository.save(movie);

        log.info(movie.getMno());
    }

    @Test
    @Transactional
    @Commit
    public void testAddPoster() {

        Movie movie = movieRepository.getById(1L);

        movie.addPoster(Poster.builder().fname("극한직업포스터3.jpg").build());

        movieRepository.save(movie);
    }

    @Test
    @Transactional
    @Commit
    public void testRemovePoster() {

        Movie movie = movieRepository.getById(1L);

        movie.removePoster(2L);

        movieRepository.save(movie);
    }

    @Test
    public void insertMovies() {

        IntStream.rangeClosed(10, 100).forEach(i -> {
            Movie movie = Movie.builder().title("세계명작" +i).build();

            movie.addPoster(Poster.builder().fname("세계명작" +i+"포스터1.jpg").build());
            movie.addPoster(Poster.builder().fname("세계명작" +i+"포스터2.jpg").build());

            movieRepository.save(movie);
        });
    }

    @Test
    public void testPaging1() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());

        Page<Movie> result = movieRepository.findAll(pageable);

        result.getContent().forEach(m -> {
            log.info(m.getMno());
            log.info(m.getTitle());
            log.info(m.getPosterList().size());
            log.info("--------------------------");
        });
    }

    @Test
    public void testPaging2All() {

        //문제 발생, limit 가 없다.
        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());

        Page<Movie> result = movieRepository.findAll2(pageable);

        result.getContent().forEach(m -> {
            log.info(m.getMno());
            log.info(m.getTitle());
            log.info(m.getPosterList());
            log.info("-----------------------------");
        });
    }

    @Test
    public void testPaging3All() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.findAll3(pageable);

        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));

            log.info("----------------------------");
        });
    }
}
