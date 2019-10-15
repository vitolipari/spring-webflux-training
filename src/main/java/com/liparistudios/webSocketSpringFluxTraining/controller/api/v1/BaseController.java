package com.liparistudios.webSocketSpringFluxTraining.controller.api.v1;

import org.springframework.beans.factory.annotation.Value;

public interface BaseController {
    final static String MOBILE_USER_AGENT_REGEX = "[a-zA-Z0-9\\(\\)\\.\\/\\s;,]+Mobi[a-zA-Z0-9\\(\\)\\.\\/\\s;,]+";
    final static String MOBILE_USER_AGENT_REGEX_LOWERCASE = "[a-zA-Z0-9\\(\\)\\.\\/\\s;,]+mobi[a-zA-Z0-9\\(\\)\\.\\/\\s;,]+";
    final static String ANDROID_USER_AGENT_REGEX = "[a-zA-Z0-9\\(\\)\\.\\/\\s;,]+ndroid[a-zA-Z0-9\\(\\)\\.\\/\\s;,]+";
    final static String IPHONE_USER_AGENT_REGEX = "[a-zA-Z0-9\\(\\)\\.\\/\\s;,]+i[pP]hone[a-zA-Z0-9\\(\\)\\.\\/\\s;,]+";


}
