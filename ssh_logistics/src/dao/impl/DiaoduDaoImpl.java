// 
// 
// 

package dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.util.List;
import model.Diaodu;
import dao.DiaoduDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DiaoduDaoImpl extends HibernateDaoSupport implements DiaoduDao
{
    public void deleteBean(final Diaodu Diaodu) {
        this.getHibernateTemplate().delete((Object)Diaodu);
    }
    
    public void insertBean(final Diaodu Diaodu) {
        this.getHibernateTemplate().save((Object)Diaodu);
    }
    
    public Diaodu selectBean(final String where) {
        final List<Diaodu> list = (List<Diaodu>)this.getHibernateTemplate().find("from Diaodu " + where);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    
    public int selectBeanCount(final String where) {
        final long count = (long)this.getHibernateTemplate().find("select count(*) from Diaodu " + where).get(0);
        return (int)count;
    }
    
    public List<Diaodu> selectBeanList(final int start, final int limit, final String where) {
        return (List<Diaodu>)this.getHibernateTemplate().executeFind((HibernateCallback)new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                final List<Diaodu> list = (List<Diaodu>)session.createQuery("from Diaodu " + where).setFirstResult(start).setMaxResults(limit).list();
                return list;
            }
        });
    }
    
    public void updateBean(final Diaodu Diaodu) {
        this.getHibernateTemplate().update((Object)Diaodu);
    }
}
