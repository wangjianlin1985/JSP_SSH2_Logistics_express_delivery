// 
// 
// 

package action;

import model.Dingdan;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Util;
import model.Cangzu;
import model.Caiwu;
import model.Kucun;
import model.User;
import java.util.Date;
import model.ChuRuku;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import dao.CangzuDao;
import dao.DingdanDao;
import dao.CaiwuDao;
import dao.KucunDao;
import dao.ChuRukuDao;
import com.opensymphony.xwork2.ActionSupport;

public class CangchuAction extends ActionSupport
{
    private static final long serialVersionUID = -4304509122548259589L;
    private ChuRukuDao churukuDao;
    private KucunDao kucunDao;
    private String url;
    private CaiwuDao caiwuDao;
    private DingdanDao dingdanDao;
    private CangzuDao cangzuDao;
    
    public CangchuAction() {
        this.url = "./";
    }
    
    public CaiwuDao getCaiwuDao() {
        return this.caiwuDao;
    }
    
    public void setCaiwuDao(final CaiwuDao caiwuDao) {
        this.caiwuDao = caiwuDao;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    public String rukulist() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where type=1  order by id desc ";
        final String where2 = " where type=1  ";
        final int total = this.churukuDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.churukuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        this.setUrl("ruku/rukulist.jsp");
        return "success";
    }
    
    public String rukuadd() {
        this.setUrl("ruku/rukuadd.jsp");
        return "success";
    }
    
    public void rukuadd2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String jiage = request.getParameter("jiage");
        final String shangpingming = request.getParameter("shangpingming");
        final String shuliang = request.getParameter("shuliang");
        final String zhanyongmianji = request.getParameter("zhanyongmianji");
        final ChuRuku bean = new ChuRuku();
        bean.setCreatetime(new Date());
        bean.setJiage(Double.parseDouble(jiage));
        bean.setShangpingming(shangpingming);
        bean.setShuliang(Integer.parseInt(shuliang));
        final HttpSession session = request.getSession();
        final User user = (User)session.getAttribute("user");
        bean.setUser(user);
        bean.setZhanyongmianji(Double.parseDouble(zhanyongmianji));
        bean.setType(1);
        this.churukuDao.insertBean(bean);
        Kucun kucun = this.kucunDao.selectBean(" where  shangpingming='" + shangpingming + "'");
        if (kucun == null) {
            kucun = new Kucun();
            kucun.setBianhao(new StringBuilder(String.valueOf(new Date().getTime())).toString());
            kucun.setShangpingming(shangpingming);
            kucun.setShuliang(Integer.parseInt(shuliang));
            this.kucunDao.insertBean(kucun);
        }
        else {
            kucun.setShuliang(kucun.getShuliang() + Integer.parseInt(shuliang));
            this.kucunDao.updateBean(kucun);
        }
        final Caiwu caiwu = new Caiwu();
        caiwu.setChuruku(bean);
        caiwu.setCreatetime(new Date());
        caiwu.setJine(Double.parseDouble(jiage));
        caiwu.setType("\u8d22\u52a1\u652f\u51fa");
        caiwu.setUser(user);
        caiwu.setLeixing("\u5165\u5e93\u652f\u51fa");
        this.caiwuDao.insertBean(caiwu);
        final Caiwu caiwu2 = new Caiwu();
        caiwu2.setKucun(kucun);
        caiwu2.setCreatetime(new Date());
        final double feiyong = this.cangzuDao.selectBeanList(0, 99, " where 1=1 ").get(0).getZujin();
        caiwu2.setJine(Double.parseDouble(zhanyongmianji) * feiyong);
        caiwu2.setType("\u8d22\u52a1\u652f\u51fa");
        caiwu2.setUser(user);
        caiwu2.setLeixing("\u5e93\u5b58\u652f\u51fa");
        this.caiwuDao.insertBean(caiwu2);
        final HttpServletResponse resp = ServletActionContext.getResponse();
        resp.setCharacterEncoding("utf-8");
        final PrintWriter out = resp.getWriter();
        out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "cangchumethod!rukulist", "rukulist"));
        out.flush();
        out.close();
    }
    
    public ChuRukuDao getChurukuDao() {
        return this.churukuDao;
    }
    
    public void setChurukuDao(final ChuRukuDao churukuDao) {
        this.churukuDao = churukuDao;
    }
    
    public KucunDao getKucunDao() {
        return this.kucunDao;
    }
    
    public void setKucunDao(final KucunDao kucunDao) {
        this.kucunDao = kucunDao;
    }
    
    public String kucunlist() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String shangpingming = request.getParameter("shangpingming");
        final StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        final StringBuffer sb2 = new StringBuffer();
        sb2.append(" where ");
        if (shangpingming != null && !"".equals(shangpingming)) {
            sb.append("shangpingming like '%" + shangpingming + "%'");
            sb.append(" and ");
            sb2.append("shangpingming like '%" + shangpingming + "%'");
            sb2.append(" and ");
            request.setAttribute("shangpingming", (Object)shangpingming);
        }
        sb.append(" 1=1 order by id desc");
        final String where = sb.toString();
        sb2.append(" 1=1 ");
        final String where2 = sb2.toString();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final int total = this.kucunDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.kucunDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"method!kucunlist");
        this.setUrl("kucun/kucunlist.jsp");
        return "success";
    }
    
    public DingdanDao getDingdanDao() {
        return this.dingdanDao;
    }
    
    public void setDingdanDao(final DingdanDao dingdanDao) {
        this.dingdanDao = dingdanDao;
    }
    
    public String chukulist() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where shouhuozhuangtai='\u672a\u53d1\u8d27' order by id desc ";
        final String where2 = " where shouhuozhuangtai='\u672a\u53d1\u8d27' ";
        final int total = this.dingdanDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.dingdanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"cangchumethod!chukulist");
        this.setUrl("chuku/chukulist.jsp");
        return "success";
    }
    
    public void chukudelete() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final Dingdan bean = this.dingdanDao.selectBean(" where id= " + request.getParameter("id"));
        final Kucun kucun = this.kucunDao.selectBean(" where shangpingming = '" + bean.getHuowumingchen() + "' ");
        if (bean.getShuliang() > kucun.getShuliang()) {
            bean.setShouhuozhuangtai("\u5e93\u5b58\u6570\u91cf\u4e0d\u591f,\u8f6c\u5165\u8ba2\u5355\u4ed3\u5e93");
            this.dingdanDao.updateBean(bean);
            final PrintWriter out = response.getWriter();
            out.print(Util.tiaozhuan2("\u64cd\u4f5c\u5931\u8d25\uff0c\u8ba2\u5355\u7684\u8d27\u7269\u6570\u91cf\u5927\u4e8e\u5e93\u5b58\u6570\u91cf\uff0c\u8f6c\u5165\u8ba2\u5355\u4ed3\u5e93", "cangchumethod!chukulist", "chukulist"));
            out.flush();
            out.close();
        }
        else {
            bean.setShouhuozhuangtai("\u5546\u54c1\u5df2\u51fa\u5e93\uff0c\u8f6c\u5165\u8c03\u5ea6\u4e2d\u5fc3");
            this.dingdanDao.updateBean(bean);
            kucun.setShuliang(kucun.getShuliang() - bean.getShuliang());
            this.kucunDao.updateBean(kucun);
            final ChuRuku churuku = new ChuRuku();
            churuku.setCreatetime(new Date());
            churuku.setJiage(bean.getJine());
            churuku.setShangpingming(bean.getHuowumingchen());
            churuku.setShuliang(bean.getShuliang());
            churuku.setType(2);
            final HttpSession session = request.getSession();
            final User user = (User)session.getAttribute("user");
            churuku.setUser(user);
            churuku.setDingdanhao(bean.getDingdanhao());
            this.churukuDao.insertBean(churuku);
            final PrintWriter out2 = response.getWriter();
            out2.print(Util.tiaozhuan2("\u64cd\u4f5c\u6210\u529f", "cangchumethod!chukulist", "chukulist"));
            out2.flush();
            out2.close();
        }
    }
    
    public void chukudelete2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final Dingdan bean = this.dingdanDao.selectBean(" where id= " + request.getParameter("id"));
        final Kucun kucun = this.kucunDao.selectBean(" where shangpingming = '" + bean.getHuowumingchen() + "' ");
        if (bean.getShuliang() > kucun.getShuliang()) {
            bean.setShouhuozhuangtai("\u5e93\u5b58\u6570\u91cf\u4e0d\u591f,\u8f6c\u5165\u8ba2\u5355\u4ed3\u5e93");
            this.dingdanDao.updateBean(bean);
            final PrintWriter out = response.getWriter();
            out.print(Util.tiaozhuan2("\u64cd\u4f5c\u5931\u8d25\uff0c\u8ba2\u5355\u7684\u8d27\u7269\u6570\u91cf\u5927\u4e8e\u5e93\u5b58\u6570\u91cf\uff0c\u8f6c\u5165\u8ba2\u5355\u4ed3\u5e93", "cangchumethod!chukulist3", "chukulist3"));
            out.flush();
            out.close();
        }
        else {
            bean.setShouhuozhuangtai("\u5546\u54c1\u5df2\u51fa\u5e93\uff0c\u8f6c\u5165\u8c03\u5ea6\u4e2d\u5fc3");
            this.dingdanDao.updateBean(bean);
            kucun.setShuliang(kucun.getShuliang() - bean.getShuliang());
            this.kucunDao.updateBean(kucun);
            final ChuRuku churuku = new ChuRuku();
            churuku.setCreatetime(new Date());
            churuku.setJiage(bean.getJine());
            churuku.setShangpingming(bean.getHuowumingchen());
            churuku.setShuliang(bean.getShuliang());
            churuku.setType(2);
            final HttpSession session = request.getSession();
            final User user = (User)session.getAttribute("user");
            churuku.setUser(user);
            churuku.setDingdanhao(bean.getDingdanhao());
            this.churukuDao.insertBean(churuku);
            final PrintWriter out2 = response.getWriter();
            out2.print(Util.tiaozhuan2("\u64cd\u4f5c\u6210\u529f", "cangchumethod!chukulist3", "chukulist3"));
            out2.flush();
            out2.close();
        }
    }
    
    public String chukulist2() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where type=2  order by id desc ";
        final String where2 = " where type=2  ";
        final int total = this.churukuDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.churukuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"cangchumethod!chukulist2");
        this.setUrl("chuku/chukulist2.jsp");
        return "success";
    }
    
    public String chukulist3() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where shouhuozhuangtai='\u5e93\u5b58\u6570\u91cf\u4e0d\u591f,\u8f6c\u5165\u8ba2\u5355\u4ed3\u5e93' order by id desc ";
        final String where2 = " where shouhuozhuangtai='\u5e93\u5b58\u6570\u91cf\u4e0d\u591f,\u8f6c\u5165\u8ba2\u5355\u4ed3\u5e93' ";
        final int total = this.dingdanDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.dingdanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"cangchumethod!chukulist3");
        this.setUrl("chuku/chukulist3.jsp");
        return "success";
    }
    
    public String chukulist4() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String leixing = request.getParameter("leixing");
        final String shangpingming = request.getParameter("shangpingming");
        final String username = request.getParameter("username");
        final String time1 = request.getParameter("time1");
        final String time2 = request.getParameter("time2");
        final StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        final StringBuffer sb2 = new StringBuffer();
        sb2.append(" where ");
        if (leixing != null && !"".equals(leixing)) {
            sb.append("type =" + Integer.parseInt(leixing));
            sb.append(" and ");
            sb2.append("type =" + Integer.parseInt(leixing));
            sb2.append(" and ");
            request.setAttribute("leixing", (Object)leixing);
        }
        if (shangpingming != null && !"".equals(shangpingming)) {
            sb.append("shangpingming like '%" + shangpingming + "%'");
            sb.append(" and ");
            sb2.append("shangpingming like '%" + shangpingming + "%'");
            sb2.append(" and ");
            request.setAttribute("shangpingming", (Object)shangpingming);
        }
        if (username != null && !"".equals(username)) {
            sb.append("user.username like '%" + username + "%'");
            sb.append(" and ");
            sb2.append("user.username like '%" + username + "%'");
            sb2.append(" and ");
            request.setAttribute("username", (Object)username);
        }
        if (time1 != null && !"".equals(time1)) {
            sb.append("createtime >=  '" + time1 + "'");
            sb.append(" and ");
            sb2.append("createtime >=  '" + time1 + "'");
            sb2.append(" and ");
            request.setAttribute("time1", (Object)time1);
        }
        if (time2 != null && !"".equals(time2)) {
            sb.append("createtime <  '" + time2 + "'");
            sb.append(" and ");
            sb2.append("createtime <  '" + time2 + "'");
            sb2.append(" and ");
            request.setAttribute("time2", (Object)time2);
        }
        sb.append(" 1=1  order by id desc ");
        final String where = sb.toString();
        sb2.append(" 1=1  ");
        final String where2 = sb2.toString();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final int total = this.churukuDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.churukuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"cangchumethod!chukulist4");
        this.setUrl("chuku/chukulist4.jsp");
        return "success";
    }
    
    public CangzuDao getCangzuDao() {
        return this.cangzuDao;
    }
    
    public void setCangzuDao(final CangzuDao cangzuDao) {
        this.cangzuDao = cangzuDao;
    }
    
    public String cangzulist() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where 1=1  order by id desc ";
        final String where2 = " where 1=1  ";
        final int total = this.cangzuDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.cangzuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        this.setUrl("cangzu/cangzulist.jsp");
        return "success";
    }
    
    public String cangzuupdate() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Cangzu bean = this.cangzuDao.selectBean(" where id= " + request.getParameter("id"));
        request.setAttribute("bean", (Object)bean);
        this.setUrl("cangzu/cangzuupdate.jsp");
        return "success";
    }
    
    public void cangzuupdate2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Cangzu bean = this.cangzuDao.selectBean(" where id= " + request.getParameter("id"));
        final String zujin = request.getParameter("zujin");
        bean.setZujin(Double.parseDouble(zujin));
        this.cangzuDao.updateBean(bean);
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "cangchumethod!cangzulist", "cangzulist"));
        out.flush();
        out.close();
    }
}
