package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.Car;
import com.htsoft.oa.service.admin.CarService;

/**
 * 
 * 车辆管理Action
 * 
 * @description 车辆管理
 * @author lyy
 * 
 */
public class CarAction extends BaseAction {
	@Resource
	private CarService carService;
	private Car car;

	private Long carId;

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<Car> list = carService.getAll(filter);
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				carService.remove(new Long(id));
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		Car car = carService.get(carId);
		jsonString = mapper.toDataJson(car);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		carService.save(car);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 删除图片
	 */
	public String delphoto() {
		String strCarId = getRequest().getParameter("carId");
		if (StringUtils.isNotEmpty(strCarId)) {
			car = carService.get(new Long(strCarId));
			car.setCartImage("");
			carService.save(car);
			setJsonString("{success:true}");
		}
		return SUCCESS;
	}
}
