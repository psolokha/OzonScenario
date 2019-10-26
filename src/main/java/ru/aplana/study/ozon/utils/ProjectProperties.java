package ru.aplana.study.ozon.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class ProjectProperties {

    private static ProjectProperties instance;
    private static Properties properties;

    private ProjectProperties(){

        try {
            properties = new Properties();
            properties.load(new FileInputStream(new File("src/main/resources/project.properties")));
        } catch (IOException e) {
            System.out.println("Can't load .properties file");
            e.printStackTrace();
        }

    }

    static Properties getProperties() {
        if(instance == null) instance = new ProjectProperties();
        return properties;
    }

}
