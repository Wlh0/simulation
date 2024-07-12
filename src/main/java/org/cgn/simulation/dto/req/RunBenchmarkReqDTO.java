package org.cgn.simulation.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 运行benchmark请求入参
 */
@Data
public class RunBenchmarkReqDTO implements Serializable {

    private String fileName;

    private String timeStamp;

    private String device;

    private String sensors;



}
