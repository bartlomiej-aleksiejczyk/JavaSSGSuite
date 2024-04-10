package com.example.staticgenerator.templates;

import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.List;

public class TemplateContext {
    public static Context getContextFromList(HashMap<String, ?> contextMap) {
        Context context = new Context();
        contextMap.forEach(context::setVariable);
        return context;
    }
}
