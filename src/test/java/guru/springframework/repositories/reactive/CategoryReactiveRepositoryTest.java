package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testCategorySave() throws Exception {
        Category category = new Category();
        category.setDescription("Dondon");

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void findByDescription() throws Exception {
        Category category = new Category();
        category.setDescription("Dondon");

        categoryReactiveRepository.save(category).then().block();

        Category fetchedCat = categoryReactiveRepository.findByDescription("Dondon").block();
        assertNotNull(fetchedCat.getId());
    }

}