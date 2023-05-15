// 
// 
// 

package dao;

import java.util.List;
import model.User;

public interface UserDao
{
    void insertBean(User p0);
    
    void deleteBean(User p0);
    
    void updateBean(User p0);
    
    User selectBean(String p0);
    
    List<User> selectBeanList(int p0, int p1, String p2);
    
    int selectBeanCount(String p0);
}
