package org.shoukaiseki.expand;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import psdi.server.MXServer;


/** 是否为正式服务器
 * 首先判断 isFormalServer
 * org.shoukaiseki.expand.FormalServer <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-2017-06-09214:52:48
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/

public class FormalServer {
	
	static Boolean isFormalServer=null;
	static String message =null;
	
	public static boolean initIsFormalServer()  {
		Connection conn = null ;
		Statement staTab=null;
		ResultSet executeQuery =null;
		boolean b=true;
		boolean noerror=true;
		// 装载驱动程序
		try {
			conn = MXServer.getMXServer().getDBManager().getSequenceConnection();
			staTab = conn.createStatement();
			executeQuery = staTab.executeQuery("select SYS_CONTEXT('USERENV','LANGUAGE')||SYS_CONTEXT('USERENV','DB_NAME')|| SYS_CONTEXT('USERENV','CURRENT_USER') from dual");
			if(executeQuery.next()){
				String string = executeQuery.getString(1);
				String property = MXServer.getMXServer().getConfig().getProperty("shuto.hd.zskcronid");
				if(property==null||!property.equalsIgnoreCase(string)){
					message = "任务执行已取消,正式数据库服务器标志为="+property+",查询到的标志为="+string;
					System.out.println(message);
					b=false;
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=getTrace(e);
			noerror=false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=getTrace(e);
			noerror=false;
		}finally{
			if(executeQuery!=null){
				try {
					executeQuery.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(staTab!=null){
				try {
					staTab.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message=getTrace(e);
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message=getTrace(e);
				}
			}
			
		}
		isFormalServer=b;	
		return noerror;
		
	}
	
	/**如果返回 null,证明 initIsFormalServer 出错
	 * @return true 为正式库
	 */
	public static Boolean isFormalServer(){
		if(isFormalServer==null){
			if(!initIsFormalServer()){
				return null;
			}
		}
		return isFormalServer;
	}

	/**获取初始化错误信息
	 * @return 初始化错误信息
	 */
	public static String getMessage(){
		return message;
	}
	
	
	public static String getTrace(Throwable t) {
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer= stringWriter.getBuffer();
		return buffer.toString();
	}
}
