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
@Table(name = "t_Dingchedan")
public class Dingchedan implements Serializable
{
    private static final long serialVersionUID = -7141419035239709511L;
    private long id;
    private String dingchedanhao;
    private String dingchexingzhi;
    private String yunshuxingzhi;
    private String lianxiren;
    private String dianhua;
    private String chuanzhen;
    private String youjian;
    private Cheliang cheliang;
    private Dingdan dingdan;
    private Date createtime;
    private String fenpeizhuangtai;
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "userid")
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public String getFenpeizhuangtai() {
        return this.fenpeizhuangtai;
    }
    
    public void setFenpeizhuangtai(final String fenpeizhuangtai) {
        this.fenpeizhuangtai = fenpeizhuangtai;
    }
    
    @Id
    @GeneratedValue
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getDingchedanhao() {
        return this.dingchedanhao;
    }
    
    public void setDingchedanhao(final String dingchedanhao) {
        this.dingchedanhao = dingchedanhao;
    }
    
    public String getDingchexingzhi() {
        return this.dingchexingzhi;
    }
    
    public void setDingchexingzhi(final String dingchexingzhi) {
        this.dingchexingzhi = dingchexingzhi;
    }
    
    public String getYunshuxingzhi() {
        return this.yunshuxingzhi;
    }
    
    public void setYunshuxingzhi(final String yunshuxingzhi) {
        this.yunshuxingzhi = yunshuxingzhi;
    }
    
    public String getLianxiren() {
        return this.lianxiren;
    }
    
    public void setLianxiren(final String lianxiren) {
        this.lianxiren = lianxiren;
    }
    
    public String getDianhua() {
        return this.dianhua;
    }
    
    public void setDianhua(final String dianhua) {
        this.dianhua = dianhua;
    }
    
    public String getChuanzhen() {
        return this.chuanzhen;
    }
    
    public void setChuanzhen(final String chuanzhen) {
        this.chuanzhen = chuanzhen;
    }
    
    public String getYoujian() {
        return this.youjian;
    }
    
    public void setYoujian(final String youjian) {
        this.youjian = youjian;
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
    @JoinColumn(name = "dingdanid")
    public Dingdan getDingdan() {
        return this.dingdan;
    }
    
    public void setDingdan(final Dingdan dingdan) {
        this.dingdan = dingdan;
    }
    
    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(final Date createtime) {
        this.createtime = createtime;
    }
}
