// 
// 
// 

package dao;

import java.util.List;
import model.Diaodu;

public interface DiaoduDao
{
    void insertBean(Diaodu p0);
    
    void deleteBean(Diaodu p0);
    
    void updateBean(Diaodu p0);
    
    Diaodu selectBean(String p0);
    
    List<Diaodu> selectBeanList(int p0, int p1, String p2);
    
    int selectBeanCount(String p0);
}
