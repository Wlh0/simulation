package org.cgn.simulation.utils;

import org.cgn.simulation.base.constant.ConfigFilePath;
import org.cgn.simulation.dto.req.RunBenchmarkReqDTO;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

public final class YMLUtil {

    /**
     * null值问题，业务逻辑以及前端逻辑可以判断，快速失败
     * @param runBenchmarkReqDTO
     * @throws IOException
     */
    public static void jsonToYML(RunBenchmarkReqDTO runBenchmarkReqDTO) throws IOException {
        File file = new File(ConfigFilePath.CONFIG_FILE_PATH_PREFIX + runBenchmarkReqDTO.getFileName() + ".yml");
        Yaml yaml = new Yaml();
        FileWriter writer = new FileWriter(file);
        writer.write(yaml.dumpAsMap(runBenchmarkReqDTO));
        writer.close();
    }

    public static RunBenchmarkReqDTO YMLToJson(File filename) throws FileNotFoundException {
        File file = filename;
        Yaml yaml = new Yaml();
        RunBenchmarkReqDTO runBenchmarkReqDTO = yaml.loadAs(new FileReader(file), RunBenchmarkReqDTO.class);
        System.out.println(runBenchmarkReqDTO.toString());
        return runBenchmarkReqDTO;
    }

//    public static void main(String[] args) throws IOException {
//
//        RunBenchmarkReqDTO config = new RunBenchmarkReqDTO();
//        config.setDevice("1012223333210");
//        config.setSensors("20");
//        config.setFileName("filename1.yml");
//        config.setTimeStamp("1000");
//        jsonToYML(config);
//        YMLToJson("filename1.yml");
//    }
}
