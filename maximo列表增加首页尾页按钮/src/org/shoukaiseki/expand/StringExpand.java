package org.shoukaiseki.expand;


import java.util.Arrays;

/**
 * org.shoukaiseki.expand.StringExpand <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-09 14:54:12<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/

public class StringExpand {
	
	/**
	 * @param strs 数组,不包含null元素
	 * @param str  可以为null,为null时有空字符串代替
	 * @return 如果该数组有该字符串则返回true
	 */
	public static boolean binarySearch(String[] strs,String str){
		Arrays.sort(strs);
		return Arrays.binarySearch(strs, str==null?"":str)>=0;
	}

}
