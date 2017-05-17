package com.htsoft.oa.action.communicate;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.PhoneBook;
import com.htsoft.oa.model.communicate.PhoneGroup;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.communicate.PhoneBookService;
/**
 * 
 * @author csx
 *
 */
public class PhoneBookAction extends BaseAction{
	@Resource
	private PhoneBookService phoneBookService;
	private PhoneBook phoneBook;
	private PhoneGroup phoneGroup;
	private Long phoneId;

	public Long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}

	public PhoneGroup getPhoneGroup() {
		return phoneGroup;
	}

	public void setPhoneGroup(PhoneGroup phoneGroup) {
		this.phoneGroup = phoneGroup;
	}

	public PhoneBook getPhoneBook() {
		return phoneBook;
	}

	public void setPhoneBook(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}

	/**
	 * 显示列表
	 */
	public String list(){		
		QueryFilter filter=new QueryFilter(getRequest());
//		filter.addFilter("Q_appUser.userId_L_EQ", (ContextUtil.getCurrentUserId()).toString());
//		String strGroupId=getRequest().getParameter("groupId");
//		if(StringUtils.isNotEmpty(strGroupId)){
//			Long groupId=Long.parseLong(strGroupId);
//			 if(groupId>0){
//		        filter.addFilter("Q_phoneGroup.groupId_L_EQ", strGroupId);
//			 }
//		}
		List<PhoneBook> list= phoneBookService.getAll(filter);
		for(int i=0;i<list.size();i++){
			PhoneBook pb = list.get(i);
			if(StringUtils.isEmpty(pb.getQqNumber())) pb.setQqNumber("");
			if(StringUtils.isEmpty(pb.getPhone())) pb.setPhone("");
			if(StringUtils.isEmpty(pb.getMsn())) pb.setMsn("");
		}
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list,filter.getPagingBean().getTotalItems());
		return SUCCESS;
	}
	
	public String share(){
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_isShared_SN_EQ","1");
		List<PhoneBook> list =phoneBookService.getAll(filter);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list,filter.getPagingBean().getTotalItems());
		return SUCCESS;
	}
	
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				phoneBookService.remove(new Long(id));
			}
		}
		jsonString="{success:true}";
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		PhoneBook phoneBook=phoneBookService.get(phoneId);		
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
		jsonString = mapper.toDataJson(phoneBook);
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		AppUser appUser=ContextUtil.getCurrentUser();
		phoneBook.setAppUser(appUser);
		phoneBookService.save(phoneBook);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	/**
	 * 设置和取消共享
	 */
	public String isShared(){
		short isShared=Short.parseShort(getRequest().getParameter("isShared"));
		PhoneBook pb = phoneBookService.get(phoneId);
		pb.setIsShared(isShared);
		phoneBookService.save(pb);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	
	/**
	 * 获取联系人详细信息
	 */
	
	public String detail(){
		String strPhoneId=getRequest().getParameter("phoneId");		
		if(StringUtils.isNotEmpty(strPhoneId)){
			Long phoneId=new Long(strPhoneId);
			phoneBook=phoneBookService.get(phoneId);			
		}
		return "detail";
	}
}
