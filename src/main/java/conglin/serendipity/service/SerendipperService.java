package conglin.serendipity.service;

import conglin.serendipity.domain.Serendipper;

import java.util.List;

public interface SerendipperService  {
    List<Serendipper> findAll();

    Serendipper insertBySerendipper(Serendipper serendipper);

    Serendipper update(Serendipper serendipper);

    Serendipper delete(Long id);

    Serendipper findBySerendipperId(Long id);

    Serendipper findBySerendipperUsername(String username);

    Serendipper findBySerendipperEmail(String email);

    Serendipper loginByEmailAndPassword(String email, String password);

    Serendipper getCurrentSerendipper();

}