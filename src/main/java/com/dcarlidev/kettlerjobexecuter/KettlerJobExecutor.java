/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcarlidev.kettlerjobexecuter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author lisbet
 */
@SpringBootApplication
public class KettlerJobExecutor {

    public static void main(String[] args) {
        SpringApplication.run(KettlerJobExecutor.class, args);
    }

    public static void execute() {
        try {
            String main_path = KettlerJobExecutor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            File parent = new File(main_path).getParentFile();
            String separator = System.getProperty("file.separator");

            HashMap<String, String> job_parameters = new HashMap<>();
            HashMap<String, String> job_variables = new HashMap<>();

            Properties properties = new Properties();
            InputStream fileProperties = new FileInputStream(parent.getPath() + separator + "config" + separator + "config.properties");
            System.out.println("FilePropeties: " + fileProperties.toString());
            try {
                properties.load(fileProperties);
                properties.stringPropertyNames().forEach(prop -> {
                    if (prop.startsWith("parameter")) {
                        String parameter;
                        String value;
                        if (prop.contains("encrypt")) {
                            parameter = prop.replaceFirst("parameter.encrypt.", "");
                            //value = SecurityUtil.decrypt(properties.getProperty(prop));
                            value = properties.getProperty(prop);
                        } else {
                            parameter = prop.replaceFirst("parameter.", "");
                            value = properties.getProperty(prop);
                        }
                        job_parameters.put(parameter, value);
                    }
                    if (prop.startsWith("variable")) {
                        String variable;
                        String value;
                        if (prop.contains("encrypt")) {
                            variable = prop.replaceFirst("variable.encrypt.", "");
                            //value = SecurityUtil.decrypt(properties.getProperty(prop));
                            value = properties.getProperty(prop);
                        } else {
                            variable = prop.replaceFirst("variable.", "");
                            value = properties.getProperty(prop);
                        }
                        job_variables.put(variable, value);
                    }
                });
            } catch (Exception ex) {
                Logger.getLogger(KettlerJobExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String fecha = dateFormat.format(date);
            job_parameters.put("actual_date", fecha);

            String job_path = "";

        } catch (Exception ex) {
            Logger.getLogger(KettlerJobExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
