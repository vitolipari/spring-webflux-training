package com.liparistudios.webSocketSpringFluxTraining.controller.api.v1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;

public class BaseAPIController implements BaseController {


    public ResponseEntity<Map<String, Object>> prepareResponse(Map<String, Object> dataResponse) throws IOException {


        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

        dataResponse.put("core_status", "ok");

        return new ResponseEntity<Map<String, Object>>(dataResponse, responseHeaders, HttpStatus.OK);

    }

}
