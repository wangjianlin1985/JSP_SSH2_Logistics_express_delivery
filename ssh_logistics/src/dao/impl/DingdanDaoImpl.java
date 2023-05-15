// 
// 
// 

package dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.util.List;
import model.Dingdan;
import dao.DingdanDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DingdanDaoImpl extends HibernateDaoSupport implements DingdanDao
{
    public void deleteBean(final Dingdan Dingdan) {
        this.getHibernateTemplate().delete((Object)Dingdan);
    }
    
    public void insertBean(final Dingdan Dingdan) {
        this.getHibernateTemplate().save((Object)Dingdan);
    }
    
    public Dingdan selectBean(final String where) {
        final List<Dingdan> list = (List<Dingdan>)this.getHibernateTemplate().find("from Dingdan " + where);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    
    public int selectBeanCount(final String where) {
        final long count = (long)this.getHibernateTemplate().find("select count(*) from Dingdan " + where).get(0);
        return (int)count;
    }
    
    public List<Dingdan> selectBeanList(final int start, final int limit, final String where) {
        return (List<Dingdan>)this.getHibernateTemplate().executeFind((HibernateCallback)new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                final List<Dingdan> list = (List<Dingdan>)session.createQuery("from Dingdan " + where).setFirstResult(start).setMaxResults(limit).list();
                return list;
            }
        });
    }
    
    public void updateBean(final Dingdan Dingdan) {
        this.getHibernateTemplate().update((Object)Dingdan);
    }
}
