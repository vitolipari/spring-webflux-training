package com.liparistudios.webSocketSpringFluxTraining.controller.web.v1.home;

import com.liparistudios.webSocketSpringFluxTraining.controller.web.v1.BaseWebController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/websocket")
@CrossOrigin(origins = "*")
public class ReactiveController extends BaseWebController {


    private ModelAndView webSocketPage;

    @GetMapping("")
    public ModelAndView websocketClientPage(HttpServletRequest request, HttpServletResponse response) {

        try {

            webSocketPage = new ModelAndView("websocketClient/index");
            webSocketPage.addObject("someValue", SOME_VALUE);


        }
        catch (Exception e) {

            webSocketPage.addObject("error", "Core Error");

            String stackError = e.getMessage() +"<br/><hr/><br/>";
            for( StackTraceElement se : e.getStackTrace() ) {
                stackError += se.getFileName() +"["+ se.getLineNumber() +"] in "+ se.getMethodName() +"<br/>"+ se.toString() +"<br/><br/>";
            }
            webSocketPage.addObject("err_msg", stackError);
            webSocketPage.addObject("err_code", 100);

        }

        return webSocketPage;
    }


}
