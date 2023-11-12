package is.hi.hbv501g.verkefni;

import is.hi.hbv501g.verkefni.Persistence.Entities.Genre;
import is.hi.hbv501g.verkefni.Persistence.Repositories.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VerkefniApplicationTests {

    @Autowired
    GenreRepository gr;
    @Test
    void contextLoads() {
    }

    @Test
    void testGenre() {
        List<Genre> all = gr.findAll();
        all.forEach(genre -> System.out.println(genre.getGenre()));



    }

}
