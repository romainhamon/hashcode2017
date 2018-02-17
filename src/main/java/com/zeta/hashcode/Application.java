package com.zeta.hashcode;

import com.zeta.hashcode.utils.FileParser;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Application {

    public static void main(String[] args) {

        log.debug("Google Hash Code 2017");

        FileParser.readInputDataSet();

    }
}
