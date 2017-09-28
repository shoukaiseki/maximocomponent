package org.shoukaiseki.crontask;

import org.apache.log4j.Logger;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.server.SimpleCronTask;
import psdi.util.MXApplicationException;
import psdi.util.MXException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * org.shoukaiseki.crontask.AbstractSimpleCronTask <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-08-02 13:03:08<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/

public abstract class AbstractSimpleCronTask extends SimpleCronTask {
   //记录器
	public static Logger log = Logger.getLogger("org.shoukaiseki.crontask");
	//得到年月
	public static SimpleDateFormat dtfmt0 = new SimpleDateFormat("yyyy-MM");
	//得到年月日
	public static SimpleDateFormat dtfmt1 = new SimpleDateFormat("yyyy年MM月dd日");
	//得到年月日时分秒
	public static SimpleDateFormat dtfmt2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	//初始化
	public static String beintimestr = " 00:00:00";
	public static String endtimestr = " 23:59:59";
	
	public HashMap<String, String> params = new HashMap<String, String>();

	public String getParams(String key) {
		String val = params.get(key);
		if (val == null) {
			try {
				val = getParamAsString(key);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return val;
	}

	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}

	public void init() throws MXException {
		super.init();
	}

	@Override
	public void cronAction() {
		log.debug(this.getClass()+" begin action ... ");
		StringBuffer msg = new StringBuffer();
		setCronTaskHistoryMsg(action(msg));
		log.debug(this.getClass()+" end action ...." + msg);
	}

	/**
	 * 定期任务的执行方法
	 * @param msg
	 * @return
	 */
	public  String action(StringBuffer msg){
		try{
		    //错误信息 写到定时任务信息中
			throw new MXApplicationException("#","子类继承之后需要重写 action 方法");
		}catch (Exception e){
			log.error("执行错误:" ,e);
			msg.append(" \r\n 执行错误:" + getErrorInfoFromException(e));
		}
		return msg.toString();
	}

	public void closeMboSet(MboSetRemote msr) {
		if (msr == null)
			return;
		try {
			msr.close();
		} catch (RemoteException e) {
			e.printStackTrace();
			log.error("closeMboSet:", e);
		} catch (MXException e) {
			e.printStackTrace();
			log.error("closeMboSet:", e);
		}
	}

	public static String getErrorInfoFromException(Throwable e) {
		try {
			//StringWriter用于操作字符串
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			//在命令行打印异常信息在程序中出错的位置及原因
			e.printStackTrace(pw);
			return "\r\n" + sw.toString() + "\r\n";
		} catch (Exception e2) {
		}
		return "bad getErrorInfoFromException";
	}

	/**
	 * 装入crontask配置的参数,针对CronTask外调用时候需要使用
	 * @param server
	 * @param cronname
	 */
	public void setParams(MboRemote server, String cronname) {
		MboSetRemote paramSet;
		try {
			log.debug(" Begin Set Params : " + cronname);
			//crontaskparam(Cron 任务参数表)
			paramSet = server.getMboSet("$crontaskparam", "crontaskparam",
					" crontaskname ='" + cronname + "' ");
			MboRemote paramMbo = null;
			String key = "";
			String val = "";
			for (int x = 0; x < paramSet.count(); ++x) {
				paramMbo = paramSet.getMbo(x);
				//parameter=siteid
				key = paramMbo.getString("parameter");
				//比如武汉
				val = paramMbo.getString("value");
				if (this.params.get(key) != null) {
					val = this.params.get(key) + ',' + val;
				}
				this.params.put(key, val);
			}
			log.debug(" Finish Set Params : " + this.params);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
