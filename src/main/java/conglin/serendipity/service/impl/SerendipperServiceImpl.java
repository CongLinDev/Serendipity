package conglin.serendipity.service.impl;

import conglin.serendipity.domain.Serendipper;
import conglin.serendipity.domain.SystemSerendipper;
import conglin.serendipity.repository.SerendipperRepository;
import conglin.serendipity.service.SerendipperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SerendipperServiceImpl implements SerendipperService, UserDetailsService{

    @Autowired
    private SerendipperRepository serendipperRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    @Override
    public List<Serendipper> findAll(){
        log.info("查找所有Serendipper");
        return serendipperRepository.findAll();
    }

    @Override
    public Serendipper insertBySerendipper(Serendipper serendipper){
        //检测email是否使用过
        if(findBySerendipperEmail(serendipper.getEmail()) == null){
            log.info("新增Serendipper：" + serendipper.toString());
            //密码加密
            serendipper.setPassword(encodePassword(serendipper.getPassword().trim()));

            return serendipperRepository.save(serendipper);
        }else {
            return null;
        }
    }

    @Override
    public Serendipper update(Serendipper serendipper){
        log.info("更新Serendipper：" + serendipper.toString());
        return serendipperRepository.save(serendipper);
    }

    @Override
    public Serendipper delete(Long id){
        Serendipper serendipper = serendipperRepository.findById(id).get();
        serendipperRepository.delete(serendipper);
        log.info("删除Serendipper：" + serendipper.toString());
        return serendipper;
    }

    @Override
    public Serendipper findBySerendipperId(Long id){
        log.info("查找Serendipper：id=" + id.toString());
        return serendipperRepository.findById(id).get();
    }

    @Override
    public Serendipper findBySerendipperUsername(String username){
        log.info("查找Serendipper：username=" + username);
        return serendipperRepository.findByUsername(username);
    }
    @Override
    public Serendipper findBySerendipperEmail(String email){
        log.info("查找Serendipper：email=" + email);
        return serendipperRepository.findByEmail(email);
    }

    @Override
    public Serendipper loginByEmailAndPassword(String email, String password){
        Serendipper serendipper = findBySerendipperEmail(email);
        if(serendipper == null){
            log.info("登陆失败:账号不存在 email=" + email);
            return null;
        }
        if(!encodePassword(password.trim()).equals(serendipper.getPassword())){
            log.info("登陆失败:密码错误 email=" + email);
            return null;
        }
        log.info("登陆成功: email=" + email);
        return serendipper;
    }

    @Override
    public Serendipper getCurrentSerendipper(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) return null;
        return (Serendipper) authentication.getPrincipal();
    }


    //实现UserDetailsService接口
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{
        Serendipper serendipper = serendipperRepository.findByEmail(username);
        if(serendipper == null){
            String notfoundTip = "未找到Serendipper：email=" + username;
            log.info(notfoundTip);
            throw new UsernameNotFoundException(notfoundTip);
        }

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = getUserServiceAuthorities(serendipper);
        SystemSerendipper systemSerendipper = new SystemSerendipper(serendipper,
                true, true, true, true, simpleGrantedAuthorities);

        log.info("验证Serendipper信息：" + systemSerendipper.toString());
        return systemSerendipper;
    }

    private List<SimpleGrantedAuthority> getUserServiceAuthorities(Serendipper serendipper){
        String[] authorities = serendipper.getRoles().split(",");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String role : authorities) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return simpleGrantedAuthorities;
    }

}