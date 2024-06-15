package com.mhist.studyJava.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 */
public class CommonImportSelector implements ImportSelector{
    @Override
   public String[] selectImports(AnnotationMetadata importingClassMetadata){
        ArrayList<String> imports = new ArrayList<>();
        InputStream importstStream = CommonImportSelector.class.getClassLoader().getResourceAsStream("common.imports");
        BufferedReader importsBufferedReader = new BufferedReader(new InputStreamReader(importstStream));
        String line = null;

        try {
            while((line = importsBufferedReader.readLine()) != null){
                imports.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(importsBufferedReader != null) {
                try {
                    importsBufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return imports.toArray(new String[0]);
    }

}
