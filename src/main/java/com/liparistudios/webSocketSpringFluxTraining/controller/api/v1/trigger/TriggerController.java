package com.liparistudios.webSocketSpringFluxTraining.controller.api.v1.trigger;

import com.liparistudios.webSocketSpringFluxTraining.controller.api.v1.BaseAPIController;
import com.liparistudios.webSocketSpringFluxTraining.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/trigger")
public class TriggerController extends BaseAPIController {

    @Autowired
    TriggerService triggerService;


    @PostMapping("/action")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> trigger( @RequestBody Map request ) throws IOException {

        System.out.println("arrived request");
        System.out.println(request);

//        Map requestMap = objectMapper.readValue(request, Map.class);
//          request.get("channel").toString()

        triggerService.broadcastEmitNotific();

        Map<String, Object> userDataResponseMap = new HashMap<String, Object>(){{
            put("status", "ok");
        }};

        return this.prepareResponse( userDataResponseMap );
    }


}
