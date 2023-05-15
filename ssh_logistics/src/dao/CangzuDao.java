// 
// 
// 

package dao;

import java.util.List;
import model.Cangzu;

public interface CangzuDao
{
    void insertBean(Cangzu p0);
    
    void deleteBean(Cangzu p0);
    
    void updateBean(Cangzu p0);
    
    Cangzu selectBean(String p0);
    
    List<Cangzu> selectBeanList(int p0, int p1, String p2);
    
    int selectBeanCount(String p0);
}
