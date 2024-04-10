package example.staticgenerator;

import com.example.Book;
import com.example.staticgenerator.templates.entities.IterableRouteEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticGeneratorTest {

    private static Map<String, List<? extends IterableRouteEntity>> loadDummyData() {
        Map<String, List<? extends IterableRouteEntity>> entityMap = new HashMap<>();
        List<Book> books = List.of(new Book(1, "Book One", "Author One", "Summary"),
                new Book(2, "Book Two", "Author Two", "Summary"));
        entityMap.put("Books", books);
        return entityMap;
    }
}