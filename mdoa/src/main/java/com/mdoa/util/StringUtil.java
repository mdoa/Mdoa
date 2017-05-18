package com.mdoa.util;

import com.mdoa.user.model.UserInfo;

public class StringUtil {
	
	/**
	 * 判断一个字符串是否是空的
	 * 如果这个字符串是空的则返回一个true，否则返回一个false
	 * @param str 所需要进行判断的字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String str){
		if(str == null || str.trim().length() == 0)
			return true;
		return false;
	}
	
	/**
	 * 将一个 id或者name字符串拆分成一个字符串数组
	 * 按照 “,”逗号进行分割，并且能够去掉拆分后的首尾 " 引号
	 * @param str 需要拆分的字符串
	 * @return 拆分后的字符串数组
	 */
	public static String[] splitString(String str){
		return splitString(str,",");
	}
	
	/**
	 * 将一个 id或者name字符串拆分成一个字符串数组
	 * 按照 split 逗号进行分割，并且能够去掉拆分后的首尾 " 引号
	 * @param str 需要拆分的字符串
	 * @return 拆分后的字符串数组
	 */
	public static String[] splitString(String str, String split){
		if(isEmpty(str) || isEmpty(split)){
			return null;
		}else{
			String[] strs = str.split(split);
			for(int i = 0; i<strs.length ; i++){
				strs[i] = strs[i].substring(1, strs[i].length()-1);
			}
			return strs;
		}
	}
	/**
	 * 从Url中获取父Id的方法
	 */
	public static String getParentIdFromUrl(String url){
		String[] str = url.split("_");
		return str[str.length-2];
	}
	/**
	 * 从Url中获取Id的方法
	 */
	public static String getIdFromUrl(String url){
		int lastIndexOf = url.lastIndexOf("_");
		return url.substring(lastIndexOf+1,url.length());
	}
	
	/**
	 * 判断 parent 字符串是否包含 child 字符串，并且仅右模糊
	 * @param child
	 * @param parent
	 * @return 
	 */
	public static boolean isInclude(String parent,String child){
		if(parent.substring(0, child.length()).equals(child)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 转换sex
	 * @param sex
	 * @return
	 */
	public static String parseSex(String sex){
		if(isEmpty(sex)){
			return "";
		}else if(sex.equals("1")){
			return "男";
		}else{
			return "女";
		}
	}
	
	/**
	 * 转换学历
	 * @param education
	 * @return
	 */
	public static String parseEducation(String education){
		if(isEmpty(education)){
			return "";
		}else if(education.equals("1")){
 		   return "初中";
 	   }else if(education.equals("2")){
 		   return "高中";
 	   }else if(education.equals("3")){
 		   return "专科";
 	   }else if(education.equals("4")){
 		   return "本科";
 	   }else{
 		   return "硕士及以上";
 	   }
	}
	
	/**
	 * 转换inviteFlag
	 * @param inviteFlag
	 * @return
	 */
	public static String parseInviteFlag(String inviteFlag){
		if(isEmpty(inviteFlag)){
 		   return "";
 	   }else if(inviteFlag.equals("1")){
 		   return "在职";
 	   }else if(inviteFlag.equals("2")){
 		   return "离职无手续";
 	   }else if(inviteFlag.equals("3")){
 		   return "离职有手续";
 	   }else if(inviteFlag.equals("4")){
 		   return "试用期";
 	   }else if(inviteFlag.equals("5")){
 		   return "工伤";
 	   }else{
 		   return "待聘用";
 	   }
	}
	
	/**
	 * 转换退休状态
	 * @param retireFlag
	 * @return
	 */
	public static String parseRetireFlag(String retireFlag){
		if(isEmpty(retireFlag)){
 		    return "";
 	    }else if(retireFlag.equals("1")){
 		    return "正常";
 	    }else if(retireFlag.equals("2")){
 		    return "退休";
 	    }else{
 		    return "退休返聘";
 	    }
	}
	
	/**
	 * 将字符串转为Like右模糊查询时所需要的字符串
	 */
	public static String toLikeRight(String str){
		if(str != null && str.length() != 0){
			return "'"+str+"%'";
		}else{
			return null;
		}
	}
	
	/**
	 * 将字符串转换为Like全模糊时所需的字符串
	 */
	public static String toLikeAll(String str){
		if(str != null && str.length() != 0){
			return "'%"+str+"%'";
		}else{
			return null;
		}
	}
	
	
	
}
