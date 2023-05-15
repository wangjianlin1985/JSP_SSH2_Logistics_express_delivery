// 
// 
// 

package action;

import model.Caiwu;
import model.Diaodu;
import javax.servlet.http.HttpSession;
import model.Dingdan;
import model.User;
import model.Dingchedan;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import model.Cheliang;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import dao.DiaoduDao;
import dao.DingchedanDao;
import dao.DingdanDao;
import dao.CaiwuDao;
import dao.CheliangDao;
import com.opensymphony.xwork2.ActionSupport;

public class DiaoduAction extends ActionSupport
{
    private static final long serialVersionUID = -4304509122548259589L;
    private CheliangDao cheliangDao;
    private String url;
    private CaiwuDao caiwuDao;
    private DingdanDao dingdanDao;
    private DingchedanDao dingchedanDao;
    private DiaoduDao diaoduDao;
    
    public DiaoduAction() {
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
    
    public CheliangDao getCheliangDao() {
        return this.cheliangDao;
    }
    
    public void setCheliangDao(final CheliangDao cheliangDao) {
        this.cheliangDao = cheliangDao;
    }
    
    public String chelianglist() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where chelianglock=0   order by id desc ";
        final String where2 = " where chelianglock=0   ";
        final int total = this.cheliangDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.cheliangDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"diaodumethod!chelianglist");
        this.setUrl("cheliang/chelianglist.jsp");
        return "success";
    }
    
    public void cheliangdelete() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Cheliang bean = this.cheliangDao.selectBean(" where id= " + request.getParameter("id"));
        bean.setChepai(String.valueOf(bean.getChepai()) + "_delete");
        bean.setChelianglock(1);
        this.cheliangDao.updateBean(bean);
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        out.print(Util.tiaozhuan2("\u64cd\u4f5c\u6210\u529f", "diaodumethod!chelianglist", "chelianglist"));
        out.flush();
        out.close();
    }
    
    public String cheliangadd() {
        this.setUrl("cheliang/cheliangadd.jsp");
        return "success";
    }
    
    public void cheliangadd2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String chengyungongsi = request.getParameter("chengyungongsi");
        final String chepai = request.getParameter("chepai");
        final String chexing = request.getParameter("chexing");
        final String guihao = request.getParameter("guihao");
        Cheliang bean = this.cheliangDao.selectBean(" where chepai= '" + chepai + "'");
        if (bean == null) {
            bean = new Cheliang();
            bean.setChengyungongsi(chengyungongsi);
            bean.setChepai(chepai);
            bean.setChexing(chexing);
            bean.setCreatetime(new Date());
            bean.setGuihao(guihao);
            bean.setDiaoduzhuangtai("\u672a\u8c03\u5ea6");
            this.cheliangDao.insertBean(bean);
            final HttpServletResponse resp = ServletActionContext.getResponse();
            resp.setCharacterEncoding("utf-8");
            final PrintWriter out = resp.getWriter();
            out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "diaodumethod!chelianglist", "chelianglist"));
            out.flush();
            out.close();
        }
        else {
            final HttpServletResponse resp = ServletActionContext.getResponse();
            resp.setCharacterEncoding("utf-8");
            final PrintWriter out = resp.getWriter();
            out.print(Util.tiaozhuan("\u64cd\u4f5c\u5931\u8d25\uff0c\u8be5\u8f66\u724c\u53f7\u5df2\u7ecf\u5b58\u5728", "diaodumethod!chelianglist", "chelianglist"));
            out.flush();
            out.close();
        }
    }
    
    public String cheliangupdate() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Cheliang bean = this.cheliangDao.selectBean(" where id= " + request.getParameter("id"));
        request.setAttribute("bean", (Object)bean);
        this.setUrl("cheliang/cheliangupdate.jsp");
        return "success";
    }
    
    public void cheliangupdate2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Cheliang bean = this.cheliangDao.selectBean(" where id= " + request.getParameter("id"));
        final String chengyungongsi = request.getParameter("chengyungongsi");
        final String chexing = request.getParameter("chexing");
        final String guihao = request.getParameter("guihao");
        bean.setChengyungongsi(chengyungongsi);
        bean.setChexing(chexing);
        bean.setGuihao(guihao);
        this.cheliangDao.updateBean(bean);
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "diaodumethod!chelianglist", "chelianglist"));
        out.flush();
        out.close();
    }
    
    public DingdanDao getDingdanDao() {
        return this.dingdanDao;
    }
    
    public void setDingdanDao(final DingdanDao dingdanDao) {
        this.dingdanDao = dingdanDao;
    }
    
    public String peisonglist() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where shouhuozhuangtai='\u5546\u54c1\u5df2\u51fa\u5e93\uff0c\u8f6c\u5165\u8c03\u5ea6\u4e2d\u5fc3' order by id desc ";
        final String where2 = " where shouhuozhuangtai='\u5546\u54c1\u5df2\u51fa\u5e93\uff0c\u8f6c\u5165\u8c03\u5ea6\u4e2d\u5fc3' ";
        final int total = this.dingdanDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.dingdanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"diaodumethod!peisonglist");
        this.setUrl("diaodu/peisonglist.jsp");
        return "success";
    }
    
    public DingchedanDao getDingchedanDao() {
        return this.dingchedanDao;
    }
    
    public void setDingchedanDao(final DingchedanDao dingchedanDao) {
        this.dingchedanDao = dingchedanDao;
    }
    
    public String dingchedanadd() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("id", (Object)request.getParameter("id"));
        this.setUrl("diaodu/dingchedanadd.jsp");
        return "success";
    }
    
    public void dingchedanadd2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Dingdan dingdan = this.dingdanDao.selectBean(" where id=  " + request.getParameter("id"));
        final String chuanzhen = request.getParameter("chuanzhen");
        final String dianhua = request.getParameter("dianhua");
        final String dingchexingzhi = request.getParameter("dingchexingzhi");
        final String lianxiren = request.getParameter("lianxiren");
        final String youjian = request.getParameter("youjian");
        final String yunshuxingzhi = request.getParameter("yunshuxingzhi");
        final Dingchedan bean = new Dingchedan();
        bean.setChuanzhen(chuanzhen);
        bean.setCreatetime(new Date());
        bean.setDianhua(dianhua);
        bean.setDingchedanhao(new StringBuilder(String.valueOf(new Date().getTime())).toString());
        bean.setDingchexingzhi(dingchexingzhi);
        bean.setDingdan(dingdan);
        bean.setLianxiren(lianxiren);
        bean.setYoujian(youjian);
        bean.setYunshuxingzhi(yunshuxingzhi);
        bean.setFenpeizhuangtai("\u672a\u5206\u914d\u8f66\u8f86");
        final HttpSession session = request.getSession();
        final User user = (User)session.getAttribute("user");
        bean.setUser(user);
        this.dingchedanDao.insertBean(bean);
        dingdan.setDingchedangeshu(this.dingchedanDao.selectBeanCount(" where dingdan.id =" + dingdan.getId()));
        this.dingdanDao.updateBean(dingdan);
        final HttpServletResponse resp = ServletActionContext.getResponse();
        resp.setCharacterEncoding("utf-8");
        final PrintWriter out = resp.getWriter();
        out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "diaodumethod!peisonglist", "peisonglist"));
        out.flush();
        out.close();
    }
    
    public String diaodulist() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where fenpeizhuangtai='\u672a\u5206\u914d\u8f66\u8f86' order by fenpeizhuangtai desc,id desc ";
        final String where2 = " where fenpeizhuangtai='\u672a\u5206\u914d\u8f66\u8f86' ";
        final int total = this.dingchedanDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.dingchedanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"diaodumethod!diaodulist");
        this.setUrl("diaodu/diaodulist.jsp");
        return "success";
    }
    
    public DiaoduDao getDiaoduDao() {
        return this.diaoduDao;
    }
    
    public void setDiaoduDao(final DiaoduDao diaoduDao) {
        this.diaoduDao = diaoduDao;
    }
    
    public String diaoduadd() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("id", (Object)request.getParameter("id"));
        request.setAttribute("list", (Object)this.cheliangDao.selectBeanList(0, 9999, " where diaoduzhuangtai='\u672a\u8c03\u5ea6' and chelianglock=0"));
        this.setUrl("diaodu/diaoduadd.jsp");
        return "success";
    }
    
    public void diaoduadd2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Dingchedan dingchedan = this.dingchedanDao.selectBean(" where id=  " + request.getParameter("id"));
        final String cheliang = request.getParameter("cheliang");
        final String xianluming = request.getParameter("xianluming");
        final String yaoqiudaidashijian = request.getParameter("yaoqiudaidashijian");
        final String yunshufeiyong = request.getParameter("yunshufeiyong");
        final String chengyungongsi = request.getParameter("chengyungongsi");
        final Diaodu bean = new Diaodu();
        final Cheliang cheliangs = this.cheliangDao.selectBean(" where id= " + cheliang);
        bean.setCheliang(cheliangs);
        bean.setChengyungongsi(chengyungongsi);
        bean.setCreatetime(new Date());
        bean.setDiaoduzhuangtai("\u672a\u8c03\u5ea6");
        bean.setDingchedan(dingchedan);
        bean.setDingdan(dingchedan.getDingdan());
        final HttpSession session = request.getSession();
        final User user = (User)session.getAttribute("user");
        bean.setUser(user);
        bean.setXianluming(xianluming);
        bean.setYaoqiudaidashijian(yaoqiudaidashijian);
        bean.setYunshufeiyong(Double.parseDouble(yunshufeiyong));
        this.diaoduDao.insertBean(bean);
        dingchedan.setCheliang(cheliangs);
        dingchedan.setFenpeizhuangtai("\u5df2\u5206\u914d\u8f66\u8f86");
        this.dingchedanDao.updateBean(dingchedan);
        final Caiwu caiwu = new Caiwu();
        caiwu.setCheliang(cheliangs);
        caiwu.setCreatetime(new Date());
        caiwu.setJine(Double.parseDouble(yunshufeiyong));
        caiwu.setType("\u8d22\u52a1\u652f\u51fa");
        caiwu.setUser(user);
        caiwu.setLeixing("\u8f66\u8f86\u8fd0\u8f93\u652f\u51fa");
        this.caiwuDao.insertBean(caiwu);
        final HttpServletResponse resp = ServletActionContext.getResponse();
        resp.setCharacterEncoding("utf-8");
        final PrintWriter out = resp.getWriter();
        out.print(Util.tiaozhuan("\u64cd\u4f5c\u6210\u529f", "diaodumethod!diaodulist", "diaodulist"));
        out.flush();
        out.close();
    }
    
    public String diaodulist2() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where diaoduzhuangtai='\u672a\u8c03\u5ea6' order by id desc ";
        final String where2 = " where diaoduzhuangtai='\u672a\u8c03\u5ea6' ";
        final int total = this.diaoduDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.diaoduDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"diaodumethod!diaodulist2");
        this.setUrl("diaodu/diaodulist2.jsp");
        return "success";
    }
    
    public void diaoduupdate() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Diaodu bean = this.diaoduDao.selectBean(" where id= " + request.getParameter("id"));
        bean.setDiaoduzhuangtai("\u5df2\u8c03\u5ea6");
        bean.setDiaoduriqi(Util.getTime());
        final HttpSession session = request.getSession();
        final User user = (User)session.getAttribute("user");
        bean.setUser2(user);
        this.diaoduDao.updateBean(bean);
        final Dingdan dingdan = bean.getDingdan();
        dingdan.setShouhuozhuangtai("\u5546\u54c1\u5df2\u914d\u9001");
        this.dingdanDao.updateBean(dingdan);
        final Cheliang cheliang = bean.getCheliang();
        cheliang.setDiaoduzhuangtai("\u5df2\u8c03\u5ea6");
        this.cheliangDao.updateBean(cheliang);
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        out.print(Util.tiaozhuan2("\u64cd\u4f5c\u6210\u529f", "diaodumethod!diaodulist2", "diaodulist2"));
        out.flush();
        out.close();
    }
    
    public void diaoduupdate2() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Diaodu bean = this.diaoduDao.selectBean(" where id= " + request.getParameter("id"));
        bean.setDiaoduzhuangtai("\u5b8c\u6210\u8c03\u5ea6");
        bean.setDiaoduriqi(Util.getTime());
        final HttpSession session = request.getSession();
        final User user = (User)session.getAttribute("user");
        bean.setUser3(user);
        this.diaoduDao.updateBean(bean);
        final Dingdan dingdan = bean.getDingdan();
        dingdan.setShouhuozhuangtai("\u5b8c\u6210\u914d\u9001");
        this.dingdanDao.updateBean(dingdan);
        final Cheliang cheliang = bean.getCheliang();
        cheliang.setDiaoduzhuangtai("\u672a\u8c03\u5ea6");
        this.cheliangDao.updateBean(cheliang);
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        out.print(Util.tiaozhuan2("\u64cd\u4f5c\u6210\u529f", "diaodumethod!diaodulist3", "diaodulist3"));
        out.flush();
        out.close();
    }
    
    public String diaodulist3() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final String where = " where diaoduzhuangtai!='\u672a\u8c03\u5ea6' order by  diaoduzhuangtai desc ,id desc ";
        final String where2 = " where diaoduzhuangtai!='\u672a\u8c03\u5ea6' ";
        final int total = this.diaoduDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.diaoduDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"diaodumethod!diaodulist3");
        this.setUrl("diaodu/diaodulist3.jsp");
        return "success";
    }
}
