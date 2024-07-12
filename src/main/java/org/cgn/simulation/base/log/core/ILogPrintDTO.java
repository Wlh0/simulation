package org.cgn.simulation.base.log.core;

import lombok.Data;

@Data
public class ILogPrintDTO {


    private String beginTime;

    private Object[] inputParams;

    private Object outputParams;
}
