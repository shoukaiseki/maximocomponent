package org.shoukaiseki.expand;

/**
 * org.shoukaiseki.expand.CodeLineStackTrace <br>
 *
 * @author 蒋カイセキ    Japan-Tokyo  2017-09-25 15:00:25<br>
 * ブログ http://shoukaiseki.blog.163.com/<br>
 * E-メール jiang28555@Gmail.com<br>
 **/
public class CodeLineStackTrace {

    /**
     * 获取当前行代码执行的堆栈信息
     * @return
     */
    public static StringBuffer getStackTraceCurrentline(){
        StringBuffer sb=new StringBuffer();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean b=false;
        for (int i=0;i<stackTrace.length;i++){
            StackTraceElement stackTraceElement = stackTrace[i];
            String methodName = stackTraceElement.getMethodName();
            String className = stackTraceElement.getClassName();
            int lineNumber = stackTraceElement.getLineNumber();
            String fileName = stackTraceElement.getFileName();
            if(b){
                sb.append(className);
                sb.append(".").append(methodName);
                sb.append("(");
                sb.append(fileName);
                sb.append(":");
                sb.append(lineNumber);
                sb.append(")");
                break;
            }else{
                //如果为本类本方法之前的代码都忽略,ibm的jdk 和 oracle的jdk堆栈信息不同,判断类名方法名效果最好
                if(CodeLineStackTrace.class.getName().equals(className)&&"getStackTraceCurrentline".equals(methodName)){
                    b=true;
                }
            }
        }
        return sb;
    }

    /**
     *  获取代码执行的堆栈跟踪
     * @return  堆栈字符串信息
     */
    public static StringBuffer getStackTrace(){
        StringBuffer sb=new StringBuffer();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean b=false;
        for (int i=0;i<stackTrace.length;i++){
            StackTraceElement stackTraceElement = stackTrace[i];
            String methodName = stackTraceElement.getMethodName();
            String className = stackTraceElement.getClassName();
            int lineNumber = stackTraceElement.getLineNumber();
            String fileName = stackTraceElement.getFileName();
            if(b){
                sb.append("\tCodeLineStackTrace\t");
                sb.append(className);
                sb.append(".").append(methodName);
                sb.append("(");
                sb.append(fileName);
                sb.append(":");
                sb.append(lineNumber);
                sb.append(")");
                sb.append("\n");
            }else{
                //如果为本类本方法之前的代码都忽略,ibm的jdk 和 oracle的jdk堆栈信息不同,判断类名方法名效果最好
                if(CodeLineStackTrace.class.getName().equals(className)&&"getStackTrace".equals(methodName)){
                    b=true;
                }
            }
        }
        return sb;
    }
}
