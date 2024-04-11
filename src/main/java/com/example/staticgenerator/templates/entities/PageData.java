package com.example.staticgenerator.templates.entities;

import org.thymeleaf.context.Context;

//TODO: Intelligent Autocompletion
public class PageData {
    private String templateName;
    private String outputPath;
    private Context context;

    public PageData(String templateName, String outputPath, Context context) {
        this.templateName = templateName;
        this.outputPath = outputPath;
        this.context = context;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public Context getContext() {
        return context;
    }
}

