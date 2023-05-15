<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>物流管理系统</title>

<link href="ui/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="ui/themes/css/core.css" rel="stylesheet" type="text/css" />
<link href="ui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="ui/themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->

<script src="ui/js/speedup.js" type="text/javascript"></script>
<script src="ui/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="ui/js/jquery.cookie.js" type="text/javascript"></script>
<script src="ui/js/jquery.validate.js" type="text/javascript"></script>
<script src="ui/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="ui/xheditor/xheditor-1.1.9-zh-cn.min.js" type="text/javascript"></script>
<script src="ui/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="ui/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>

<script src="ui/js/dwz.min.js" type="text/javascript"></script>
<script src="ui/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("ui/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"ui/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<!-- <a class="logo" href="http://">标志</a> -->
				<div style="font-size: 25px;font-weight: bold;color: #fff;padding-left:10px;height: 40px;;padding-top: 10px;">
				物流管理系统</div>
				<ul class="nav">
				<c:if test="${user!=null}">
					<li>您好,${user.username}
					
					</li>
					<li><a href="method!changepwd" target="dialog" width="600">修改密码</a></li>
					<li><a href="method!loginout">安全退出</a></li>
				</c:if>
					<c:if test="${user==null}">
				<li><a href="method!login2">用户登录</a></li>
				</c:if>
				</ul>
				
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
                  
				<div class="accordion" fillSpace="sidebar">
				

				<div class="accordionHeader">
						<h2><span>Folder</span>订单查询</h2>
				</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						<li><a>订单查询</a>
								<ul>
									
									<li><a href="method!dingdanlist2" target="navTab"  rel="dingdanlist2">订单查询</a></li>
									
									
								
									
								</ul>
							</li>
						</ul>
					</div>


<c:if test="${user!=null}">


 <c:if test="${user.role==0}">
 
 				<div class="accordionHeader">
						<h2><span>Folder</span>客户管理</h2>
				</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						<li><a>客户管理</a>
								<ul>
									
									<li><a href="method!kehulist" target="navTab"  rel="kehulist">客户管理</a></li>
									
									
								
									
								</ul>
							</li>
						</ul>
					</div>
 
				<div class="accordionHeader">
						<h2><span>Folder</span>订单管理</h2>
				</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						<li><a>订单管理</a>
								<ul>
									
									<li><a href="method!dingdanlist" target="navTab"  rel="dingdanlist">订单管理</a></li>
									
									
								
									
								</ul>
							</li>
						</ul>
					</div>
					
				


				<div class="accordionHeader">
						<h2><span>Folder</span>仓储管理</h2>
				</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						<li><a>仓储管理</a>
								<ul>
									<li><a href="cangchumethod!rukulist" target="navTab"  rel="rukulist">入仓商品登记</a></li>
									
									<li><a href="cangchumethod!chukulist2" target="navTab"  rel="chukulist2">出仓商品登记</a></li>
									
									<li><a href="cangchumethod!kucunlist" target="navTab"  rel="kucunlist">库存商品查询</a></li>
									
									<li><a href="cangchumethod!chukulist" target="navTab"  rel="chukulist">出仓选货</a></li>
									
									<li><a href="cangchumethod!chukulist3" target="navTab"  rel="chukulist3">订单仓库</a></li>
									
									<li><a href="cangchumethod!chukulist4" target="navTab"  rel="chukulist4">入仓出仓查询</a></li>
									
									<li><a href="cangchumethod!cangzulist" target="navTab"  rel="cangzulist">仓库租金管理</a></li>
									
								</ul>
							</li>
						</ul>
					</div>
					
			
			<div class="accordionHeader">
						<h2><span>Folder</span>调度管理</h2>
				</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						<li><a>调度管理</a>
								<ul>
									<li><a href="diaodumethod!chelianglist" target="navTab"  rel="chelianglist">车辆管理</a></li>
									
									<li><a href="diaodumethod!peisonglist" target="navTab"  rel="peisonglist">配送中心</a></li>
									
									<li><a href="diaodumethod!diaodulist" target="navTab"  rel="diaodulist">调度配载</a></li>
									
									<li><a href="diaodumethod!diaodulist2" target="navTab"  rel="diaodulist2">调度中心</a></li>
									
									<li><a href="diaodumethod!diaodulist3" target="navTab"  rel="diaodulist3">调度反馈</a></li>
								</ul>
							</li>
						</ul>
					</div>
		</c:if>			
		
		
			 <c:if test="${user.role==1}">
				 
				  <div class="accordionHeader">
						<h2><span>Folder</span>财务统计查询</h2>
				</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						<li><a>财务统计查询</a>
								<ul>
									<li><a href="caiwumethod!caiwulist" target="navTab"  rel="caiwulist">财务查询</a></li>
									
									<li><a href="caiwumethod!caiwulist2" target="navTab"  rel="caiwulist2">订单财务明细</a></li>
									
									<li><a href="caiwumethod!caiwulist3" target="navTab"  rel="caiwulist3">库存财务明细</a></li>
									
									<li><a href="caiwumethod!caiwulist4" target="navTab"  rel="caiwulist4">入库财务明细</a></li>
									
									<li><a href="caiwumethod!caiwulist5" target="navTab"  rel="caiwulist5">运输财务明细</a></li>
									
									<li><a href="caiwumethod!caiwulist6" target="navTab"  rel="caiwulist6">收付利润统计</a></li>
								</ul>
							</li>
						</ul>
					</div>
				 
				 
				
				 
				
				 <div class="accordionHeader">
						<h2><span>Folder</span>员工管理</h2>
				</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						<li><a>员工管理</a>
								<ul>
									<li><a href="method!userlist" target="navTab"  rel="userlist">员工管理</a></li>
								</ul>
							</li>
						</ul>
					</div>
					
					
					
				 </c:if>
				 
				 
	</c:if>				
					
					
					
					
					
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<p><h1 style="font-size: 22px;text-align: center;">欢迎${user.truename }使用本平台
							<c:if test="${user==null}">
							请点击右上角登录本系统
							</c:if>
							</h1>
						</div>
						<div class="pageFormContent" layoutH="80">
							
	
							
							
							
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer"></div>

</body>
</html>