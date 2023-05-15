<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<div class="pageContent">
<form method="post" name=form1 action="method!kehuupdate2" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent nowrap" layoutH="80">
	
							<dl>
							<dt >
							客户名称
							</dt>
							<dd >
							<input  type="hidden" name="id" size="20" value="${bean.id }"/>
								<input class="required " type="text" name="kehumingcheng" size="20" maxlength="20" value="${bean.kehumingcheng }"/>
							</dd>
						</dl>
						
						<dl>
							<dt >
							公司名称
							</dt>
							<dd >
								<input class="required " type="text" name="gongsimingchen" size="20" maxlength="20" value="${bean.gongsimingchen }"/>
							
							</dd>
						</dl>
					
					
				
						<div id="biaotou"></div>
	</div>
<div class="formBar">
      <ul>
            <li>
                 <div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div>
            </li>
            <li>
                  <div class="button"><div class="buttonContent"><button type="Button" class="close">取消</button></div></div>
            </li>
      </ul>
</div>
</form>
</div>

