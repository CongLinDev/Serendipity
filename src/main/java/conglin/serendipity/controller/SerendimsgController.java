package conglin.serendipity.controller;

import conglin.serendipity.domain.Serendimsg;
import conglin.serendipity.service.SerendimsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/serendimsg")
public class SerendimsgController {
    @Autowired
    private SerendimsgService serendimsgService;

    @GetMapping("/delete/{id}")
    public String deleteSerendimsg(@PathVariable("id") Long id){
        serendimsgService.delete(id);
        return "forward:/serendipper/";
    }

    @GetMapping("/all")
    public String allSerendimsg(ModelMap map){
        map.addAttribute("serendimsg", new Serendimsg());
        map.addAttribute("new_serendimsg_action", "new_serendimsg");

        map.addAttribute("serendimsgs", serendimsgService.findAll());
        return "serendimsg";
    }

    @PostMapping("/new_serendimsg")
    public String newSerendimsg(ModelMap map,
                                @ModelAttribute @Valid Serendimsg serendimsg,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            map.addAttribute("new_serendimsg_action", "new_serendimsg");
            return "/error";
        }

        serendimsgService.insertBySerendimsg(serendimsg);//添加信息
        return "redirect:/serendipper/";
    }
}
