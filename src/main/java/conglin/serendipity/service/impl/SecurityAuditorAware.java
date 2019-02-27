package conglin.serendipity.service.impl;

import conglin.serendipity.domain.Serendipper;
import conglin.serendipity.service.SerendipperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component("auditorAware")
public class SecurityAuditorAware implements AuditorAware<Long> {
    @Autowired
    private SerendipperService serendipperService;

    @Override
    public Optional<Long> getCurrentAuditor(){
        Serendipper serendipper = serendipperService.getCurrentSerendipper();
        log.info("AuditorAware:" + serendipper.toString());
        return Optional.ofNullable(serendipper.getId());
    }
}
