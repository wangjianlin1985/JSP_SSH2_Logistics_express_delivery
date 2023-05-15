// 
// 
// 

package dao;

import java.util.List;
import model.Kucun;

public interface KucunDao
{
    void insertBean(Kucun p0);
    
    void deleteBean(Kucun p0);
    
    void updateBean(Kucun p0);
    
    Kucun selectBean(String p0);
    
    List<Kucun> selectBeanList(int p0, int p1, String p2);
    
    int selectBeanCount(String p0);
}
