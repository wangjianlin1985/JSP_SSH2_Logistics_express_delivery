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
@Table(name = "t_Cheliang")
public class Cheliang implements Serializable
{
    private static final long serialVersionUID = -7141419035239709511L;
    private long id;
    private String chepai;
    private String chexing;
    private String guihao;
    private String chengyungongsi;
    private String diaoduzhuangtai;
    private int chelianglock;
    private Date createtime;
    
    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(final Date createtime) {
        this.createtime = createtime;
    }
    
    public int getChelianglock() {
        return this.chelianglock;
    }
    
    public void setChelianglock(final int chelianglock) {
        this.chelianglock = chelianglock;
    }
    
    @Id
    @GeneratedValue
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getChepai() {
        return this.chepai;
    }
    
    public void setChepai(final String chepai) {
        this.chepai = chepai;
    }
    
    public String getChexing() {
        return this.chexing;
    }
    
    public void setChexing(final String chexing) {
        this.chexing = chexing;
    }
    
    public String getGuihao() {
        return this.guihao;
    }
    
    public void setGuihao(final String guihao) {
        this.guihao = guihao;
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
}
