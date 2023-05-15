// 
// 
// 

package util;

import org.springframework.web.context.WebApplicationContext;
import model.Cangzu;
import dao.CangzuDao;
import model.User;
import dao.UserDao;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Util
{
    public static String getTime() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = new Date();
        return sdf.format(date.getTime());
    }
    
    public static String getTime2() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = new Date();
        return sdf.format(date.getTime());
    }
    
    public static String getTime3() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        final Date date = new Date();
        return sdf.format(date.getTime());
    }
    
    public static String getTime4(final Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
    public static void copyFile(final File src, final File dst) {
        try {
            final int BUFFER_SIZE = 16384;
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
                final byte[] buffer = new byte[BUFFER_SIZE];
                int byteRead = 0;
                while ((byteRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, byteRead);
                }
            }
            finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String tiaozhuan(final String msg, final String url, final String id) {
        final String tiaozhuan = "{\"statusCode\":\"200\", \"message\":\"" + msg + "\"," + "\"navTabId\":\"" + id + "\", \"rel\":\"" + id + "\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"" + url + "\"}";
        return tiaozhuan;
    }
    
    public static String tiaozhuan2(final String msg, final String url, final String id) {
        final String tiaozhuan = "{\"statusCode\":\"200\", \"message\":\"" + msg + "\"," + "\"navTabId\":\"" + id + "\", \"rel\":\"" + id + "\", \"callbackType\":\"\",\"forwardUrl\":\"" + url + "\"}";
        return tiaozhuan;
    }
    
    public static void init(final HttpServletRequest request) {
        final WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        final UserDao userDao = (UserDao)app.getBean("userDao");
        User user = userDao.selectBean(" where username='admin' and userlock=0  ");
        if (user == null) {
            user = new User();
            user.setPassword("111111");
            user.setRole(1);
            user.setTruename("admin");
            user.setUsername("admin");
            userDao.insertBean(user);
        }
        final CangzuDao cangzuDao = (CangzuDao)app.getBean("cangzuDao");
        final int count = cangzuDao.selectBeanCount(" where 1=1 ");
        if (count == 0) {
            final Cangzu cangzu = new Cangzu();
            cangzu.setZujin(10.0);
            cangzuDao.insertBean(cangzu);
        }
    }
}
