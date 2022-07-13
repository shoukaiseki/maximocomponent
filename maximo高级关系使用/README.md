# maximo高级关系使用

应用程序设计器里面的关系名

![image](https://gitee.com/shoukaiseki/maximocomponent/raw/master/maximo%E9%AB%98%E7%BA%A7%E5%85%B3%E7%B3%BB%E4%BD%BF%E7%94%A8/img/001.png)

数据库配置中的关系配置,where条件为1=2,那么在查询是肯定是无数据的

![image](https://gitee.com/shoukaiseki/maximocomponent/raw/master/maximo%E9%AB%98%E7%BA%A7%E5%85%B3%E7%B3%BB%E4%BD%BF%E7%94%A8/img/002.png)

我们来看最后页面显示的结果

![image](https://gitee.com/shoukaiseki/maximocomponent/raw/master/maximo%E9%AB%98%E7%BA%A7%E5%85%B3%E7%B3%BB%E4%BD%BF%E7%94%A8/img/003.png)

原因是,这个结果集所属的类是 psdi.app.workorder.virtual.ShowTasksSet 

该类继承与psdi.mbo.FauxMboSet,而在类中取了另外一个关系的集合

#### psdi.app.workorder.virtual.ShowTasksSet 类代码

```Java

    public final void setOwner(MboRemote mbo) throws MXException, RemoteException {
        super.setOwner(mbo);
	//看此句
        this.realSet = (ChildrenSet)this.getOwner().getMboSet("CHILDREN");
        this.setMboSetInfo(this.realSet.getMboSetInfo());
        this.realSet.addListener(this);
    }

```

可以看出,取的是 CHILDREN 关系所属集合,而 CHILDREN 又是 psdi.app.workorder.virtual.ChildrenSet 处理的

#### psdi.app.workorder.virtual.ChildrenSet 类代码

```Java
public final void setOwner(MboRemote mbo) throws MXException, RemoteException {
	super.setOwner(mbo);
	this.realSet[0] = (WOActivitySet)mbo.getMboSet("WOACTIVITY");
	this.realSet[1] = (WOReleaseSet)mbo.getMboSet("WORELEASE");
	this.realSet[2] = (WOChangeSet)mbo.getMboSet("WOCHANGE");
	this.realSet[3] = (WOSet)mbo.getMboSet("WOWORKORDER");
	this.setMboSetInfo(this.realSet[0].getMboSetInfo());

	for(int i = 0; i < this.realSet.length; ++i) {
		this.realSet[i].addListener(this);
	}

}
```

在这里我们可以看到取的是 WOACTIVITY 关系



以下为psdi.app.workorder.WO 中的代码,可以发现 SHOWTASKS 是固定的类psdi.app.workorder.virtual.ShowTasksSet去处理的,所以跟关系里面的where条件无关

而关系里面的只是用于页面解析时候使用的,不可获取,where条件可以,随意,但是子对象必须一致

```Java
    public MboSetRemote getMboSet(String name) throws MXException, RemoteException {
        this.initFauxMboSetLookup();
        name = name.toUpperCase();
        Object faux = this.fauxMboSetLookup.get(name);
        if (faux == null) {
            return super.getMboSet(name);
        } else if (faux instanceof MboSetRemote) {
            return (MboSetRemote)faux;
        } else {
            RelationInfo ri = this.getMboSetInfo().getRelationInfo(name);
            if (ri == null) {
                return super.getMboSet(name);
            } else {
                String fauxClassName = (String)faux;

                MboSetRemote mboSet;
                try {
                    Class fauxClass = Class.forName(fauxClassName);
                    Constructor fauxConstructor = fauxClass.getConstructor(MboRemote.class);
                    mboSet = (MboSetRemote)fauxConstructor.newInstance(this);
                } catch (Exception var8) {
                    throw new MXSystemException("system", "FauxFailure", var8);
                }

                mboSet.setOwner(this);
                mboSet.setRelationName(name);
                SqlFormat sf = new SqlFormat(this, ri.getSqlExpr());
                mboSet.setRelationship(sf.format());
                this.fauxMboSetLookup.put(name, mboSet);
                return mboSet;
            }
        }
    }



    private void initFauxMboSetLookup() {
        if (this.fauxMboSetLookup == null) {
            this.fauxMboSetLookup = new HashMap();
            this.fauxMboSetLookup.put("CHILDREN", "psdi.app.workorder.virtual.ChildrenSet");
            this.fauxMboSetLookup.put("SHOWCHILDREN", "psdi.app.workorder.virtual.ShowChildrenSet");
            this.fauxMboSetLookup.put("SHOWTASKS", "psdi.app.workorder.virtual.ShowTasksSet");
            this.fauxMboSetLookup.put("SHOWPLANMATERIAL", "psdi.app.workorder.virtual.ShowPlanMaterialSet");
            this.fauxMboSetLookup.put("SHOWPLANLABOR", "psdi.app.workorder.virtual.ShowPlanLaborSet");
            this.fauxMboSetLookup.put("SHOWPLANTOOL", "psdi.app.workorder.virtual.ShowPlanToolSet");
            this.fauxMboSetLookup.put("SHOWACTUALMATERIAL", "psdi.app.workorder.virtual.ShowActualMaterialSet");
            this.fauxMboSetLookup.put("SHOWACTUALLABOR", "psdi.app.workorder.virtual.ShowActualLaborSet");
            this.fauxMboSetLookup.put("SHOWACTUALTOOL", "psdi.app.workorder.virtual.ShowActualToolSet");
            this.fauxMboSetLookup.put("SHOWWOHIERARCHY", "psdi.app.workorder.virtual.ShowWOHierarchy");
            this.fauxMboSetLookup.put("SHOWALLPLANMATERIAL", "psdi.app.workorder.virtual.ShowAllPlanMaterialSet");
            this.fauxMboSetLookup.put("SHOWALLPLANLABOR", "psdi.app.workorder.virtual.ShowAllPlanLaborSet");
            this.fauxMboSetLookup.put("SHOWALLPLANTOOL", "psdi.app.workorder.virtual.ShowAllPlanToolSet");
            this.fauxMboSetLookup.put("SHOWINVRESERVE", "psdi.app.workorder.virtual.ShowInvReserveSet");
            this.fauxMboSetLookup.put("SHOWASSIGNMENT", "psdi.app.workorder.virtual.ShowAssignmentSet");
            this.fauxMboSetLookup.put("SHOWPLANSERVICE", "psdi.app.workorder.virtual.ShowPlanServiceSet");
            this.fauxMboSetLookup.put("SHOWALLPLANSERVICE", "psdi.app.workorder.virtual.ShowAllPlanServiceSet");
            this.fauxMboSetLookup.put("SHOWPLANITEM", "psdi.app.workorder.virtual.ShowPlanItemSet");
            this.fauxMboSetLookup.put("SHOWALLPLANITEM", "psdi.app.workorder.virtual.ShowAllPlanItemSet");
            this.fauxMboSetLookup.put("SHOWACTUALSERVICE", "psdi.app.workorder.virtual.ShowActualServiceSet");
        }

        this.fauxMboSetLookup.put("SHOWTASKRELATION", "psdi.app.workorder.virtual.ShowTaskRelationSet");
        this.fauxMboSetLookup.put("SHOWALLTASKRELATION", "psdi.app.workorder.virtual.ShowAllTaskRelationSet");
    }
```
