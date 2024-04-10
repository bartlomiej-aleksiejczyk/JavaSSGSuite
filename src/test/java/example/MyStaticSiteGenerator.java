package example;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyStaticSiteGenerator {

    private static final String OUTPUT_BASE_DIR = "output";
    private static final TemplateEngine templateEngine = initializeTemplateEngine();
    private static Map<String, List<? extends IterableRouteEntity>> entityMap = new HashMap<>();

    public static void main(String[] args) {
        List<Book> books = List.of(new Book(1, "Book One", "Author One", "Summary"),
                new Book(2, "Book Two", "Author Two", "Summary"));
        entityMap.put("Book", books);


        routes.forEach((path, value) -> {
            String[] parts = ((String) value).split(":");
            String templateName = parts[0];
            String entityType = parts.length > 1 ? parts[1] : null;

            if (entityType != null && entityMap.containsKey(entityType)) {
                // This is an iterable route
                List<? extends IterableRouteEntity> entities = entityMap.get(entityType);
                for (IterableRouteEntity iterableRouteEntity : entities) {
                    generatePageForEntity(iterableRouteEntity, templateName, path.toString());
                }
            } else {
                // Handle static route
                generateStaticPage(path.toString(), templateName);
            }
        });
    }

    private static TemplateEngine initializeTemplateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }

    private static void generateStaticPage(List<Book> books) {
        Context context = new Context();
        context.setVariable("books", books);
        String output = templateEngine.process("book_list", context); // Assuming you have a `book_list.html`
        writeFile(OUTPUT_BASE_DIR + "/products/book/index.html", output);
    }

    private static void generatePageForEntity(Book book) {
        Context context = new Context();
        context.setVariable("book", book);
        String output = templateEngine.process("book_detail", context); // Assuming you have a `book_detail.html`
        writeFile(OUTPUT_BASE_DIR + "/products/book/" + book.getId() + "/index.html", output);
    }

    private static void writeFile(String filePath, String content) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
