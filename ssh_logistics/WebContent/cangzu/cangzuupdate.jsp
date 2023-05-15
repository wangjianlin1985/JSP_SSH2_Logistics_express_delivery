<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<div class="pageContent">
<form method="post" name=form1 action="cangchumethod!cangzuupdate2" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent nowrap" layoutH="80">
	
						
						<input type="hidden" name="id"  value="${bean.id }"/>
						<dl>
							<dt >
							仓库租金(每平方米/元)
							</dt>
							<dd >
								<input class="required " type="text" name="zujin" size="20" maxlength="20" value="${bean.zujin }"/>
							
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

