// 
// 
// 

package model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "t_Caiwu")
public class Caiwu implements Serializable
{
    private static final long serialVersionUID = -7141419035239709511L;
    private long id;
    private String type;
    private Cheliang cheliang;
    private ChuRuku churuku;
    private Kucun kucun;
    private double jine;
    private Date createtime;
    private User user;
    private Dingdan dingdan;
    private String leixing;
    
    public String getLeixing() {
        return this.leixing;
    }
    
    public void setLeixing(final String leixing) {
        this.leixing = leixing;
    }
    
    @ManyToOne
    @JoinColumn(name = "dingdanid")
    public Dingdan getDingdan() {
        return this.dingdan;
    }
    
    public void setDingdan(final Dingdan dingdan) {
        this.dingdan = dingdan;
    }
    
    @Id
    @GeneratedValue
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    @ManyToOne
    @JoinColumn(name = "cheliangid")
    public Cheliang getCheliang() {
        return this.cheliang;
    }
    
    public void setCheliang(final Cheliang cheliang) {
        this.cheliang = cheliang;
    }
    
    @ManyToOne
    @JoinColumn(name = "churukuid")
    public ChuRuku getChuruku() {
        return this.churuku;
    }
    
    public void setChuruku(final ChuRuku churuku) {
        this.churuku = churuku;
    }
    
    @ManyToOne
    @JoinColumn(name = "kucunid")
    public Kucun getKucun() {
        return this.kucun;
    }
    
    public void setKucun(final Kucun kucun) {
        this.kucun = kucun;
    }
    
    public double getJine() {
        return this.jine;
    }
    
    public void setJine(final double jine) {
        this.jine = jine;
    }
    
    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(final Date createtime) {
        this.createtime = createtime;
    }
    
    @ManyToOne
    @JoinColumn(name = "userid")
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
}
