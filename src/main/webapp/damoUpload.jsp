<%@page contentType = "text/html;charset=utf-8" %>
<%@page import = "java.io.*,
                   java.util.*,
                   com.oreilly.servlet.MultipartRequest,
                   com.oreilly.servlet.multipart.DefaultFileRenamePolicy,
                   java.text.SimpleDateFormat,
                   java.util.Date,
                   java.io.File,
                   java.net.URLEncoder,
                   java.text.DecimalFormat
                   "


%>
<%

	//날짜변로 폴더 만들기
//sysdate
	Date today = new Date();
	SimpleDateFormat format1, format2;

	format1 = new SimpleDateFormat("yyyyMMdd");
	//paramMap.put("nowDate", format1.format(today));




	int sizeLimit = 20 * 1024 * 1024; // 20M
	Calendar calendar = Calendar.getInstance();
	String yearPath = String.valueOf(calendar.get(Calendar.YEAR));
	String monthPath = String.valueOf(new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1));

	//로컬
	//String rootpath = "D:\\attache\\hsq\\upload\\editor\\";// + yearPath + File.separator + monthPath + File.separator;
	//개발
	String rootpath = "E:\\stwMis\\hsq\\upload\\editor\\";// + yearPath + File.separator + monthPath + File.separator;
/*
	List<String> paths = new ArrayList<>();
	paths.add(yearPath);
	paths.add(monthPath);

	for(String path : paths){
		rootpath = rootpath + path + File.separator;
		File f = new File(rootpath);
		if(f.isDirectory()) {
			System.out.println("디랙토리 있음");
		} else {
			f.mkdir();
		}
	}
*/
	MultipartRequest multi = new MultipartRequest(request, rootpath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy() );

	/*로컬*/
	//String fileName = "http://55.60.234.58:9002/damoDownload.jsp?filename=/" + yearPath + "/" + monthPath + "/" + URLEncoder.encode(multi.getFilesystemName("damoImgFile"), "UTF-8");
	//String fileName = "http://55.60.234.58:9002/damoDownload.jsp?filename=/" + URLEncoder.encode(multi.getFilesystemName("damoImgFile"), "UTF-8");
	/*개발*/
	String fileName = "http://55.60.100.131:9002/damoDownload.jsp?filename=/" + URLEncoder.encode(multi.getFilesystemName("damoImgFile"), "UTF-8");
	/*운영*/
	//String fileName = "http://10.0.200.10:9002/damoDownload.jsp?filename=/" + URLEncoder.encode(multi.getFilesystemName("damoImgFile"), "UTF-8");

	out.println(fileName);

%>
