package org.shoukaiseki.workflow;

import java.rmi.RemoteException;

import bsh.EvalError;
import bsh.Interpreter;


import org.shoukaiseki.app.autobsh.WriteAutoBeanShellLog;
import psdi.common.action.ActionCustomClass;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.mbo.SqlFormat;
import psdi.util.MXApplicationException;
import psdi.util.MXException;
import psdi.workflow.WFAction;
import psdi.workflow.WFInstance;

/**
 * org.shoukaiseki.workflow.ActionAutoBeanShell <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-06-09 14:50:05<br>
 *         ブログ http://shoukaiseki.blog.163.com/<br>
 *         E-メール jiang28555@Gmail.com<br>
 **/

public class ActionAutoBeanShell
implements ActionCustomClass
{
	private	Interpreter i = new Interpreter();  // Construct an interpreter

	@Override
	public void applyCustomAction(MboRemote mbo, Object[] aobj)
			throws MXException, RemoteException {
		// TODO Auto-generated method stub
		runBeanShellClassFunction(mbo, aobj, true);

	}

	/**
	 * @param mbo 
	 * @param canError	bsh脚本异常时是否可以抛错,false,不抛错
	 * @throws RemoteException
	 * @throws MXException
	 */
	public void runBeanShellClassFunction(MboRemote mbo, Object[] aobj,boolean canError ) throws RemoteException, MXException{
		//工作流表达式验证获取的appName为null
		String action=null;
		for (Object object : aobj) {
			if(object instanceof psdi.workflow.WFInstance){
				WFInstance wf=(WFInstance) object;
			}else if(object instanceof psdi.workflow.WFAction){
				WFAction wf= (WFAction) object;
				action=wf.getString("ACTION");
			}
		}
		SqlFormat sqlformat=new SqlFormat("CLASS='ActionCustomClass' and TABLEFIELD=:1");
		sqlformat.setObject(1, "BEANSHELLTRIGGER", "TABLEFIELD", action);
		String message = sqlformat.format();
		//BEANSHELLTRIGGER的唯一ID
		long uniqueId=0;
		MboSetRemote bshcfSet=null;
		/**是否需要抛错**/
		boolean haserror=false;
		try {
			bshcfSet=mbo.getMboSet("$runBEANSHELLTRIGGER","BEANSHELLTRIGGER",message);
			bshcfSet.setOrderBy("SEQUENCE");
			bshcfSet.reset();
			try {
				i.set("absMbo", mbo);
					i.set("aobj", aobj);
				for (int j = 0; j < bshcfSet.count(); j++) {
					MboRemote bshcf = bshcfSet.getMbo(j);
					uniqueId=bshcf.getUniqueIDValue();
					String bsh = bshcf.getString("SOURCE");
					i.set("bshcfMbo", bshcf);
					i.eval("import psdi.mbo.*;" +
							"import psdi.util.MXException;" +
							"import org.shoukaiseki.expand.StringExpand;" +
							"import psdi.util.MXApplicationException;" +
							"import psdi.server.MXServer;");
					if(bsh!=null&&!bsh.isEmpty()){
						bsh="try {\n"
								+bsh
								+"\n} catch (Exception e) {\n"
								+"	return e;\n"
								+"}\n";
						Object eval = i.eval(bsh);
						if(eval!=null){
							if(eval instanceof MXException){
								throw (MXException)eval;
							}else if(eval instanceof RemoteException){
								throw (RemoteException)eval;
							}else if(eval instanceof Exception){
								((Exception)eval).printStackTrace();
								message+=" 執行bsh腳本錯誤"+((Exception)eval).getMessage();
								WriteAutoBeanShellLog bshlog=new WriteAutoBeanShellLog(mbo);
								bshlog.error("bsh脚本错误,BeanShellTrigger记录ID为"+uniqueId+":"+message);
								haserror=true;
							}
						}
					}
				}
			} catch (EvalError e) {
				// TODO Auto-generated catch block
				//			e.printStackTrace();
				WriteAutoBeanShellLog bshlog=new WriteAutoBeanShellLog(mbo);
				bshlog.error("bsh脚本错误,BeanShellTrigger记录ID为"+uniqueId+":"+message);
				haserror=true;
			}
		} catch (RemoteException e) {
			if(bshcfSet!=null)
				bshcfSet.close();
			throw e;
		} catch ( MXException e) {
			if(bshcfSet!=null)
				bshcfSet.close();
			throw e;
		}finally{
			if(bshcfSet!=null)
				bshcfSet.close();
		}
		/**bsh有错误,抛错**/
		if(haserror&&canError)
			throw new MXApplicationException("bsh脚本错误,请联系管理员,BeanShellTriggerID为"+uniqueId+":"+message, "");
	}
}
