package kr.bit.frontcontroller;

import java.util.HashMap;

import kr.bit.controller.Controller;
import kr.bit.controller.*;

public class HandlerMapping {
	private HashMap<String, Controller> mappings;
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/memberlist.do", new MemberListController());
		mappings.put("/memberInsert.do", new MemberInsertController());
		mappings.put("/memberRegister.do", new MemberRegisterController());
		mappings.put("/memberContent.do", new MemberContentController());
		mappings.put("/memberUpdate.do", new MemberUpdateController());
		mappings.put("/memberDelete.do", new MemberDeleteController());
		mappings.put("/memberLogin.do", new MemberLoginController());
		mappings.put("/memberLogout.do", new MemberLogoutController());	
		mappings.put("/memberDbcheck.do", new MemberLogoutController());	
		mappings.put("/memberAjaxList.do", new MemberAjaxListController());
		mappings.put("/memberAjaxDelete.do", new MemberAjaxDeleteController());
		mappings.put("/fileAdd.do", new FileAddController());	
		mappings.put("/fileGet.do", new FileGetController());	
		
	}
	public Controller getController(String key) {
		 //예시 key = memberList.do
		return mappings.get(key);
	}
}
