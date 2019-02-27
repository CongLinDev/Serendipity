package conglin.serendipity.controller;

import conglin.serendipity.domain.Serendipper;
import conglin.serendipity.service.SerendipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController{
    @Autowired
    private SerendipperService serendipperService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(ModelMap map){
        map.addAttribute("serendipper", new Serendipper());
        map.addAttribute("register_action", "register");
        return "register";
    }

    /**
     *  创建用户
     *    处理 POST 请求，用来获取用户列表
     *    通过 @ModelAttribute 绑定参数，也通过 @RequestParam 从页面中传递参数
     */
    @PostMapping("/register")
    public String register(ModelMap map,
                           @ModelAttribute @Valid Serendipper serendipper,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            map.addAttribute("register_action", "register");
            return "register";
        }

        if(serendipperService.insertBySerendipper(serendipper) != null){//添加用户成功
            return "redirect:/serendipper/"+ serendipper.getId();
        } else {//添加用户失败
            return "redirect:/account/register";
        }
    }



}