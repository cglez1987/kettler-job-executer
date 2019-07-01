/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcarlidev.kettlerjobexecuter.controllers;

import com.dcarlidev.kettlerjobexecuter.beans.JobConfiguration;
import com.dcarlidev.kettlerjobexecuter.etl.ETL_Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author carlos
 */
@RestController
@RequestMapping(path = "/executeEtl")
public class EtlExecutorController {

    @Autowired
    ETL_Executor etlExecutor;

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity executeEtl(@RequestParam("etlJobName") String etlPath) {
//        System.out.println("Vamos a ver si funcionaaaa...............");
//        try {
//            etlExecutor.executeKettleJob(etlPath, null, null);
//        } catch (Exception ex) {
//            Logger.getLogger(EtlExecutorController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return new ResponseEntity(HttpStatus.OK);
//    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity executeEtl(@Valid @RequestBody JobConfiguration jobConfiguration) {
        try {
            etlExecutor.executeKettleJob(jobConfiguration.getEtlJobName(), jobConfiguration.getParameters(), jobConfiguration.getVariables());
        } catch (Exception ex) {
            Logger.getLogger(EtlExecutorController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
