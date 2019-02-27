package conglin.serendipity.domain;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@ToString
public class SystemSerendipper extends Serendipper implements UserDetails {

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<SimpleGrantedAuthority> authorities;

    /*以下方法实现 UserDetails 接口 */
    //用户拥有的权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }

    //用户账号是否过期
    @Override
    public boolean isAccountNonExpired(){
        return accountNonExpired;
    }

    //用户账号是否被锁定
    @Override
    public boolean isAccountNonLocked(){
        return accountNonLocked;
    }

    //用户密码是否过期
    @Override
    public boolean isCredentialsNonExpired(){
        return credentialsNonExpired;
    }

    //用户是否可用
    @Override
    public boolean isEnabled(){
        return enabled;
    }

    public SystemSerendipper(Serendipper serendipper,
                             boolean accountNonExpired,
                             boolean accountNonLocked,
                             boolean credentialsNonExpired,
                             boolean enabled,
                             List<SimpleGrantedAuthority> authorities){
        super(serendipper);
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = authorities;
    }
}
