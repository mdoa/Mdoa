package com.mdoa.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdoa.constant.FrameWorkConstant;
import com.mdoa.framework.dao.PowerDao;
import com.mdoa.framework.model.FrameworkPower;
import com.mdoa.framework.model.FrameworkTree;
import com.mdoa.framework.model.Power;
import com.mdoa.repertory.model.TreeModel;

@Service
@Transactional
public class PowerService {
	
	@Autowired
	private PowerDao powerDao;
	
	/**
	 * 通过递归的形式来获取所需要的一个权限下的所有子权限
	 * @param superPowerId 父权限Id
	 * @return 权限列表
	 */
	public List<FrameworkTree> injectFrameworkPower(String superPowerId){
		//获取子权限列表
		List<FrameworkTree> powers = powerDao.getPowerBySuper(superPowerId);
		//对子权限进行遍历，递归查询查询子权限所具有的子权限
		for(FrameworkTree power : powers){
			power.setChildren(injectFrameworkPower(power.getThisId()));
		}
		return powers;
	}
	
	public List<Power> getPowerByUser(String userId){
		return powerDao.getPowerByUser(userId);
	}
	/**
	 * 添加权限
	 * @param power
	 */
	public void addUserPower(Power power) {
		//查询权限表
		List<Power> list = powerDao.queryPower(power);
		power.setSuperPowerId(list.get(0).getPowerId());
		power.setPower(power.getPower()+":"+power.getPowerKey());
		if (!powerDao.addUserPower(power)) 
			throw new RuntimeException();
		
		updateDocument(FrameWorkConstant.frameworkPowers,power);
	}
	/**
	 * 修改树结构(add)
	 * @param frameworkPowers
	 * @param power
	 */
	private void updateDocument(List<FrameworkTree> list, Power power) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(power.getSuperPowerId())) {
				FrameworkTree frameworkTree=new FrameworkTree();
				frameworkTree.setId(power.getPower());
				frameworkTree.setText(power.getPowerName());
				list.get(i).getChildren().add(frameworkTree);
				continue;
			}
			if (list.get(i).getChildren().size() == 0 || list.get(i).getChildren() == null) {
				continue;
			}
			updateDocument(list.get(i).getChildren(), power);
		}
	}
}
