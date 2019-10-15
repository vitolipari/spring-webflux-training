package com.liparistudios.webSocketSpringFluxTraining.controller.api.v1.trigger;

import com.liparistudios.webSocketSpringFluxTraining.controller.api.v1.BaseAPIController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/trigger")
public class TriggerController extends BaseAPIController {


    @PostMapping("/action")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> checkInstagramUsername( @RequestBody String request ) throws IOException {

        Map<String, Object> userDataResponseMap = new HashMap<String, Object>(){{
            put("status", "ok");
        }};

        return this.prepareResponse( userDataResponseMap );
    }


}
