/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcarlidev.kettlerjobexecuter.etl;

import com.dcarlidev.kettlerjobexecuter.KettlerJobExecutor;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
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

    public void executeKettleJob(String path, Map<String, String> parameters, Map<String, String> variables) {
        try {
            KettleEnvironment.init();
            if ("".equals(path)) {
                throw new Exception("Path is empty!");
            } else {
                File f = new File(path);
                if (f.isFile()) {
                    List<String> ad = new ArrayList();
                    JobMeta jobMeta = new JobMeta(path, null);
                    Job job = new Job(null, jobMeta);
                    parameters.forEach((key, value) -> {
                        try {
                            job.setParameterValue(key, value);
                        } catch (Exception ex) {
                            Logger.getLogger(ETL_Executor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    variables.forEach(job::setVariable);

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
        } catch (Exception ex) {
            Logger.getLogger(ETL_Executor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
