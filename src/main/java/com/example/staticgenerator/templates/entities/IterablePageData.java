package com.example.staticgenerator.templates.entities;

import org.thymeleaf.context.Context;

public class IterablePageData extends PageData{
    private String entityName;
    public IterablePageData(String templateName, String outputPath, Context context, String entityName) {
        super(templateName, outputPath, context);
        this.entityName = entityName;
    }

}