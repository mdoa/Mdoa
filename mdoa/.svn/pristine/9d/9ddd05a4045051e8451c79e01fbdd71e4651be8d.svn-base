package com.mdoa.repertory.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mdoa.base.model.BaseModel;
import com.mdoa.repertory.model.RepertoryInRecord;

public class GoodsForm extends BaseModel {

	private String goodsId;// 物品id
	private String goodsName;// 物品名字
	private String goodsTypeUrl;// 物品类型url
	private Integer totalNumber;// 物品总数量
	private String goodsSize;// 物品的型号
	private String unit; // 单位
	private Integer warnNumber;// 警示数量
	private Double latestUnitPrice;// 最新单价
	private Double movingAverPrice;// 移动平均价
	private Double weightedAverPrice;// 加权平均价

	private String goodsTypeId;// 物品类型id
	private String goodsTypeName;/// 物品类型名字
	private String parentTypeId;// 父级目录
	private Date createTime;// 创建时间
	private String createTimeStr;// string创建时间
	private String createUserId;// 创建人id
	private String createUserName;// 创建人名字
	private Date updateTime;// 修改时间
	private String updateTimeStr;
	private String updateUserId;// 修改人id
	private String updateUserName;// 修改人名字
	private String aliveFlag;// 是否有效

	private String inRecordId;// 入库记录id
	private String repertoryId;// 仓库id
	private String repertoryName;// 仓库名字
	private String goodsPositionId;// 仓位id
	private String goodsPositionName;// 仓位名字
	private Integer inNumber;// 入库数量
	private Double pretaxAmount;// 税前金额
	private Double pretaxAverPrice;// 税前平均价
	private Double taxRate;// 税率
	private Double taxAmount;// 税后金额
	private Double taxAverPrice;// 税后平均价
	private String operateUserId;// 操作人id
	private String operateUserName;// 操作人名字
	private String inTimeStr;
	private Date inTime;// 入库时间
	private String putUserId;// 放入人ID
	private String putUserName;// 放入人名字

	private String outRecordId;// 出库记录id
	private Integer outNumber;// 出库数量
	private String outTimeStr; // string出库时间
	private Date outTime;// 出库时间
	private String getUserId;// 领用人
	private String getUserName;// 领用人名字
	private String getDepartmentId;// 领用部门id
	private String getDepartmentName;// 领用部门名字
	private String useType;// 物品用途

	private String record;// 备注
	private Integer goodsNumber;// 仓位中的的物品数量
	private String text;// 仓库的描述
	private String area;// 仓库位置

	private String operation;// 操作
	private String newRepertoryId;// 移库时的新仓库id
	private Integer moveNumber;// 移库数量

	private String beginTime;// 查询时前面的时间
	private String endTime;// 查询时后面的时间
	private Integer beginNumber;// 查询时前面的数量
	private Integer endNumber;// 查询时后面的数量

	private Integer goodsLack;// 缺货 1 不缺货 0
	private String provider;// 供应商

	private Integer inTotalNumber;
	private Double inTotalPretaxAmount;
	private Double inTotalTaxAmount;

	private Integer outTotalNumber;
	private Double outTotalTaxAmount;

	public Integer getInTotalNumber() {
		return inTotalNumber;
	}

	public void setInTotalNumber(Integer inTotalNumber) {
		this.inTotalNumber = inTotalNumber;
	}

	public Double getInTotalPretaxAmount() {
		return inTotalPretaxAmount;
	}

	public void setInTotalPretaxAmount(Double inTotalPretaxAmount) {
		this.inTotalPretaxAmount = inTotalPretaxAmount;
	}

	public Double getInTotalTaxAmount() {
		return inTotalTaxAmount;
	}

	public void setInTotalTaxAmount(Double inTotalTaxAmount) {
		this.inTotalTaxAmount = inTotalTaxAmount;
	}

	public Integer getOutTotalNumber() {
		return outTotalNumber;
	}

	public void setOutTotalNumber(Integer outTotalNumber) {
		this.outTotalNumber = outTotalNumber;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public Integer getGoodsLack() {
		return goodsLack;
	}

	public void setGoodsLack(Integer goodsLack) {
		this.goodsLack = goodsLack;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getBeginNumber() {
		return beginNumber;
	}

	public void setBeginNumber(Integer beginNumber) {
		this.beginNumber = beginNumber;
	}

	public Integer getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(Integer endNumber) {
		this.endNumber = endNumber;
	}

	public Integer getMoveNumber() {
		return moveNumber;
	}

	public void setMoveNumber(Integer moveNumber) {
		this.moveNumber = moveNumber;
	}

	public String getNewRepertoryId() {
		return newRepertoryId;
	}

	public void setNewRepertoryId(String newRepertoryId) {
		this.newRepertoryId = newRepertoryId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public String getGetDepartmentId() {
		return getDepartmentId;
	}

	public void setGetDepartmentId(String getDepartmentId) {
		this.getDepartmentId = getDepartmentId;
	}

	public String getGetDepartmentName() {
		return getDepartmentName;
	}

	public void setGetDepartmentName(String getDepartmentName) {
		this.getDepartmentName = getDepartmentName;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getOutRecordId() {
		return outRecordId;
	}

	public void setOutRecordId(String outRecordId) {
		this.outRecordId = outRecordId;
	}

	public Integer getOutNumber() {
		return outNumber;
	}

	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}

	public String getOutTimeStr() {
		return outTimeStr;
	}

	public void setOutTimeStr(String outTimeStr) {
		this.outTimeStr = outTimeStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.outTime = dateFormat.parse(outTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.outTimeStr = dateFormat.format(outTime);

	}

	public String getGetUserId() {
		return getUserId;
	}

	public void setGetUserId(String getUserId) {
		this.getUserId = getUserId;
	}

	public String getGetUserName() {
		return getUserName;
	}

	public void setGetUserName(String getUserName) {
		this.getUserName = getUserName;
	}

	public String getInRecordId() {
		return inRecordId;
	}

	public void setInRecordId(String inRecordId) {
		this.inRecordId = inRecordId;
	}

	public String getRepertoryId() {
		return repertoryId;
	}

	public void setRepertoryId(String repertoryId) {
		this.repertoryId = repertoryId;
	}

	public String getRepertoryName() {
		return repertoryName;
	}

	public void setRepertoryName(String repertoryName) {
		this.repertoryName = repertoryName;
	}

	public String getGoodsPositionId() {
		return goodsPositionId;
	}

	public void setGoodsPositionId(String goodsPositionId) {
		this.goodsPositionId = goodsPositionId;
	}

	public String getGoodsPositionName() {
		return goodsPositionName;
	}

	public void setGoodsPositionName(String goodsPositionName) {
		this.goodsPositionName = goodsPositionName;
	}

	public Integer getInNumber() {
		return inNumber;
	}

	public void setInNumber(Integer inNumber) {
		this.inNumber = inNumber;
	}

	public Double getPretaxAmount() {
		return pretaxAmount;
	}

	public void setPretaxAmount(Double pretaxAmount) {
		this.pretaxAmount = pretaxAmount;
	}

	public Double getPretaxAverPrice() {
		return pretaxAverPrice;
	}

	public void setPretaxAverPrice(Double pretaxAverPrice) {
		this.pretaxAverPrice = pretaxAverPrice;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getTaxAverPrice() {
		return taxAverPrice;
	}

	public void setTaxAverPrice(Double taxAverPrice) {
		this.taxAverPrice = taxAverPrice;
	}

	public String getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}

	public String getOperateUserName() {
		return operateUserName;
	}

	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}

	public String getInTimeStr() {
		return inTimeStr;
	}

	public void setInTimeStr(String inTimeStr) {
		this.inTimeStr = inTimeStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.inTime = dateFormat.parse(inTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.inTimeStr = dateFormat.format(inTime);
	}

	public String getPutUserId() {
		return putUserId;
	}

	public void setPutUserId(String putUserId) {
		this.putUserId = putUserId;
	}

	public String getPutUserName() {
		return putUserName;
	}

	public void setPutUserName(String putUserName) {
		this.putUserName = putUserName;
	}

	// ==========================
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getWarnNumber() {
		return warnNumber;
	}

	public void setWarnNumber(Integer warnNumber) {
		this.warnNumber = warnNumber;
	}

	public Double getLatestUnitPrice() {
		return latestUnitPrice;
	}

	public void setLatestUnitPrice(Double latestUnitPrice) {
		this.latestUnitPrice = latestUnitPrice;
	}

	public Double getMovingAverPrice() {
		return movingAverPrice;
	}

	public void setMovingAverPrice(Double movingAverPrice) {
		this.movingAverPrice = movingAverPrice;
	}

	public Double getWeightedAverPrice() {
		return weightedAverPrice;
	}

	public void setWeightedAverPrice(Double weightedAverPrice) {
		this.weightedAverPrice = weightedAverPrice;
	}

	public String getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public String getGoodsTypeUrl() {
		return goodsTypeUrl;
	}

	public void setGoodsTypeUrl(String goodsTypeUrl) {
		this.goodsTypeUrl = goodsTypeUrl;
	}

	public String getParentTypeId() {
		return parentTypeId;
	}

	public void setParentTypeId(String parentTypeId) {
		this.parentTypeId = parentTypeId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getAliveFlag() {
		return aliveFlag;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public GoodsForm() {
		super();
	}

	/******************************************************************************/
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createTimeStr = dateFormat.format(createTime);
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.createTime = dateFormat.parse(createTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.updateTimeStr = dateFormat.format(updateTime);
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.updateTime = dateFormat.parse(updateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Double getOutTotalTaxAmount() {
		return outTotalTaxAmount;
	}

	public void setOutTotalTaxAmount(Double outTotalTaxAmount) {
		this.outTotalTaxAmount = outTotalTaxAmount;
	}
}
