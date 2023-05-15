// 
// 
// 

package dao;

import java.util.List;
import model.Dingchedan;

public interface DingchedanDao
{
    void insertBean(Dingchedan p0);
    
    void deleteBean(Dingchedan p0);
    
    void updateBean(Dingchedan p0);
    
    Dingchedan selectBean(String p0);
    
    List<Dingchedan> selectBeanList(int p0, int p1, String p2);
    
    int selectBeanCount(String p0);
}
