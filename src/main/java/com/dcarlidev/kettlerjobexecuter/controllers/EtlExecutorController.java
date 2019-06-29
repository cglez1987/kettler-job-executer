/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcarlidev.kettlerjobexecuter.controllers;

import com.dcarlidev.kettlerjobexecuter.etl.ETL_Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lisbet
 */
@RestController
@RequestMapping(path = "/executeEtl")
public class EtlExecutorController {

    @Autowired
    ETL_Executor etlExecutor;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity executeEtl(@RequestParam("etlPath") String etlPath) {
        
        return new ResponseEntity(HttpStatus.OK);
    }

}
