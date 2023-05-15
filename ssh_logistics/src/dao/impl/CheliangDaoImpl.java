// 
// 
// 

package dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.util.List;
import model.Cheliang;
import dao.CheliangDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CheliangDaoImpl extends HibernateDaoSupport implements CheliangDao
{
    public void deleteBean(final Cheliang Cheliang) {
        this.getHibernateTemplate().delete((Object)Cheliang);
    }
    
    public void insertBean(final Cheliang Cheliang) {
        this.getHibernateTemplate().save((Object)Cheliang);
    }
    
    public Cheliang selectBean(final String where) {
        final List<Cheliang> list = (List<Cheliang>)this.getHibernateTemplate().find("from Cheliang " + where);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    
    public int selectBeanCount(final String where) {
        final long count = (long)this.getHibernateTemplate().find("select count(*) from Cheliang " + where).get(0);
        return (int)count;
    }
    
    public List<Cheliang> selectBeanList(final int start, final int limit, final String where) {
        return (List<Cheliang>)this.getHibernateTemplate().executeFind((HibernateCallback)new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                final List<Cheliang> list = (List<Cheliang>)session.createQuery("from Cheliang " + where).setFirstResult(start).setMaxResults(limit).list();
                return list;
            }
        });
    }
    
    public void updateBean(final Cheliang Cheliang) {
        this.getHibernateTemplate().update((Object)Cheliang);
    }
}
