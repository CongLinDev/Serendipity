package conglin.serendipity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 用户实体类
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "serendipper", schema = "serendipper")
public class Serendipper{
    //Serendipper id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serendipper_id")
    private Long id;

    //Serendipper 用户名
    @NotEmpty(message = "用户名不得为空")
    @Size(max=20, min=4, message="用户名不得小于4个字符且超过20个字符")
    @Column(name = "username")
    private String username;

    //Serendipper 密码
    @NotEmpty(message = "密码不得为空")
    @Size(min=6, message="密码不得小于6个字符")
    @Column(name = "password")
    private String password;

    //Serendipper email
    @NotEmpty(message = "E-mail不得为空")
    @Column(name = "email")
    private String email;

    //用户角色
    @Column(name = "roles")
    private String roles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="created_by")
    @OrderBy("serendimsg_id DESC")
    private List<Serendimsg> serendimsgs;

    @Override
    public boolean equals(Object obj){
        return EqualsBuilder.reflectionEquals(this, obj, "id", "time");
    }

    @Override
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this, "id", "time");
    }

    public Serendipper(Serendipper that){
        this.id = that.getId();
        this.username = that.getUsername();
        this.password = that.getPassword();
        this.email = that.getEmail();
        this.roles = that.getRoles();
    }
}