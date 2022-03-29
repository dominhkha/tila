package org.tila.utils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class FileUtils {
    private String filePath;
    private String content;

    public FileUtils(String filePath) {
        this.filePath = filePath;
        this.content = null;
    }

    public void readFile() {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
            this.content = contentBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getContent(){
        return this.content;
    }
}
