// 
// 
// 

package model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "t_Kehu")
public class Kehu implements Serializable
{
    private static final long serialVersionUID = -7141419035239709511L;
    private long id;
    private String kehumingcheng;
    private String gongsimingchen;
    private double jiaoyijine;
    private int jiaoyicishu;
    private Date createtime;
    private int kehulock;
    
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
    
    public String getKehumingcheng() {
        return this.kehumingcheng;
    }
    
    public void setKehumingcheng(final String kehumingcheng) {
        this.kehumingcheng = kehumingcheng;
    }
    
    public String getGongsimingchen() {
        return this.gongsimingchen;
    }
    
    public void setGongsimingchen(final String gongsimingchen) {
        this.gongsimingchen = gongsimingchen;
    }
    
    public double getJiaoyijine() {
        return this.jiaoyijine;
    }
    
    public void setJiaoyijine(final double jiaoyijine) {
        this.jiaoyijine = jiaoyijine;
    }
    
    public int getJiaoyicishu() {
        return this.jiaoyicishu;
    }
    
    public void setJiaoyicishu(final int jiaoyicishu) {
        this.jiaoyicishu = jiaoyicishu;
    }
    
    public int getKehulock() {
        return this.kehulock;
    }
    
    public void setKehulock(final int kehulock) {
        this.kehulock = kehulock;
    }
}
