// 
// 
// 

package dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.util.List;
import model.ChuRuku;
import dao.ChuRukuDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ChuRukuDaoImpl extends HibernateDaoSupport implements ChuRukuDao
{
    public void deleteBean(final ChuRuku ChuRuku) {
        this.getHibernateTemplate().delete((Object)ChuRuku);
    }
    
    public void insertBean(final ChuRuku ChuRuku) {
        this.getHibernateTemplate().save((Object)ChuRuku);
    }
    
    public ChuRuku selectBean(final String where) {
        final List<ChuRuku> list = (List<ChuRuku>)this.getHibernateTemplate().find("from ChuRuku " + where);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    
    public int selectBeanCount(final String where) {
        final long count = (long)this.getHibernateTemplate().find("select count(*) from ChuRuku " + where).get(0);
        return (int)count;
    }
    
    public List<ChuRuku> selectBeanList(final int start, final int limit, final String where) {
        return (List<ChuRuku>)this.getHibernateTemplate().executeFind((HibernateCallback)new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                final List<ChuRuku> list = (List<ChuRuku>)session.createQuery("from ChuRuku " + where).setFirstResult(start).setMaxResults(limit).list();
                return list;
            }
        });
    }
    
    public void updateBean(final ChuRuku ChuRuku) {
        this.getHibernateTemplate().update((Object)ChuRuku);
    }
}
