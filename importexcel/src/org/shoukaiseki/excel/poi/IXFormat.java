package org.shoukaiseki.excel.poi;

public class IXFormat{
	
	/**清首尾空
	 * @param value
	 */
	public static String trim(String value){
		return value==null?null:value.trim();
	}
	
	/** 处理  字段 的值,将null 转化为 "",并去首尾空
	 * @param value
	 */
	public static String transColumnValue(String value){
		return value==null?"":value.trim();
	}
	
	/** 清首尾空并转换为大写
	 * @param name
	 */
	public static String trimUpperCase(String name){
		return name==null?null:name.trim().toUpperCase();
	}
	
	/** 为 null 或者 清首尾空后为空
	 * @param name
	 */
	public static boolean isNullOrTrimEmpty(String name){
		return name==null||name.trim().isEmpty();
	}

	
	/**  在 values 里面 是否存在于 value相同的值
	 * @param value
	 * @param values
	 * @return true:values 中有與 value 相同值的數
	 */
	public static boolean isIn(String value,String[] values){
		for (String string : values) {
			if(value==null){
				if(string==null){
					return true;
				}
			}else{
				if(value.equals(string)){
					return true;
				}
			}
		}
		
		return false;
	}
	/** 满足以下要求, 或者  (空/null) 都返回 true
     * (non-Javadoc)
	 * @see #isTrue(String)
	 */
	public static boolean isTrueOrNull(String str){
		if(str==null||str.trim().isEmpty()){
			return true;
		}
		return isTrue(str);
	}
	
	/** 满足以下要求,而且不为 (空/null) 才返回 true
     * (non-Javadoc)
	 * @see #isTrue(String)
	 */
	public static boolean isTrueAntNotNull(String str){
		if(str==null||str.trim().isEmpty()){
			return false;
		}
		return isTrue(str);
	}
	
	/** 为 true/yes/on
	 * @param str
	 */
	public static boolean isTrue(String str){
		String s=trimUpperCase(str);
		return ("TRUE".equals(s)||"YES".equals(s)||"ON".equals(s))?true:false;
	}

	/** 判断两个字符串是否相同
	 * @param a
	 * @param b
	 */
	public static boolean  equalsString(String a,String b){
		if(a==null&&b==null){
			return true;
		}
		if(a==null){
			return false;
		}else{
			return a.equals(b);
		}
	}

}
