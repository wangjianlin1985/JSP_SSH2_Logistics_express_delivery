// 
// 
// 

package dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.util.List;
import model.User;
import dao.UserDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao
{
    public void deleteBean(final User User) {
        this.getHibernateTemplate().delete((Object)User);
    }
    
    public void insertBean(final User User) {
        this.getHibernateTemplate().save((Object)User);
    }
    
    public User selectBean(final String where) {
        final List<User> list = (List<User>)this.getHibernateTemplate().find("from User " + where);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    
    public int selectBeanCount(final String where) {
        final long count = (long)this.getHibernateTemplate().find("select count(*) from User " + where).get(0);
        return (int)count;
    }
    
    public List<User> selectBeanList(final int start, final int limit, final String where) {
        return (List<User>)this.getHibernateTemplate().executeFind((HibernateCallback)new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                final List<User> list = (List<User>)session.createQuery("from User " + where).setFirstResult(start).setMaxResults(limit).list();
                return list;
            }
        });
    }
    
    public void updateBean(final User User) {
        this.getHibernateTemplate().update((Object)User);
    }
}
