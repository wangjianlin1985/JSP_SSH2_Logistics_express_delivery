// 
// 
// 

package action;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import model.Dingdan;
import model.Caiwu;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import dao.DingdanDao;
import dao.CaiwuDao;
import com.opensymphony.xwork2.ActionSupport;

public class CaiwuAction extends ActionSupport
{
    private static final long serialVersionUID = -4304509122548259589L;
    private CaiwuDao caiwuDao;
    private String url;
    private DingdanDao dingdanDao;
    
    public CaiwuAction() {
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
    
    public String caiwulist() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String type = request.getParameter("type");
        final String leixing = request.getParameter("leixing");
        final String time1 = request.getParameter("time1");
        final String time2 = request.getParameter("time2");
        final StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        final StringBuffer sb2 = new StringBuffer();
        sb2.append(" where ");
        if (type != null && !"".equals(type)) {
            sb.append("type like '%" + type + "%'");
            sb.append(" and ");
            sb2.append("type like '%" + type + "%'");
            sb2.append(" and ");
            request.setAttribute("type", (Object)type);
        }
        if (leixing != null && !"".equals(leixing)) {
            sb.append("leixing like '%" + leixing + "%'");
            sb.append(" and ");
            sb2.append("leixing like '%" + leixing + "%'");
            sb2.append(" and ");
            request.setAttribute("leixing", (Object)leixing);
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
        final int total = this.caiwuDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.caiwuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"method!caiwulist");
        this.setUrl("caiwu/caiwulist.jsp");
        return "success";
    }
    
    public String caiwulist2() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String time1 = request.getParameter("time1");
        final String time2 = request.getParameter("time2");
        final StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        final StringBuffer sb2 = new StringBuffer();
        sb2.append(" where ");
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
        sb.append(" dingdan !=null  order by id desc ");
        final String where = sb.toString();
        sb2.append(" dingdan !=null  ");
        final String where2 = sb2.toString();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final int total = this.caiwuDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.caiwuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"method!caiwulist2");
        this.setUrl("caiwu/caiwulist2.jsp");
        return "success";
    }
    
    public DingdanDao getDingdanDao() {
        return this.dingdanDao;
    }
    
    public void setDingdanDao(final DingdanDao dingdanDao) {
        this.dingdanDao = dingdanDao;
    }
    
    public void duizhang() throws IOException {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final Caiwu caiwu = this.caiwuDao.selectBean(" where id= " + request.getParameter("id"));
        final Dingdan dingdan = caiwu.getDingdan();
        dingdan.setDuizhangzhuangtai("\u5df2\u5bf9\u8d26");
        this.dingdanDao.updateBean(dingdan);
        final HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        out.print(Util.tiaozhuan2("\u64cd\u4f5c\u6210\u529f", "caiwumethod!caiwulist2", "caiwulist2"));
        out.flush();
        out.close();
    }
    
    public String caiwulist3() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String time1 = request.getParameter("time1");
        final String time2 = request.getParameter("time2");
        final StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        final StringBuffer sb2 = new StringBuffer();
        sb2.append(" where ");
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
        sb.append(" kucun !=null  order by id desc ");
        final String where = sb.toString();
        sb2.append(" kucun !=null ");
        final String where2 = sb2.toString();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final int total = this.caiwuDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.caiwuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"method!caiwulist3");
        this.setUrl("caiwu/caiwulist3.jsp");
        return "success";
    }
    
    public String caiwulist4() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String time1 = request.getParameter("time1");
        final String time2 = request.getParameter("time2");
        final StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        final StringBuffer sb2 = new StringBuffer();
        sb2.append(" where ");
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
        sb.append(" churuku !=null  order by id desc ");
        final String where = sb.toString();
        sb2.append(" churuku !=null  ");
        final String where2 = sb2.toString();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final int total = this.caiwuDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.caiwuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"method!caiwulist4");
        this.setUrl("caiwu/caiwulist4.jsp");
        return "success";
    }
    
    public String caiwulist5() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String time1 = request.getParameter("time1");
        final String time2 = request.getParameter("time2");
        final StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        final StringBuffer sb2 = new StringBuffer();
        sb2.append(" where ");
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
        sb.append(" cheliang !=null  order by id desc ");
        final String where = sb.toString();
        sb2.append(" cheliang !=null   ");
        final String where2 = sb2.toString();
        int currentpage = 1;
        int pagesize = 20;
        if (request.getParameter("pageNum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pageNum"));
            pagesize = Integer.parseInt(request.getParameter("numPerPage"));
        }
        final int total = this.caiwuDao.selectBeanCount(where2);
        request.setAttribute("list", (Object)this.caiwuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"method!caiwulist5");
        this.setUrl("caiwu/caiwulist5.jsp");
        return "success";
    }
    
    public String caiwulist6() {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String time1 = request.getParameter("time1");
        final String time2 = request.getParameter("time2");
        final StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        final StringBuffer sb2 = new StringBuffer();
        sb2.append(" where ");
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
        final int total = this.caiwuDao.selectBeanCount(where2);
        final List<Caiwu> list = this.caiwuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where);
        double shouru = 0.0;
        double zhichu = 0.0;
        double lirun = 0.0;
        for (final Caiwu bean : list) {
            if ("\u8d22\u52a1\u6536\u5165".equals(bean.getType())) {
                shouru += bean.getJine();
            }
            if ("\u8d22\u52a1\u652f\u51fa".equals(bean.getType())) {
                zhichu += bean.getJine();
            }
        }
        lirun = shouru - zhichu;
        request.setAttribute("shouru", (Object)shouru);
        request.setAttribute("zhichu", (Object)zhichu);
        request.setAttribute("lirun", (Object)lirun);
        request.setAttribute("list", (Object)list);
        request.setAttribute("totalCount", (Object)total);
        request.setAttribute("ps", (Object)pagesize);
        request.setAttribute("pn", (Object)currentpage);
        request.setAttribute("url", (Object)"method!caiwulist6");
        this.setUrl("caiwu/caiwulist6.jsp");
        return "success";
    }
}
