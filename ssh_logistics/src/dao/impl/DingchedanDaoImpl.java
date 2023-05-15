// 
// 
// 

package dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.util.List;
import model.Dingchedan;
import dao.DingchedanDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DingchedanDaoImpl extends HibernateDaoSupport implements DingchedanDao
{
    public void deleteBean(final Dingchedan Dingchedan) {
        this.getHibernateTemplate().delete((Object)Dingchedan);
    }
    
    public void insertBean(final Dingchedan Dingchedan) {
        this.getHibernateTemplate().save((Object)Dingchedan);
    }
    
    public Dingchedan selectBean(final String where) {
        final List<Dingchedan> list = (List<Dingchedan>)this.getHibernateTemplate().find("from Dingchedan " + where);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    
    public int selectBeanCount(final String where) {
        final long count = (long)this.getHibernateTemplate().find("select count(*) from Dingchedan " + where).get(0);
        return (int)count;
    }
    
    public List<Dingchedan> selectBeanList(final int start, final int limit, final String where) {
        return (List<Dingchedan>)this.getHibernateTemplate().executeFind((HibernateCallback)new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                final List<Dingchedan> list = (List<Dingchedan>)session.createQuery("from Dingchedan " + where).setFirstResult(start).setMaxResults(limit).list();
                return list;
            }
        });
    }
    
    public void updateBean(final Dingchedan Dingchedan) {
        this.getHibernateTemplate().update((Object)Dingchedan);
    }
}
