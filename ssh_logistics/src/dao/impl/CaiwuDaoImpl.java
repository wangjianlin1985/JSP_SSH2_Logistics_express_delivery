// 
// 
// 

package dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.util.List;
import model.Caiwu;
import dao.CaiwuDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CaiwuDaoImpl extends HibernateDaoSupport implements CaiwuDao
{
    public void deleteBean(final Caiwu Caiwu) {
        this.getHibernateTemplate().delete((Object)Caiwu);
    }
    
    public void insertBean(final Caiwu Caiwu) {
        this.getHibernateTemplate().save((Object)Caiwu);
    }
    
    public Caiwu selectBean(final String where) {
        final List<Caiwu> list = (List<Caiwu>)this.getHibernateTemplate().find("from Caiwu " + where);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    
    public int selectBeanCount(final String where) {
        final long count = (long)this.getHibernateTemplate().find("select count(*) from Caiwu " + where).get(0);
        return (int)count;
    }
    
    public List<Caiwu> selectBeanList(final int start, final int limit, final String where) {
        return (List<Caiwu>)this.getHibernateTemplate().executeFind((HibernateCallback)new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                final List<Caiwu> list = (List<Caiwu>)session.createQuery("from Caiwu " + where).setFirstResult(start).setMaxResults(limit).list();
                return list;
            }
        });
    }
    
    public void updateBean(final Caiwu Caiwu) {
        this.getHibernateTemplate().update((Object)Caiwu);
    }
}
