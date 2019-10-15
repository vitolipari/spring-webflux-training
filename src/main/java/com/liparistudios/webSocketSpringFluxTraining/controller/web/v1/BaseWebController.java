package com.liparistudios.webSocketSpringFluxTraining.controller.web.v1;

import com.liparistudios.webSocketSpringFluxTraining.controller.api.v1.BaseController;
import org.springframework.beans.factory.annotation.Value;

public class BaseWebController implements BaseController {

    @Value("${some-value}")
    protected String SOME_VALUE;


}
