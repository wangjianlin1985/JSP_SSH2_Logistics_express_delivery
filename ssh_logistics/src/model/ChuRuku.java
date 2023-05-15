// 
// 
// 

package model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "t_ChuRuku")
public class ChuRuku implements Serializable
{
    private static final long serialVersionUID = -7141419035239709511L;
    private long id;
    private String shangpingming;
    private User user;
    private int shuliang;
    private double jiage;
    private Date createtime;
    private double zhanyongmianji;
    private String dingdanhao;
    private int type;
    
    public int getType() {
        return this.type;
    }
    
    public void setType(final int type) {
        this.type = type;
    }
    
    public double getZhanyongmianji() {
        return this.zhanyongmianji;
    }
    
    public void setZhanyongmianji(final double zhanyongmianji) {
        this.zhanyongmianji = zhanyongmianji;
    }
    
    public void setJiage(final double jiage) {
        this.jiage = jiage;
    }
    
    public String getShangpingming() {
        return this.shangpingming;
    }
    
    public void setShangpingming(final String shangpingming) {
        this.shangpingming = shangpingming;
    }
    
    public int getShuliang() {
        return this.shuliang;
    }
    
    public void setShuliang(final int shuliang) {
        this.shuliang = shuliang;
    }
    
    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(final Date createtime) {
        this.createtime = createtime;
    }
    
    @Id
    @GeneratedValue
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    @ManyToOne
    @JoinColumn(name = "userid")
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public double getJiage() {
        return this.jiage;
    }
    
    public String getDingdanhao() {
        return this.dingdanhao;
    }
    
    public void setDingdanhao(final String dingdanhao) {
        this.dingdanhao = dingdanhao;
    }
}
