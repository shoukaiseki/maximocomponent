package org.shoukaiseki.app.autobsh;

import java.rmi.RemoteException;



import psdi.mbo.*;
import psdi.util.MXException;

public class AutoBeanShell extends Mbo implements AutoBeanShellRemote {
	private RunMboBeanShellClassFunction rmb=null;
	public AutoBeanShell(MboSet ms) throws RemoteException  {
		super(ms);
		// TODO Auto-generated constructor stub
		rmb=new RunMboBeanShellClassFunction(this);
	}
	
	@Override
	public void add() throws MXException, RemoteException {
		// TODO Auto-generated method stub
		rmb.runBeanShellClassFunction("add", "Before",true);
		super.add();
		rmb.runBeanShellClassFunction("add", "After",true);
	}
	
	@Override
	public void init() throws MXException {
		// TODO Auto-generated method stub

		/**
		try {
			rmb.runBeanShellClassFunction("init", "Before",false);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		 **/
		super.init();
		//init 触发容易影响列表显示性能,暂不做处理
		/**
		try {
			rmb.runBeanShellClassFunction("init", "After",false);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		 **/

	}
}
