package com.example.staticgenerator.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownConverter {
    public static String convertMarkdownFile(String markdownFilePath) throws IOException {
        String markdownContent = Files.readString(Path.of(markdownFilePath));
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdownContent);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}
