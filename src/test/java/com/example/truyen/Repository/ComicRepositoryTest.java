package com.example.truyen.Repository;

import com.example.truyen.Entity.Comic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ComicRepositoryTest {

    @Autowired
    private ComicRepository comicRepository;

    @Test
    public void testFindAllComics() {
        List<Comic> comics = comicRepository.findAll();
        System.out.println("Comics size: " + comics.size());
        for (Comic comic : comics) {
            System.out.println("Title: " + comic.getTitle());
        }
    }
}
