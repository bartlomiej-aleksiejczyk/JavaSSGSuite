package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class MyStaticSiteGenerator {
    public static void main(String[] args) {
        // Load routes from properties file
        Properties routes = new Properties();
        try {
            routes.load(MyStaticSiteGenerator.class.getResourceAsStream("/routes.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        templateEngine.setTemplateResolver(resolver);

        // Iterate over each route
        routes.forEach((path, templateName) -> {
            Context context = new Context();
            // Add your model attributes to the context if needed
            context.setVariable("user", new UserForm());

            String output = templateEngine.process(templateName.toString(), context);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output" + path + ".html"))) {
                writer.write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
