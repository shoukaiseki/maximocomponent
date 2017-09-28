package org.shoukaiseki.app.autobsh;

import java.rmi.RemoteException;

import bsh.EvalError;
import bsh.Interpreter;

import psdi.mbo.*;
import psdi.util.MXApplicationException;
import psdi.util.MXException;

public class RunMboBeanShellClassFunction {
	private Mbo mbo=null;
	private String appName=null;
	private	Interpreter i = new Interpreter();  // Construct an interpreter
	
	public RunMboBeanShellClassFunction(Mbo mbo) throws RemoteException {
		// TODO Auto-generated constructor stub
		this.mbo=mbo;
		try {
			appName=mbo.getThisMboSet().getParentApp();
			runBeanShellClassFunction("DefaultConstructor", "After",false);
		} catch (MXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param function 
	 * @param when
	 * @param canError	bsh脚本异常时是否可以抛错,false,不抛错
	 * @throws RemoteException
	 * @throws MXException
	 */
	public void runBeanShellClassFunction(String function,String when,boolean canError) throws RemoteException, MXException{
		//工作流表达式验证获取的appName为null
		if(appName==null){
			return;
		}
		SqlFormat sqlformat=new SqlFormat("CLASS='Mbo' and APPNAME=:1 and FUNCTION=:2" +
				" and upper(when)=upper(:3) and TABLEFIELD=:4");
		sqlformat.setObject(1, "BEANSHELLTRIGGER", "APPNAME", appName);
		sqlformat.setObject(2, "BEANSHELLTRIGGER", "FUNCTION", function);
		sqlformat.setObject(3, "BEANSHELLTRIGGER", "WHEN", when);
		sqlformat.setObject(4, "BEANSHELLTRIGGER", "TABLEFIELD", mbo.getName());
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
				for (int j = 0; j < bshcfSet.count(); j++) {
					MboRemote bshcf = bshcfSet.getMbo(j);
					uniqueId=bshcf.getUniqueIDValue();
					String bsh = bshcf.getString("SOURCE");
					i.set("bshcfMbo", bshcf);
					i.eval("import psdi.mbo.*;" +
							"import psdi.util.MXException;" +
							"import org.shoukaiseki.app.expand.StringExpand;" +
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
