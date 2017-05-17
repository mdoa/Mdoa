package com.htsoft.oa.action.system;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.htsoft.core.Constants;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.Company;
import com.htsoft.oa.service.system.CompanyService;

/**
 * 公司信息Ation
 * @author zxh
 *
 */
public class CompanyAction extends BaseAction{

	private Company company;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Resource
	private CompanyService companyService;
	
	/**
	 * 检查是否有公司记录
	 * @return
	 */
	public String check(){
		List<Company> list = companyService.findCompany();
		if(list.size()>0){
			setJsonString("{success:true}");
		}else{
			setJsonString("{success:false}");
		}
		return SUCCESS;
	}
	/**
	 * 公司信息list
	 */
	public String list(){
		List<Company> list = companyService.findCompany();
		if(list != null && list.size()>0){
			company = list.get(0);
			JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
			jsonString = mapper.toDataJson(company);
		}else{
			setJsonString("{success:false,message:'还没有填写公司信息'}");
			return SUCCESS;			
		}		
		return SUCCESS;

	}

	/**
	 * 增加公司记录
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String add(){
		companyService.save(company);
		Map map=AppUtil.getSysConfig();
		map.remove(Constants.COMPANY_LOGO);
		map.remove(Constants.COMPANY_NAME);
		map.put(Constants.COMPANY_LOGO,company.getLogo());
		map.put(Constants.COMPANY_NAME,company.getCompanyName());
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	/**
	 * 删除Logo
	 * @return
	 */
	public String delphoto(){
		List<Company> list = companyService.findCompany();
		if(list.size()>0){
			company = list.get(0);
			company.setLogo("");
			companyService.save(company);
		}
		return SUCCESS;
	}

}
