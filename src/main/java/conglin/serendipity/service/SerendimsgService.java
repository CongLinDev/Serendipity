package conglin.serendipity.service;

import java.util.List;

import conglin.serendipity.domain.Serendimsg;

public interface SerendimsgService {
    
    List<Serendimsg> findAll();

    Serendimsg insertBySerendimsg(Serendimsg serendimsg);

    Serendimsg update(Serendimsg serendimsg);

    Serendimsg delete(Long id);

    Serendimsg findBySerendimsg(Long id);
}