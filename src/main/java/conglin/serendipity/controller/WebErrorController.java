package conglin.serendipity.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, ModelMap map){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        //Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        map.addAttribute("statusCode", statusCode);
        //map.addAttribute("throwable", throwable);
        return "error";
    }

    @Override
    public String getErrorPath(){
        return "/error";
    }

}
