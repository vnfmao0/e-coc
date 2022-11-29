<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import = "java.io.*,
                   java.util.*,
                   java.io.OutputStream,
                   java.text.DecimalFormat,
                    java.net.URLDecoder"
%>
<%
    Calendar calendar = Calendar.getInstance();
    String yearPath = String.valueOf(calendar.get(Calendar.YEAR));
    String monthPath = String.valueOf(new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1));
    //local
    //String filepath = "D:\\attache\\hsq\\upload\\editor\\";
    //dev
    String filepath = "E:\\stwMis\\hsq\\upload\\editor\\";

    String filename = URLDecoder.decode(request.getParameter("filename"), "UTF-8");

    response.setHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
    OutputStream os = response.getOutputStream();
    FileInputStream fis = new FileInputStream(filepath + filename);
    int ncount = 0;
    byte[] bytes = new byte[512];
    while((ncount = fis.read(bytes)) != -1 ) {
        os.write(bytes, 0, ncount);
    }
    fis.close();
    os.close();
%>