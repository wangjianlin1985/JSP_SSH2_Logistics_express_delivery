<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<div class="pageContent">
<form method="post" name=form1 action="diaodumethod!cheliangupdate2" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent nowrap" layoutH="80">
	
							<dl>
							<dt >
							车牌号
							</dt>
							<dd ><input  type="hidden" name="id"  value="${bean.id }"/>
								${bean.chepai }
							</dd>
						</dl>
						
						<dl>
							<dt >
							车型
							</dt>
							<dd >
								<input class="required " type="text" name="chexing" size="20" maxlength="20" value="${bean.chexing }"/>
							
							</dd>
						</dl>
						<dl>
							<dt >
							柜号
							</dt>
							<dd >
								<input class="required " type="text" name="guihao" size="20" maxlength="20" value="${bean.guihao }"/>
							
							</dd>
						</dl>
						<dl>
							<dt >
							承运公司
							</dt>
							<dd >
								<input class="required " type="text" name="chengyungongsi" size="20" maxlength="20" value="${bean.chengyungongsi }"/>
							
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

