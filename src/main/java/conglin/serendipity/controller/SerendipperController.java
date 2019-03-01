package conglin.serendipity.controller;

import conglin.serendipity.domain.Serendimsg;
import conglin.serendipity.domain.Serendipper;
import conglin.serendipity.service.SerendimsgService;
import conglin.serendipity.service.SerendipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/serendipper")
public class SerendipperController {
    @Autowired
    private SerendipperService serendipperService;

    @Autowired
    private SerendimsgService serendimsgService;

    @GetMapping("/{id}")
    public String serendimsg(@PathVariable("id") Long id, ModelMap map){
        map.addAttribute("serendimsg", new Serendimsg());
        map.addAttribute("new_serendimsg_action", "new_serendimsg");

        Serendipper serendipper = serendipperService.findBySerendipperId(id);
        if(serendipper != null){
            map.addAttribute("serendimsgs", serendipper.getSerendimsgs());
        }

        return "serendimsg";
    }

    @GetMapping(path={"/", ""})
    public String serendimsg(){
        Serendipper serendipper = serendipperService.getCurrentSerendipper();
        return "forward:/serendipper/" + serendipper.getId();
    }
}
