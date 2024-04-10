package com.example.staticgenerator.templates;

import com.example.Book;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TemplateRenderer {
    private static final String OUTPUT_BASE_DIR = "output";
    private final TemplateEngine templateEngine;
    public TemplateRenderer() {
        TemplateEngine engine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        engine.setTemplateResolver(resolver);
        this.templateEngine = engine;
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

    public void generatePage(List<Book> books) {
        Context context = new Context();
        context.setVariable("books", books);
        String output = this.templateEngine.process("book_list", context); // Assuming you have a `book_list.html`
        writeFile(OUTPUT_BASE_DIR + "/products/book/index.html", output);
    }

    public void generatePageForEntity(Book book) {
        Context context = new Context();
        context.setVariable("book", book);
        String output = this.templateEngine.process("book_detail", context); // Assuming you have a `book_detail.html`
        writeFile(OUTPUT_BASE_DIR + "/products/book/" + book.getId() + "/index.html", output);
    }
}
