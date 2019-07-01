/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcarlidev.kettlerjobexecuter.etl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.parameters.UnknownParamException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//import org.pentaho.di.core.KettleEnvironment;
//import org.pentaho.di.job.Job;
//import org.pentaho.di.job.JobMeta;

/**
 *
 * @author carlos
 */
@Service
public class ETL_Executor {

    @Value(value = "${spring.jobPath}")
    private String jobPath;

    public void executeKettleJob(String etlJobName, Map<String, String> parameters, Map<String, String> variables) throws Exception {
        KettleEnvironment.init();
        if ("".equals(etlJobName)) {
            throw new Exception("Job name is empty!");
        } else {
            String completePath = jobPath + etlJobName + ".ktr.kjb";
            System.err.println("Path: " + completePath);
            File f = new File(completePath);
            if (f.isFile()) {
                JobMeta jobMeta = new JobMeta(completePath, null);
                Job job = new Job(null, jobMeta);
                if (parameters != null) {
                    parameters.forEach((key, value) -> {
                        try {
                            job.setParameterValue(key, value);
                        } catch (UnknownParamException ex) {
                            Logger.getLogger(ETL_Executor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }
                if (variables != null) {
                    variables.forEach(job::setVariable);
                }
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String fecha = dateFormat.format(date);
                job.setVariable("actualDate", fecha);
                job.start();
                job.waitUntilFinished();
                if (job.isFinished() && job.getErrors() == 0) {
                    System.out.println("Transformation has been finished correctly!!!!");
                } else {
                    System.out.println("Error in transformation: \n");
                }
            } else {
                throw new Exception("Job not found!");
            }
        }
    }
}
