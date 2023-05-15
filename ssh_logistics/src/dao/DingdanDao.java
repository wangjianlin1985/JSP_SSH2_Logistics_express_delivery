// 
// 
// 

package dao;

import java.util.List;
import model.Dingdan;

public interface DingdanDao
{
    void insertBean(Dingdan p0);
    
    void deleteBean(Dingdan p0);
    
    void updateBean(Dingdan p0);
    
    Dingdan selectBean(String p0);
    
    List<Dingdan> selectBeanList(int p0, int p1, String p2);
    
    int selectBeanCount(String p0);
}
