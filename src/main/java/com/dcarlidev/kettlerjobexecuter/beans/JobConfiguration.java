/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcarlidev.kettlerjobexecuter.beans;

import java.util.Map;
import javax.validation.constraints.NotNull;

/**
 *
 * @author carlos
 */
public class JobConfiguration {

    @NotNull
    private String etlJobName;
    private Map<String, String> parameters;
    private Map<String, String> variables;

    public JobConfiguration() {
    }

    public JobConfiguration(String etlJobName, Map<String, String> parameters, Map<String, String> variables) {
        this.etlJobName = etlJobName;
        this.parameters = parameters;
        this.variables = variables;
    }

    public String getEtlJobName() {
        return etlJobName;
    }

    public void setEtlJobName(String etlJobName) {
        this.etlJobName = etlJobName;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

}
