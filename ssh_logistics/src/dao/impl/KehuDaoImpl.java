// 
// 
// 

package dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.util.List;
import model.Kehu;
import dao.KehuDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class KehuDaoImpl extends HibernateDaoSupport implements KehuDao
{
    public void deleteBean(final Kehu Kehu) {
        this.getHibernateTemplate().delete((Object)Kehu);
    }
    
    public void insertBean(final Kehu Kehu) {
        this.getHibernateTemplate().save((Object)Kehu);
    }
    
    public Kehu selectBean(final String where) {
        final List<Kehu> list = (List<Kehu>)this.getHibernateTemplate().find("from Kehu " + where);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    
    public int selectBeanCount(final String where) {
        final long count = (long)this.getHibernateTemplate().find("select count(*) from Kehu " + where).get(0);
        return (int)count;
    }
    
    public List<Kehu> selectBeanList(final int start, final int limit, final String where) {
        return (List<Kehu>)this.getHibernateTemplate().executeFind((HibernateCallback)new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                final List<Kehu> list = (List<Kehu>)session.createQuery("from Kehu " + where).setFirstResult(start).setMaxResults(limit).list();
                return list;
            }
        });
    }
    
    public void updateBean(final Kehu Kehu) {
        this.getHibernateTemplate().update((Object)Kehu);
    }
}
