// 
// 
// 

package model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "t_Kucun")
public class Kucun implements Serializable
{
    private static final long serialVersionUID = -7141419035239709511L;
    private long id;
    private String bianhao;
    private String shangpingming;
    private int shuliang;
    
    public String getBianhao() {
        return this.bianhao;
    }
    
    public void setBianhao(final String bianhao) {
        this.bianhao = bianhao;
    }
    
    @Id
    @GeneratedValue
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
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
}
