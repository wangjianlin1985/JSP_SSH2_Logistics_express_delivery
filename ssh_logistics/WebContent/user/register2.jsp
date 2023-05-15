<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理平台</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />


<style type="text/css">
img, div { behavior: url(iepngfix.htc) }
</style>

</head>

<body>
<body id="login">
		<div id="wrappertop"></div>
			<div id="wrapper">
					<div id="content">
						<div id="header">
							<h1><h2>用户注册</h2></h1>
						</div>
							
						<div id="darkbannerwrap">
						</div>
						<form action="method!register2" method="post">
						<fieldset class="form">
							
                        	<p>
								<label>用户号</label>
								 <input type="text" name="username" size="30" class="required" />
							</p>
							<p>
								<label>密码</label>
				 <input type="text" name="password" size="30" class="required" />
							</p>
							<p>
								<label>密码确认</label>
				 <input type="text" name="password2" size="30" class="required" />
							</p>
							<p>
								<label>真实姓名</label>
				 <input type="text" name="truename" size="30" class="required" />
							</p>
							<p>
								<label>部门</label>
								<select  name="bumen" >
								<c:forEach items="${list}" var="bean">
								<option value="${bean.id }">${bean.name }</option>
								</c:forEach>
								
								
								</select>
								
							</p>
							
							<button type="submit" class="positive" name="Submit">
								注册</button>
                            						</fieldset>
						
						
					</form></div>
				</div>   


</body>
</html>