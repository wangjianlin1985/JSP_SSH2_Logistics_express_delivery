// 
// 
// 

package action;

import java.util.Iterator;
import model.Caiwu;
import model.Dingdan;
import model.Kehu;
import model.Kucun;
import java.util.List;
import java.util.Date;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import javax.servlet.http.HttpServletRequest;
import util.Util;
import org.apache.struts2.ServletActionContext;
import dao.KehuDao;
import dao.KucunDao;
import dao.DingdanDao;
import dao.CaiwuDao;
import dao.UserDao;
import com.opensymphony.xwork2.ActionSupport;

public class ManageAction extends ActionSupport
{
    private static final long serialVersionUID = -4304509122548259589L;
    private UserDao userDao;
    private String url;
    private CaiwuDao caiwuDao;
    private DingdanDao dingdanDao;
    private KucunDao kucunDao;
    private KehuDao kehuDao;
    
    public ManageAction() {
        this.url = "./";
    }
    
    public CaiwuDao getCaiwuDao() {
        return this.caiwuDao;
    }
    
    public void setCaiwuDao(final CaiwuDao caiwuDao) {
        this.caiwuDao = caiwuDao;
    }
    
    public UserDao getUserDao() {
        return this.userDao;
    }
    
    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    public String index() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        Util.init(request);
        return "success1";
    }
    
    public String login2() {
        this.setUrl("login.jsp");
        return "success";
    }
    
    public String login() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final String role = request.getParameter("role");
        final User user = this.userDao.selectBean(" where username = '" + username + "' and password= '" + password + "' and userlock=0 and role=" + role);
        if (user != null) {
            final HttpSession session = request.getSession();
            session.setAttribute("user", (Object)user);
            this.setUrl("index");
            return "redirect";
        }
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.getWriter().print("<script language=javascript>alert('\u5458\u5de5\u53f7\u6216\u8005\u5bc6\u7801\u9519\u8bef');window.location.href='method!login2';</script>");
        return null;
    }
    
    public String loginout() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final HttpSession session = request.getSession();
        session.removeAttribute("user");
        this.setUrl("login.jsp");
        return "success";
    }
    
    public String changepwd() {
        this.setUrl("user/user.jsp");
        return "success";
    }
    
    public void changepwd2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        final HttpSession session = request.getSession();
        final User u = (User)session.getAttribute("user");
        final String password1 = request.getParameter("password1");
        final String password2 = request.getParameter("password2");
        final String password3 = request.getParameter("password3");
        final User bean = this.userDao.selectBean(" where username= '" + u.getUsername() + "' and password= '" + password1 + "'");
        if (!password2.equals(password3)) {
            out.print(Util.tiaozhuan2("\u4e24\u6b21\u8f93\u5165\u5bc6\u7801\u4e0d\u4e00\u81f4", "index", ""));
            out.flush();
            out.close();
        }
        else if (bean != null) {
            bean.setPassword(password2);
            this.userDao.updateBean(bean);
            out.print(Util.tiaozhuan2("\u64cd\u4f5c\u6210\u529f", "index", ""));
            out.flush();
            out.close();
        }
        else {
            out.print(Util.tiaozhuan2("\u539f\u5bc6\u7801\u9519\u8bef", "index", ""));
            out.flush();
            out.close();
        }
    }
    
    public String userlist() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where userlock=0 and role=0  order by id desc ";
        final String where2 = " where userlock=0 and role=0  ";
        final int total = this.userDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.userDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        this.setUrl("user/userlist.jsp");
        return "success";
    }
    
    public void userdelete() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final User bean = this.userDao.selectBean(" where id= " + request.getParameter("id"));
        bean.setUsername(String.valueOf(bean.getUsername()) + "_delete");
        bean.setUserlock(1);
        this.userDao.updateBean(bean);
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        out.print(Util.tiaozhuan2("\u64cd\u4f5c\u6210\u529f", "method!userlist", "userlist"));
        out.flush();
        out.close();
    }
    
    public String useradd() {
        this.setUrl("user/useradd.jsp");
        return "success";
    }
    
    public void useradd2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String username = request.getParameter("username");
        final String truename = request.getParameter("truename");
        User bean = this.userDao.selectBean(" where username= '" + username + "'");
        if (bean == null) {
            bean = new User();
            bean.setCreatetime(new Date());
            bean.setPassword("111111");
            bean.setRole(0);
            bean.setTruename(truename);
            bean.setUsername(username);
            this.userDao.insertBean(bean);
            final HttpServletResponse resp = ServletActionContext.getResponse();
            resp.setCharacterEncoding("utf-8");
            final PrintWriter out = resp.getWriter();
            out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "method!userlist", "userlist"));
            out.flush();
            out.close();
        }
        else {
            final HttpServletResponse resp = ServletActionContext.getResponse();
            resp.setCharacterEncoding("utf-8");
            final PrintWriter out = resp.getWriter();
            out.print(Util.tiaozhuan("\u64cd\u4f5c\u5931\u8d25\uff0c\u8be5\u7528\u6237\u540d\u5df2\u7ecf\u5b58\u5728", "method!userlist", "userlist"));
            out.flush();
            out.close();
        }
    }
    
    public String userupdate() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final User bean = this.userDao.selectBean(" where id= " + request.getParameter("id"));
        request.setAttribute("bean", (Object)bean);
        this.setUrl("user/userupdate.jsp");
        return "success";
    }
    
    public void userupdate2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final User bean = this.userDao.selectBean(" where id= " + request.getParameter("id"));
        final String truename = request.getParameter("truename");
        final String password = request.getParameter("password");
        bean.setTruename(truename);
        bean.setPassword(password);
        this.userDao.updateBean(bean);
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "method!userlist", "userlist"));
        out.flush();
        out.close();
    }
    
    public DingdanDao getDingdanDao() {
        return this.dingdanDao;
    }
    
    public void setDingdanDao(final DingdanDao dingdanDao) {
        this.dingdanDao = dingdanDao;
    }
    
    public String dingdanlist() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String dingdanhao = request.getParameter("dingdanhao");
        final String huowumingchen = request.getParameter("huowumingchen");
        final String kehuxingming = request.getParameter("kehuxingming");
        final String fahuodi = request.getParameter("fahuodi");
        final String mudidi = request.getParameter("mudidi");
        final String shouhuozhuangtai = request.getParameter("shouhuozhuangtai");
        final String duizhangzhuangtai = request.getParameter("duizhangzhuangtai");
        final String time1 = request.getParameter("time1");
        final String time2 = request.getParameter("time2");
        final StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        final StringBuffer sb2 = new StringBuffer();
        sb2.append(" where ");
        if (dingdanhao != null && !"".equals(dingdanhao)) {
            sb.append("dingdanhao like '%" + dingdanhao + "%'");
            sb.append(" and ");
            sb2.append("dingdanhao like '%" + dingdanhao + "%'");
            sb2.append(" and ");
            request.setAttribute("dingdanhao", (Object)dingdanhao);
        }
        if (huowumingchen != null && !"".equals(huowumingchen)) {
            sb.append("huowumingchen like '%" + huowumingchen + "%'");
            sb.append(" and ");
            sb2.append("huowumingchen like '%" + huowumingchen + "%'");
            sb2.append(" and ");
            request.setAttribute("huowumingchen", (Object)huowumingchen);
        }
        if (kehuxingming != null && !"".equals(kehuxingming)) {
            sb.append("kehuxingming like '%" + kehuxingming + "%'");
            sb.append(" and ");
            sb2.append("kehuxingming like '%" + kehuxingming + "%'");
            sb2.append(" and ");
            request.setAttribute("kehuxingming", (Object)kehuxingming);
        }
        if (fahuodi != null && !"".equals(fahuodi)) {
            sb.append("fahuodi like '%" + fahuodi + "%'");
            sb.append(" and ");
            sb2.append("fahuodi like '%" + fahuodi + "%'");
            sb2.append(" and ");
            request.setAttribute("fahuodi", (Object)fahuodi);
        }
        if (mudidi != null && !"".equals(mudidi)) {
            sb.append("mudidi like '%" + mudidi + "%'");
            sb.append(" and ");
            sb2.append("mudidi like '%" + mudidi + "%'");
            sb2.append(" and ");
            request.setAttribute("mudidi", (Object)mudidi);
        }
        if (shouhuozhuangtai != null && !"".equals(shouhuozhuangtai)) {
            sb.append("shouhuozhuangtai like '%" + shouhuozhuangtai + "%'");
            sb.append(" and ");
            sb2.append("shouhuozhuangtai like '%" + shouhuozhuangtai + "%'");
            sb2.append(" and ");
            request.setAttribute("shouhuozhuangtai", (Object)shouhuozhuangtai);
        }
        if (duizhangzhuangtai != null && !"".equals(duizhangzhuangtai)) {
            sb.append("duizhangzhuangtai like '%" + duizhangzhuangtai + "%'");
            sb.append(" and ");
            sb2.append("duizhangzhuangtai like '%" + duizhangzhuangtai + "%'");
            sb2.append(" and ");
            request.setAttribute("duizhangzhuangtai", (Object)duizhangzhuangtai);
        }
        if (time1 != null && !"".equals(time1)) {
            sb.append("riqi >=  '" + time1 + "'");
            sb.append(" and ");
            sb2.append("riqi >=  '" + time1 + "'");
            sb2.append(" and ");
            request.setAttribute("time1", (Object)time1);
        }
        if (time2 != null && !"".equals(time2)) {
            sb.append("riqi <  '" + time2 + "'");
            sb.append(" and ");
            sb2.append("riqi <  '" + time2 + "'");
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
        final int total = this.dingdanDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.dingdanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"method!dingdanlist");
        this.setUrl("dingdan/dingdanlist.jsp");
        return "success";
    }
    
    public String dingdanlist2() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String dingdanhao = request.getParameter("dingdanhao");
        final String huowumingchen = request.getParameter("huowumingchen");
        final String kehuxingming = request.getParameter("kehuxingming");
        final String fahuodi = request.getParameter("fahuodi");
        final String mudidi = request.getParameter("mudidi");
        final String shouhuozhuangtai = request.getParameter("shouhuozhuangtai");
        final String duizhangzhuangtai = request.getParameter("duizhangzhuangtai");
        final String time1 = request.getParameter("time1");
        final String time2 = request.getParameter("time2");
        final StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        final StringBuffer sb2 = new StringBuffer();
        sb2.append(" where ");
        if (dingdanhao != null && !"".equals(dingdanhao)) {
            sb.append("dingdanhao like '%" + dingdanhao + "%'");
            sb.append(" and ");
            sb2.append("dingdanhao like '%" + dingdanhao + "%'");
            sb2.append(" and ");
            request.setAttribute("dingdanhao", (Object)dingdanhao);
        }
        if (huowumingchen != null && !"".equals(huowumingchen)) {
            sb.append("huowumingchen like '%" + huowumingchen + "%'");
            sb.append(" and ");
            sb2.append("huowumingchen like '%" + huowumingchen + "%'");
            sb2.append(" and ");
            request.setAttribute("huowumingchen", (Object)huowumingchen);
        }
        if (kehuxingming != null && !"".equals(kehuxingming)) {
            sb.append("kehuxingming like '%" + kehuxingming + "%'");
            sb.append(" and ");
            sb2.append("kehuxingming like '%" + kehuxingming + "%'");
            sb2.append(" and ");
            request.setAttribute("kehuxingming", (Object)kehuxingming);
        }
        if (fahuodi != null && !"".equals(fahuodi)) {
            sb.append("fahuodi like '%" + fahuodi + "%'");
            sb.append(" and ");
            sb2.append("fahuodi like '%" + fahuodi + "%'");
            sb2.append(" and ");
            request.setAttribute("fahuodi", (Object)fahuodi);
        }
        if (mudidi != null && !"".equals(mudidi)) {
            sb.append("mudidi like '%" + mudidi + "%'");
            sb.append(" and ");
            sb2.append("mudidi like '%" + mudidi + "%'");
            sb2.append(" and ");
            request.setAttribute("mudidi", (Object)mudidi);
        }
        if (shouhuozhuangtai != null && !"".equals(shouhuozhuangtai)) {
            sb.append("shouhuozhuangtai like '%" + shouhuozhuangtai + "%'");
            sb.append(" and ");
            sb2.append("shouhuozhuangtai like '%" + shouhuozhuangtai + "%'");
            sb2.append(" and ");
            request.setAttribute("shouhuozhuangtai", (Object)shouhuozhuangtai);
        }
        if (duizhangzhuangtai != null && !"".equals(duizhangzhuangtai)) {
            sb.append("duizhangzhuangtai like '%" + duizhangzhuangtai + "%'");
            sb.append(" and ");
            sb2.append("duizhangzhuangtai like '%" + duizhangzhuangtai + "%'");
            sb2.append(" and ");
            request.setAttribute("duizhangzhuangtai", (Object)duizhangzhuangtai);
        }
        if (time1 != null && !"".equals(time1)) {
            sb.append("riqi >=  '" + time1 + "'");
            sb.append(" and ");
            sb2.append("riqi >=  '" + time1 + "'");
            sb2.append(" and ");
            request.setAttribute("time1", (Object)time1);
        }
        if (time2 != null && !"".equals(time2)) {
            sb.append("riqi <  '" + time2 + "'");
            sb.append(" and ");
            sb2.append("riqi <  '" + time2 + "'");
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
        final int total = this.dingdanDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.dingdanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"method!dingdanlist2");
        this.setUrl("dingdan/dingdanlist2.jsp");
        return "success";
    }
    
    public KucunDao getKucunDao() {
        return this.kucunDao;
    }
    
    public void setKucunDao(final KucunDao kucunDao) {
        this.kucunDao = kucunDao;
    }
    
    public String dingdanadd() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final List<Kucun> list = this.kucunDao.selectBeanList(0, 9999, " where 1=1 ");
        request.setAttribute("list", (Object)list);
        final List<Kehu> list2 = this.kehuDao.selectBeanList(0, 9999, " where kehulock=0 ");
        request.setAttribute("list2", (Object)list2);
        this.setUrl("dingdan/dingdanadd.jsp");
        return "success";
    }
    
    public void dingdanadd2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String fahuodi = request.getParameter("fahuodi");
        final String kucunid = request.getParameter("kucun");
        final Kucun kucun = this.kucunDao.selectBean("  where id= " + kucunid);
        final String jine = request.getParameter("jine");
        final String kehuid = request.getParameter("kehu");
        final Kehu kehu = this.kehuDao.selectBean(" where id= " + kehuid);
        final String liaxifangshi = request.getParameter("liaxifangshi");
        final String mianji = request.getParameter("mianji");
        final String mudidi = request.getParameter("mudidi");
        final String shuliang = request.getParameter("shuliang");
        final String tiji = request.getParameter("tiji");
        final String zhongliang = request.getParameter("zhongliang");
        final Dingdan bean = new Dingdan();
        bean.setDingdanhao(new StringBuilder(String.valueOf(new Date().getTime())).toString());
        bean.setDuizhangzhuangtai("\u672a\u5bf9\u5e10");
        bean.setFahuodi(fahuodi);
        bean.setHuowubianhao(kucun.getBianhao());
        bean.setHuowumingchen(kucun.getShangpingming());
        bean.setJine(Double.parseDouble(jine));
        bean.setKehuxingming(kehu.getKehumingcheng());
        bean.setLiaxifangshi(liaxifangshi);
        bean.setMianji(Double.parseDouble(mianji));
        bean.setMudidi(mudidi);
        bean.setRiqi(new Date());
        bean.setShouhuozhuangtai("\u672a\u53d1\u8d27");
        bean.setShuliang(Integer.parseInt(shuliang));
        bean.setTiji(Double.parseDouble(tiji));
        bean.setZhongliang(Double.parseDouble(zhongliang));
        bean.setKehu(kehu);
        this.dingdanDao.insertBean(bean);
        kehu.setJiaoyicishu(kehu.getJiaoyicishu() + 1);
        kehu.setJiaoyijine(kehu.getJiaoyijine() + Double.parseDouble(jine));
        this.kehuDao.updateBean(kehu);
        final Caiwu caiwu = new Caiwu();
        caiwu.setDingdan(bean);
        caiwu.setCreatetime(new Date());
        caiwu.setJine(Double.parseDouble(jine));
        caiwu.setType("\u8d22\u52a1\u6536\u5165");
        final HttpSession session = request.getSession();
        final User user = (User)session.getAttribute("user");
        caiwu.setUser(user);
        caiwu.setLeixing("\u8ba2\u5355\u6536\u5165");
        this.caiwuDao.insertBean(caiwu);
        final HttpServletResponse resp = ServletActionContext.getResponse();
        resp.setCharacterEncoding("utf-8");
        final PrintWriter out = resp.getWriter();
        out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "method!dingdanlist", "dingdanlist"));
        out.flush();
        out.close();
    }
    
    public String dingdanupdate() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Dingdan bean = this.dingdanDao.selectBean(" where id= " + request.getParameter("id"));
        if ("\u672a\u53d1\u8d27".equals(bean.getShouhuozhuangtai())) {
            request.setAttribute("bean", (Object)bean);
            this.setUrl("dingdan/dingdanupdate.jsp");
        }
        else {
            this.setUrl("dingdan/dingdanupdate2.jsp");
        }
        return "success";
    }
    
    public void dingdanupdate2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Dingdan bean = this.dingdanDao.selectBean(" where id= " + request.getParameter("id"));
        final Kehu kehu = bean.getKehu();
        kehu.setJiaoyijine(kehu.getJiaoyijine() - bean.getJine());
        final String fahuodi = request.getParameter("fahuodi");
        final String jine = request.getParameter("jine");
        final String liaxifangshi = request.getParameter("liaxifangshi");
        final String mianji = request.getParameter("mianji");
        final String mudidi = request.getParameter("mudidi");
        final String shuliang = request.getParameter("shuliang");
        final String tiji = request.getParameter("tiji");
        final String zhongliang = request.getParameter("zhongliang");
        bean.setFahuodi(fahuodi);
        bean.setJine(Double.parseDouble(jine));
        bean.setLiaxifangshi(liaxifangshi);
        bean.setMianji(Double.parseDouble(mianji));
        bean.setMudidi(mudidi);
        bean.setShuliang(Integer.parseInt(shuliang));
        bean.setTiji(Double.parseDouble(tiji));
        bean.setZhongliang(Double.parseDouble(zhongliang));
        this.dingdanDao.updateBean(bean);
        kehu.setJiaoyijine(kehu.getJiaoyijine() + Double.parseDouble(jine));
        this.kehuDao.updateBean(kehu);
        final Caiwu caiwu = this.caiwuDao.selectBean(" where  dingdan.id=" + bean.getId());
        caiwu.setJine(Double.parseDouble(jine));
        this.caiwuDao.updateBean(caiwu);
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "method!dingdanlist", "dingdanlist"));
        out.flush();
        out.close();
    }
    
    public void dingdandelete() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Dingdan bean = this.dingdanDao.selectBean(" where id= " + request.getParameter("id"));
        if ("\u672a\u53d1\u8d27".equals(bean.getShouhuozhuangtai())) {
            final Kehu kehu = bean.getKehu();
            kehu.setJiaoyijine(kehu.getJiaoyijine() - bean.getJine());
            kehu.setJiaoyicishu(kehu.getJiaoyicishu() - 1);
            final List<Caiwu> list = this.caiwuDao.selectBeanList(0, 99, " where dingdan.id= " + bean.getId());
            for (final Caiwu caiwu : list) {
                this.caiwuDao.deleteBean(caiwu);
            }
            this.dingdanDao.deleteBean(bean);
            final HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("utf-8");
            final PrintWriter out = response.getWriter();
            out.print(Util.tiaozhuan2("\u64cd\u4f5c\u6210\u529f", "method!dingdanlist", "dingdanlist"));
            out.flush();
            out.close();
        }
        else {
            final HttpServletResponse response2 = ServletActionContext.getResponse();
            response2.setCharacterEncoding("utf-8");
            final PrintWriter out2 = response2.getWriter();
            out2.print(Util.tiaozhuan2("\u5220\u9664\u5931\u8d25\uff0c\u8be5\u8ba2\u5355\u5df2\u7ecf\u8fdb\u5165\u7269\u6d41\u914d\u9001", "method!dingdanlist", "dingdanlist"));
            out2.flush();
            out2.close();
        }
    }
    
    public KehuDao getKehuDao() {
        return this.kehuDao;
    }
    
    public void setKehuDao(final KehuDao kehuDao) {
        this.kehuDao = kehuDao;
    }
    
    public String kehulist() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where kehulock=0   order by id desc ";
        final String where2 = " where kehulock=0   ";
        final int total = this.kehuDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.kehuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        this.setUrl("kehu/kehulist.jsp");
        return "success";
    }
    
    public void kehudelete() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Kehu bean = this.kehuDao.selectBean(" where id= " + request.getParameter("id"));
        bean.setKehulock(1);
        this.kehuDao.updateBean(bean);
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        out.print(Util.tiaozhuan2("\u64cd\u4f5c\u6210\u529f", "method!kehulist", "kehulist"));
        out.flush();
        out.close();
    }
    
    public String kehuadd() {
        this.setUrl("kehu/kehuadd.jsp");
        return "success";
    }
    
    public void kehuadd2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String gongsimingchen = request.getParameter("gongsimingchen");
        final String kehumingcheng = request.getParameter("kehumingcheng");
        final Kehu bean = new Kehu();
        bean.setCreatetime(new Date());
        bean.setGongsimingchen(gongsimingchen);
        bean.setKehumingcheng(kehumingcheng);
        this.kehuDao.insertBean(bean);
        final HttpServletResponse resp = ServletActionContext.getResponse();
        resp.setCharacterEncoding("utf-8");
        final PrintWriter out = resp.getWriter();
        out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "method!kehulist", "kehulist"));
        out.flush();
        out.close();
    }
    
    public String kehuupdate() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Kehu bean = this.kehuDao.selectBean(" where id= " + request.getParameter("id"));
        request.setAttribute("bean", (Object)bean);
        this.setUrl("kehu/kehuupdate.jsp");
        return "success";
    }
    
    public void kehuupdate2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Kehu bean = this.kehuDao.selectBean(" where id= " + request.getParameter("id"));
        final String gongsimingchen = request.getParameter("gongsimingchen");
        final String kehumingcheng = request.getParameter("kehumingcheng");
        bean.setGongsimingchen(gongsimingchen);
        bean.setKehumingcheng(kehumingcheng);
        this.kehuDao.updateBean(bean);
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "method!kehulist", "kehulist"));
        out.flush();
        out.close();
    }
}
