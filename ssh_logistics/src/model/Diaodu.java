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
@Table(name = "t_Diaodu")
public class Diaodu implements Serializable
{
    private static final long serialVersionUID = -7141419035239709511L;
    private long id;
    private Dingdan dingdan;
    private Cheliang cheliang;
    private Dingchedan dingchedan;
    private Date createtime;
    private String chengyungongsi;
    private String xianluming;
    private String yaoqiudaidashijian;
    private String diaoduzhuangtai;
    private String diaoduriqi;
    private double yunshufeiyong;
    private User user;
    private User user2;
    private User user3;
    
    @ManyToOne
    @JoinColumn(name = "user3id")
    public User getUser3() {
        return this.user3;
    }
    
    public void setUser3(final User user3) {
        this.user3 = user3;
    }
    
    @ManyToOne
    @JoinColumn(name = "user2id")
    public User getUser2() {
        return this.user2;
    }
    
    public void setUser2(final User user2) {
        this.user2 = user2;
    }
    
    @ManyToOne
    @JoinColumn(name = "userid")
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    @Id
    @GeneratedValue
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getChengyungongsi() {
        return this.chengyungongsi;
    }
    
    public void setChengyungongsi(final String chengyungongsi) {
        this.chengyungongsi = chengyungongsi;
    }
    
    public String getDiaoduzhuangtai() {
        return this.diaoduzhuangtai;
    }
    
    public void setDiaoduzhuangtai(final String diaoduzhuangtai) {
        this.diaoduzhuangtai = diaoduzhuangtai;
    }
    
    @ManyToOne
    @JoinColumn(name = "dingdanid")
    public Dingdan getDingdan() {
        return this.dingdan;
    }
    
    public void setDingdan(final Dingdan dingdan) {
        this.dingdan = dingdan;
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
    @JoinColumn(name = "dingchedanid")
    public Dingchedan getDingchedan() {
        return this.dingchedan;
    }
    
    public void setDingchedan(final Dingchedan dingchedan) {
        this.dingchedan = dingchedan;
    }
    
    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(final Date createtime) {
        this.createtime = createtime;
    }
    
    public String getXianluming() {
        return this.xianluming;
    }
    
    public void setXianluming(final String xianluming) {
        this.xianluming = xianluming;
    }
    
    public String getYaoqiudaidashijian() {
        return this.yaoqiudaidashijian;
    }
    
    public void setYaoqiudaidashijian(final String yaoqiudaidashijian) {
        this.yaoqiudaidashijian = yaoqiudaidashijian;
    }
    
    public String getDiaoduriqi() {
        return this.diaoduriqi;
    }
    
    public void setDiaoduriqi(final String diaoduriqi) {
        this.diaoduriqi = diaoduriqi;
    }
    
    public double getYunshufeiyong() {
        return this.yunshufeiyong;
    }
    
    public void setYunshufeiyong(final double yunshufeiyong) {
        this.yunshufeiyong = yunshufeiyong;
    }
}
