package org.cgn.simulation.dto.req;

import lombok.Data;

/**
 * 运行benchmark请求入参
 * 数值类型对应为Integer，否则转换为yml文件会带上''
 */
@Data
public class RunBenchmarkReqDTO {
    /**
     * 设备数
     */
    private long device;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 传感器数
     */
    private long sensor;
    /**
     * 时间戳
     */
    private long timeStamp;

}
