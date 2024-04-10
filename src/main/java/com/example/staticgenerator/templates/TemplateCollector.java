package com.example.staticgenerator.templates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.thymeleaf.context.Context;

import com.example.staticgenerator.templates.entities.TemplateData;
import com.example.staticgenerator.utils.MarkdownConverter;

public class TemplateCollector {
    public List<PageData> collectPages(String templatesDir, String markdownDir) throws IOException {
        List<PageData> pages = new ArrayList<>();

        // Handle Markdown files
        try (Stream<Path> stream = Files.walk(Paths.get(markdownDir))) {
            List<Path> markdownFiles = stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".md"))
                    .collect(Collectors.toList());

            for (Path markdownFile : markdownFiles) {
                String htmlContent = MarkdownConverter.convertMarkdownFile(markdownFile.toString());
                String baseName = markdownFile.getFileName().toString().replace(".md", "");
                Context context = new Context();
                context.setVariable("content", htmlContent);
                pages.add(new TemplateData(baseName, "output/path/" + baseName + ".html", context));
            }
        }

        // Handle Thymeleaf templates recursively
        try (Stream<Path> stream = Files.walk(Paths.get(templatesDir))) {
            List<Path> templateFiles = stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".html"))
                    .collect(Collectors.toList());

            for (Path templateFile : templateFiles) {
                String baseName = templateFile.getFileName().toString().replace(".html", "");
                Context context = new Context();

                // Optionally, add some default or dynamic context for regular templates here
                // For example, context.setVariable("title", "Some Title");

                pages.add(new TemplateData(baseName, "output/path/" + baseName + ".html", context));
            }
        }

        return pages;
    }

}
