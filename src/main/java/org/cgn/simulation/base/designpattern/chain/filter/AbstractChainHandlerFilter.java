package org.cgn.simulation.base.designpattern.chain.filter;

import org.cgn.simulation.base.designpattern.chain.AbstractChainHandler;
import org.cgn.simulation.base.enums.RunBenchmarkEnum;
import org.cgn.simulation.dto.req.RunBenchmarkReqDTO;

public interface AbstractChainHandlerFilter<T extends RunBenchmarkReqDTO> extends AbstractChainHandler<RunBenchmarkReqDTO> {

    @Override
    default String mark(){
        return RunBenchmarkEnum.CONFIG_FILE_FILTER.name();
    };
}
