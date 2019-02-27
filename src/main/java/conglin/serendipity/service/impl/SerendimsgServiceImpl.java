package conglin.serendipity.service.impl;

import conglin.serendipity.domain.Serendimsg;
import conglin.serendipity.repository.SerendimsgRepository;
import conglin.serendipity.service.SerendimsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SerendimsgServiceImpl implements SerendimsgService{
    @Autowired
    private SerendimsgRepository serendimsgRepository;

    @Override
    public List<Serendimsg> findAll(){
        log.info("查找所有Serendimsg");
        return serendimsgRepository.findAll();
    }

    @Override
    public Serendimsg insertBySerendimsg(Serendimsg serendimsg){
        log.info("新增Serendimsg：" + serendimsg.toString());
        return serendimsgRepository.save(serendimsg);
    }

    @Override
    public Serendimsg update(Serendimsg serendimsg){
        log.info("更新Serendimsg：" + serendimsg.toString());
        return serendimsgRepository.save(serendimsg);
    }

    @Override
    public Serendimsg delete(Long id){
        Serendimsg serendimsg = serendimsgRepository.findById(id).get();
        serendimsgRepository.delete(serendimsg);
        log.info("删除Serendimsg：" + serendimsg.toString());
        return serendimsg;
    }

    @Override
    @Transactional
    public Serendimsg findBySerendimsg(Long id){
        log.info("查找Serendimsg：id=" + id.toString());
        return serendimsgRepository.findById(id).get();
    }
}