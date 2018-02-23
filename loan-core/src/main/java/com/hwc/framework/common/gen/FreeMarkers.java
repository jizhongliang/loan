package com.hwc.framework.common.gen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

public class FreeMarkers {

    public static String renderString(String templateString, Map<String, ?> model)
            throws IOException, TemplateException {

        StringWriter result = new StringWriter();
        Template t = new Template("name", new StringReader(templateString), new Configuration());
        t.process(model, result);
        return result.toString();
    }

    public static String renderTemplate(Template template, Object model) throws TemplateException, IOException {

        StringWriter result = new StringWriter();
        template.process(model, result);
        return result.toString();
    }

    public static Configuration buildConfiguration(String directory) throws IOException {
        Configuration cfg = new Configuration();
        Resource path = new DefaultResourceLoader().getResource(directory);
        cfg.setDirectoryForTemplateLoading(path.getFile());
        return cfg;
    }

}
