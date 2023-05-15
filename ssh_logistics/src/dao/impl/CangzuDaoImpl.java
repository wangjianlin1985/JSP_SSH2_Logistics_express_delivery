// 
// 
// 

package dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.util.List;
import model.Cangzu;
import dao.CangzuDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CangzuDaoImpl extends HibernateDaoSupport implements CangzuDao
{
    public void deleteBean(final Cangzu Cangzu) {
        this.getHibernateTemplate().delete((Object)Cangzu);
    }
    
    public void insertBean(final Cangzu Cangzu) {
        this.getHibernateTemplate().save((Object)Cangzu);
    }
    
    public Cangzu selectBean(final String where) {
        final List<Cangzu> list = (List<Cangzu>)this.getHibernateTemplate().find("from Cangzu " + where);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    
    public int selectBeanCount(final String where) {
        final long count = (long)this.getHibernateTemplate().find("select count(*) from Cangzu " + where).get(0);
        return (int)count;
    }
    
    public List<Cangzu> selectBeanList(final int start, final int limit, final String where) {
        return (List<Cangzu>)this.getHibernateTemplate().executeFind((HibernateCallback)new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                final List<Cangzu> list = (List<Cangzu>)session.createQuery("from Cangzu " + where).setFirstResult(start).setMaxResults(limit).list();
                return list;
            }
        });
    }
    
    public void updateBean(final Cangzu Cangzu) {
        this.getHibernateTemplate().update((Object)Cangzu);
    }
}
