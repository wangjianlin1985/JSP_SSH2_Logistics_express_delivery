// 
// 
// 

package dao;

import java.util.List;
import model.ChuRuku;

public interface ChuRukuDao
{
    void insertBean(ChuRuku p0);
    
    void deleteBean(ChuRuku p0);
    
    void updateBean(ChuRuku p0);
    
    ChuRuku selectBean(String p0);
    
    List<ChuRuku> selectBeanList(int p0, int p1, String p2);
    
    int selectBeanCount(String p0);
}
