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
@Table(name = "t_Dingdan")
public class Dingdan implements Serializable
{
    private static final long serialVersionUID = -7141419035239709511L;
    private long id;
    private String dingdanhao;
    private String kehuxingming;
    private Date riqi;
    private String liaxifangshi;
    private String fahuodi;
    private String mudidi;
    private double jine;
    private String huowubianhao;
    private String huowumingchen;
    private int shuliang;
    private double mianji;
    private double tiji;
    private double zhongliang;
    private String shouhuozhuangtai;
    private String duizhangzhuangtai;
    private int dingchedangeshu;
    private Kehu kehu;
    
    @ManyToOne
    @JoinColumn(name = "kehuid")
    public Kehu getKehu() {
        return this.kehu;
    }
    
    public void setKehu(final Kehu kehu) {
        this.kehu = kehu;
    }
    
    public int getDingchedangeshu() {
        return this.dingchedangeshu;
    }
    
    public void setDingchedangeshu(final int dingchedangeshu) {
        this.dingchedangeshu = dingchedangeshu;
    }
    
    @Id
    @GeneratedValue
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getDingdanhao() {
        return this.dingdanhao;
    }
    
    public void setDingdanhao(final String dingdanhao) {
        this.dingdanhao = dingdanhao;
    }
    
    public String getKehuxingming() {
        return this.kehuxingming;
    }
    
    public void setKehuxingming(final String kehuxingming) {
        this.kehuxingming = kehuxingming;
    }
    
    public Date getRiqi() {
        return this.riqi;
    }
    
    public void setRiqi(final Date riqi) {
        this.riqi = riqi;
    }
    
    public String getLiaxifangshi() {
        return this.liaxifangshi;
    }
    
    public void setLiaxifangshi(final String liaxifangshi) {
        this.liaxifangshi = liaxifangshi;
    }
    
    public String getFahuodi() {
        return this.fahuodi;
    }
    
    public void setFahuodi(final String fahuodi) {
        this.fahuodi = fahuodi;
    }
    
    public String getMudidi() {
        return this.mudidi;
    }
    
    public void setMudidi(final String mudidi) {
        this.mudidi = mudidi;
    }
    
    public double getJine() {
        return this.jine;
    }
    
    public void setJine(final double jine) {
        this.jine = jine;
    }
    
    public String getHuowubianhao() {
        return this.huowubianhao;
    }
    
    public void setHuowubianhao(final String huowubianhao) {
        this.huowubianhao = huowubianhao;
    }
    
    public String getHuowumingchen() {
        return this.huowumingchen;
    }
    
    public void setHuowumingchen(final String huowumingchen) {
        this.huowumingchen = huowumingchen;
    }
    
    public int getShuliang() {
        return this.shuliang;
    }
    
    public void setShuliang(final int shuliang) {
        this.shuliang = shuliang;
    }
    
    public double getMianji() {
        return this.mianji;
    }
    
    public void setMianji(final double mianji) {
        this.mianji = mianji;
    }
    
    public double getTiji() {
        return this.tiji;
    }
    
    public void setTiji(final double tiji) {
        this.tiji = tiji;
    }
    
    public double getZhongliang() {
        return this.zhongliang;
    }
    
    public void setZhongliang(final double zhongliang) {
        this.zhongliang = zhongliang;
    }
    
    public String getShouhuozhuangtai() {
        return this.shouhuozhuangtai;
    }
    
    public void setShouhuozhuangtai(final String shouhuozhuangtai) {
        this.shouhuozhuangtai = shouhuozhuangtai;
    }
    
    public String getDuizhangzhuangtai() {
        return this.duizhangzhuangtai;
    }
    
    public void setDuizhangzhuangtai(final String duizhangzhuangtai) {
        this.duizhangzhuangtai = duizhangzhuangtai;
    }
}
