// 
// 
// 

package dao;

import java.util.List;
import model.Caiwu;

public interface CaiwuDao
{
    void insertBean(Caiwu p0);
    
    void deleteBean(Caiwu p0);
    
    void updateBean(Caiwu p0);
    
    Caiwu selectBean(String p0);
    
    List<Caiwu> selectBeanList(int p0, int p1, String p2);
    
    int selectBeanCount(String p0);
}
