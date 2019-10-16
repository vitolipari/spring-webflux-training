package com.liparistudios.webSocketSpringFluxTraining.controller.web.v1.home;

import com.liparistudios.webSocketSpringFluxTraining.controller.web.v1.BaseWebController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class HomeController extends BaseWebController {


    private ModelAndView homePage;

    @GetMapping({"", "/home"})
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {

        try {

            System.out.println("request to Home");

            Pattern androidPattern          = Pattern.compile(ANDROID_USER_AGENT_REGEX);
            Pattern iPhonePattern           = Pattern.compile(IPHONE_USER_AGENT_REGEX);
            Pattern mobilePattern           = Pattern.compile(MOBILE_USER_AGENT_REGEX_LOWERCASE);
            Pattern mobileUppercasePattern  = Pattern.compile(MOBILE_USER_AGENT_REGEX);

            Boolean isMobile =
                    androidPattern.matcher(request.getHeader("User-Agent")).find() ||
                            iPhonePattern.matcher(request.getHeader("User-Agent")).find() ||
                            mobilePattern.matcher(request.getHeader("User-Agent")).find() ||
                            mobileUppercasePattern.matcher(request.getHeader("User-Agent")).find()
                    ;


            homePage = new ModelAndView("triggerClient/index");
            homePage.addObject("someValue", SOME_VALUE);


        }
        catch (Exception e) {

            homePage.addObject("error", "Core Error");

            String stackError = e.getMessage() +"<br/><hr/><br/>";
            for( StackTraceElement se : e.getStackTrace() ) {
                stackError += se.getFileName() +"["+ se.getLineNumber() +"] in "+ se.getMethodName() +"<br/>"+ se.toString() +"<br/><br/>";
            }
            homePage.addObject("err_msg", stackError);
            homePage.addObject("err_code", 100);

        }

        return homePage;
    }



}
