package conglin.serendipity.domain;

import conglin.serendipity.service.SerendipperService;
import conglin.serendipity.service.impl.SerendipperServiceImpl;
import conglin.serendipity.util.DateUtil;
import conglin.serendipity.util.SpringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 信息实体类 
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "serendimsg", schema = "serendimsg")
public class Serendimsg{
    //Serendimsg id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serendimsg_id")
    private Long id;

    //创建者用户 id
    @CreatedBy
    @Column(
        name = "created_by",
        nullable = false,
        updatable = false
    )
    private Long createdBy;

    //文本内容
    //@NotEmpty()
    @Size(max=140, message="最大不得超过140字。")
    private String message;

    //发送时间
    @CreatedDate
    @Column(
        name = "created_date",
        nullable = false,
        updatable = false
    )
    private Date time;
    
    public String getCreatedDateMonth(){
        return DateUtil.getDateMonth(this.getTime());
    }

    public String getCreatedDateDay(){
        return DateUtil.getDateDay(this.getTime());
    }

    public String getCreatedDateYear(){
        return DateUtil.getDateYear(this.getTime());
    }

    public String getCreatedDateTime(){
        return DateUtil.getDateTime(this.getTime());
    }

    public String getHost(){
        SerendipperService serendipperService = SpringUtil.getBean(SerendipperServiceImpl.class);
        Serendipper serendipper = serendipperService.findBySerendipperId(this.getCreatedBy());
        if(serendipper != null)
            return serendipper.getUsername();
        return "Unknown";
    }

    @Override
    public boolean equals(Object obj){
        return EqualsBuilder.reflectionEquals(this, obj, "id", "time");
    }

    @Override
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this, "id", "time");
    }
}