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
     *  获取代码执行的堆栈跟踪
     * @return  堆栈字符串信息
     */
    public static StringBuffer getStackTrace(){
        StringBuffer sb=new StringBuffer();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i=0;i<stackTrace.length;i++){
            if(i<2){
                continue;
            }
            StackTraceElement stackTraceElement = stackTrace[i];
            String methodName = stackTraceElement.getMethodName();
            String className = stackTraceElement.getClassName();
            int lineNumber = stackTraceElement.getLineNumber();
            String fileName = stackTraceElement.getFileName();
            sb.append("\tCodeLineStackTrace\t");
            sb.append(className);
            sb.append(".").append(methodName);
            sb.append("(");
            sb.append(fileName);
            sb.append(":");
            sb.append(lineNumber);
            sb.append(")");
            sb.append("\n");
        }
        return sb;
    }
}
