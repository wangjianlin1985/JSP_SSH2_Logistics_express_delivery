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
@Table(name = "t_User")
public class User implements Serializable
{
    private static final long serialVersionUID = -7141419035239709511L;
    private long id;
    private String username;
    private String password;
    private Date createtime;
    private String truename;
    private int role;
    private int userlock;
    
    public int getUserlock() {
        return this.userlock;
    }
    
    public void setUserlock(final int userlock) {
        this.userlock = userlock;
    }
    
    public int getRole() {
        return this.role;
    }
    
    public void setRole(final int role) {
        this.role = role;
    }
    
    @Id
    @GeneratedValue
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(final Date createtime) {
        this.createtime = createtime;
    }
    
    public String getTruename() {
        return this.truename;
    }
    
    public void setTruename(final String truename) {
        this.truename = truename;
    }
}
