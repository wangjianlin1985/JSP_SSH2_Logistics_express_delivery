// 
// 
// 

package dao;

import java.util.List;
import model.Cheliang;

public interface CheliangDao
{
    void insertBean(Cheliang p0);
    
    void deleteBean(Cheliang p0);
    
    void updateBean(Cheliang p0);
    
    Cheliang selectBean(String p0);
    
    List<Cheliang> selectBeanList(int p0, int p1, String p2);
    
    int selectBeanCount(String p0);
}
