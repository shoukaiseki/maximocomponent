package org.shoukaiseki.expand;

import java.math.BigDecimal;

/**
 * org.shoukaiseki.expand.BigDecimalExpand <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-09 14:52:03<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/
public class BigDecimalExpand {

	/** 四舍六入五留双
	 * @param divisor  要处理的数
	 * @param scale 保留的小数位数不能为负数
	 * @return 四舍六入五留双 之后的数据
	 */
	public static BigDecimal setSiSheLiuRuWuLiuShuang(BigDecimal divisor,int scale){
		
        if (scale < 0){
            throw new IllegalArgumentException("保留位数必须大于或者等于0");
        }
        if(divisor==null){
        	return null;
        }
        char in[] = divisor.toString().toCharArray();
        
        StringBuffer soeff=new StringBuffer();
        //当前小数位数
        int dangqianxiaoshu=-1;
        //到达小数位置标识,如果检查到小数则置位为true
        boolean daodaxiaoshu=false;
        //如果改为为true时,证明最后一位需要加1
        boolean xuyaojiayi=false;
        for (int i = 0; i < in.length; i++) {
        	char c=in[i];
        	if (c == '.') {
        		daodaxiaoshu=true;
        	}
        	
        	if(daodaxiaoshu){
        		//当前小数位数+1
        		dangqianxiaoshu++;
        		if(dangqianxiaoshu==scale){
        			if(i+1>=in.length){
        				if (c != '.') {
        					soeff.append(c);
        				}
        				break;
        			}
        			
        			char cc=c;
        			if (c == '.') {
        				cc=in[i-1];
        			}
        			if(Integer.parseInt(String.valueOf(in[i+1]))<5){
        				soeff.append(cc);
        				break;
        			}else if(Integer.parseInt(String.valueOf(in[i+1]))>5){
        				soeff.append(Integer.parseInt(String.valueOf(cc)));
        				xuyaojiayi=true;
        				break;
        			}else{
        				if(Integer.parseInt(String.valueOf(cc))%2==0){
        					boolean bb=false;
        					
        					for (int j = i+2; j < in.length; j++) {
        						char c2=in[j];
        						if(Integer.parseInt(String.valueOf(c2))>0){
        							bb=true;
        						}
							}
        					soeff.append(Integer.parseInt(String.valueOf(cc)));
        					if(bb){
        						xuyaojiayi=true;
        					}
        					break;
        				}else{
        					soeff.append(Integer.parseInt(String.valueOf(cc)));
        					xuyaojiayi=true;
        					break;
        				}
        			}
        		}else{
        			soeff.append(c);
        		}
        		
        	}else{
        		//如果没有下一位 则直接退出循环
        		if(i+1>=in.length){
        			soeff.append(c);
        			break;
        		}
        		
        		//如果下一位不为点,而且有效精度大于0
        		if (in[i+1] != '.'||scale>0) {
        			soeff.append(c);
        		}
        	}
        	
		}
        
        BigDecimal  dividend=new BigDecimal(soeff.toString());
        if(xuyaojiayi){
        	dividend=dividend.add(new BigDecimal(Math.pow(0.1, scale)));
        }
        
		return dividend.setScale(scale,BigDecimal.ROUND_DOWN);
	}

	/** 四舍六入五留双
	 * @param divisor  要处理的数
	 * @param scale 保留的小数位数不能为负数
	 * @return 四舍六入五留双 之后的数据
	 */
	public static BigDecimal setSiSheLiuRuWuLiuShuang(double divisor, int scale) {
		// TODO Auto-generated method stub
		return setSiSheLiuRuWuLiuShuang(BigDecimal.valueOf(divisor),scale);
	}
	
	/** double 转换为 BigDecimal
	 * @param d 要转换为 BigDecimal 的 double 值 
	 * @return  不为null的值
	 */
	public static BigDecimal getBigDecimal(double d){
		BigDecimal bd=null;
		bd=new BigDecimal(String.valueOf(d));
		return bd;
	}
	
	/** 字符串转换为 BigDecimal
	 * @param str 要转换为 BigDecimal 的字符串
	 * @return null 参数值为null
	 */
	public static BigDecimal getBigDecimal(String str){
		BigDecimal bd=null;
		if(str==null){
			return null;
		}
		bd=new BigDecimal(str);
		return bd;
	}
}
