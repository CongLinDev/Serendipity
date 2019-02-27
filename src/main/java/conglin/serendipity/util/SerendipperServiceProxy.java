package conglin.serendipity.util;
/*
 * Description:
 * Author: 从林
 * Class: 2016240206
 * Student Id: 2016903629
 * Created at 2019-02-26  19:40
 */

import conglin.serendipity.domain.Serendipper;
import conglin.serendipity.service.SerendipperService;
import org.springframework.beans.factory.annotation.Autowired;

public class SerendipperServiceProxy {
    @Autowired
    private static SerendipperService serendimsgService;

    public static String getSerendipperUsernameBySerendipperId(Long id){
        Serendipper serendipper = serendimsgService.findBySerendipperId(id);
        if(serendipper != null){
            return serendipper.getUsername();
        }else {
            return null;
        }
    }
}
