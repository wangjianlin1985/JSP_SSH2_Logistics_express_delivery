// 
// 
// 

package dao;

import java.util.List;
import model.Kehu;

public interface KehuDao
{
    void insertBean(Kehu p0);
    
    void deleteBean(Kehu p0);
    
    void updateBean(Kehu p0);
    
    Kehu selectBean(String p0);
    
    List<Kehu> selectBeanList(int p0, int p1, String p2);
    
    int selectBeanCount(String p0);
}
