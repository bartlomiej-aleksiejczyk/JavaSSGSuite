package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.staticgenerator.templates.entities.IterableRouteEntity;
import org.thymeleaf.TemplateEngine;

public class MyStaticSiteGenerator {

    private static final TemplateEngine templateEngine = initializeTemplateEngine();
    private static Map<String, List<? extends IterableRouteEntity>> entityMap = new HashMap<>();

    public static void main(String[] args) {

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






}
