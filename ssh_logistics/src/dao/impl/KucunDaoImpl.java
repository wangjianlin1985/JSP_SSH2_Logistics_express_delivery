// 
// 
// 

package dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.util.List;
import model.Kucun;
import dao.KucunDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class KucunDaoImpl extends HibernateDaoSupport implements KucunDao
{
    public void deleteBean(final Kucun Kucun) {
        this.getHibernateTemplate().delete((Object)Kucun);
    }
    
    public void insertBean(final Kucun Kucun) {
        this.getHibernateTemplate().save((Object)Kucun);
    }
    
    public Kucun selectBean(final String where) {
        final List<Kucun> list = (List<Kucun>)this.getHibernateTemplate().find("from Kucun " + where);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    
    public int selectBeanCount(final String where) {
        final long count = (long)this.getHibernateTemplate().find("select count(*) from Kucun " + where).get(0);
        return (int)count;
    }
    
    public List<Kucun> selectBeanList(final int start, final int limit, final String where) {
        return (List<Kucun>)this.getHibernateTemplate().executeFind((HibernateCallback)new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                final List<Kucun> list = (List<Kucun>)session.createQuery("from Kucun " + where).setFirstResult(start).setMaxResults(limit).list();
                return list;
            }
        });
    }
    
    public void updateBean(final Kucun Kucun) {
        this.getHibernateTemplate().update((Object)Kucun);
    }
}
