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
@Table(name = "t_Cangzu")
public class Cangzu implements Serializable
{
    private static final long serialVersionUID = -7141419035239709511L;
    private long id;
    private double zujin;
    
    @Id
    @GeneratedValue
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public double getZujin() {
        return this.zujin;
    }
    
    public void setZujin(final double zujin) {
        this.zujin = zujin;
    }
}
