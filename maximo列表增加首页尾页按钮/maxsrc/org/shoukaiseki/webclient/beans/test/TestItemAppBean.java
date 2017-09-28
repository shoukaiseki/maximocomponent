package org.shoukaiseki.webclient.beans.test;

import org.shoukaiseki.expand.CodeLineStackTrace;
import org.shoukaiseki.webclient.utils.DataBeanUtils;
import psdi.mbo.MboRemote;
import psdi.util.CipherPlusBase64;
import psdi.webclient.applet.attachimage.Base64;
import psdi.webclient.components.ToggleImage;
import psdi.webclient.controls.Table;
import psdi.webclient.system.beans.DataBean;
import psdi.webclient.system.controller.BoundComponentInstance;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * org.shoukaiseki.webclient.beans.test.TestItemAppBean <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-27 16:20:04<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class TestItemAppBean extends psdi.webclient.beans.item.ItemAppBean{


    public void ASUS()throws  Exception{
        System.out.println("asus.....");
        MboRemote mbo = getMbo();
        MboRemote imglib = mbo.getMboSet("IMGLIB").getMbo(0);
        InputStream in = null;
        if(imglib!=null){
            imglib.delete();
            imglib=mbo.getMboSet("IMGLIB").add();
            imglib.setValue("IMAGENAME","001.jpg");
            imglib.setValue("MIMETYPE","image/jpeg");
            System.out.println("imglib is not null");
            System.out.println("image="+imglib.getBytes("IMAGE"));
            System.out.println("image="+imglib.getDatabaseValue("IMAGE"));
            // base64加密
//            String image = Base64.encode(imglib.getBytes("IMAGE"));
//            System.out.println("image="+image);
            System.out.println("isnull="+imglib.isNull("IMAGE"));
            try {
                File file=new File("C:\\tmp\\001.jpg");
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                // 根据文件创建文件的输入流
                in = new FileInputStream(file);
                // 创建字节数组
                // 读取内容，放到字节数组里面
                byte[] buffer = new byte[256];
                int n;
                while ((n = in.read(buffer)) >= 0) {
                    out.write(buffer, 0, n);
                }
                byte[] bytes = out.toByteArray();
                System.out.println("bytes="+bytes);
                imglib.setValue("IMAGE",bytes);
                imglib.getThisMboSet().save();
                System.out.println("save..");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            imglib=mbo.getMboSet("IMGLIB").add();
            imglib.setValue("IMAGENAME","002.jpg");
            imglib.setValue("MIMETYPE","image/jpeg");

            try {
                File file=new File("C:\\tmp\\002.jpg");
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                // 根据文件创建文件的输入流
                in = new FileInputStream(file);
                // 创建字节数组
                // 读取内容，放到字节数组里面
                byte[] buffer = new byte[256];
                int n;
                while ((n = in.read(buffer)) >= 0) {
                    out.write(buffer, 0, n);
                }
                byte[] bytes = out.toByteArray();
                imglib.setValue("IMAGE",bytes);
                imglib.getThisMboSet().save();

                System.out.println("save....");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(in!=null){
            try {
                // 关闭输入流
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        psdi.webclient.beans.common.RecordImageBean sadfsd;
        ToggleImage sfsd;
        psdi.webclient.system.runtime.DatasrcDescriptor sdfsda;
        psdi.app.system.ImgLibSet safs;
        Table sfsdf;

    }

   public void  movetonextrow() throws Exception{
       System.out.println("movetonextrow");
       DataBean dataBean = app.getDataBean("results_showlist");
       System.out.println("CurrentRow="+dataBean.getCurrentRow());
       dataBean.nextrow();
       System.out.println("CurrentRow="+dataBean.getCurrentRow());

       StringBuffer stackTrace = CodeLineStackTrace.getStackTrace();
       System.out.println(stackTrace.toString());
       psdi.webclient.system.session.WebClientSession dfasd;
//       DataBeanUtils.movetorow(1,dataBean);
   }


    public void moveto5page()throws  Exception{
        DataBean dataBean = app.getDataBean("results_showlist");
        int pageRowCount = dataBean.getPageRowCount();
        int page=5;
        if(dataBean.getMboSet().count()>=pageRowCount*page-1){
            DataBeanUtils.movetorow(pageRowCount*page,dataBean);
        }else{
            page=dataBean.getMboSet().count();
            DataBeanUtils.movetorow(page,dataBean);
        }
        System.out.println("moveto10page");

    }

    public void moveto205row()throws  Exception{

        System.out.println("moveto205row");
        DataBean dataBean = app.getDataBean("results_showlist");
        int pageRowCount = dataBean.getPageRowCount();
        System.out.println("pageRowCount="+pageRowCount);
        System.out.println(".........");
        int rownum=205;
        DataBeanUtils.movetorow(rownum,dataBean);
    }


}
