package org.cgn.simulation.dto.resp;

import lombok.Builder;
import lombok.Data;
import org.cgn.simulation.dto.req.RunBenchmarkReqDTO;

import java.util.List;

@Builder
@Data
public class ConfigFileListRespDTO {

    List<RunBenchmarkReqDTO> runBenchmarkReqDTOS;
}
