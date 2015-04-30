<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.fjx.wechat.config.AppConfig"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="ueditor.Uploader"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.Map"%>

<%
	//request.setCharacterEncoding(Uploader.ENCODEING);
	//response.setCharacterEncoding(Uploader.ENCODEING);
	// 解决中文乱码
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	System.out.println("上传图片开始");
	String currentPath = request.getRequestURI().replace(request.getContextPath(), "");
	String mySavePath = AppConfig.STATIC_PATH;

	File currentFile = new File(currentPath);
	//为了解决tomcat与jetty之间的差异问题，如果不转换jetty会出问题
	currentPath = (currentFile.getParent() + File.separator).replace("\\", "/");

	//加载配置文件
	String propertiesPath = request.getSession().getServletContext()
			.getRealPath(currentPath + "config.properties");
	
	Properties properties = new Properties();

	try {
		properties.load(new FileInputStream(propertiesPath));
	} catch (Exception e) {
		//加载失败的处理
		e.printStackTrace();
	}

	List<String> savePath = Arrays.asList(properties.getProperty("savePath").split(","));

	//获取存储目录结构
	if (request.getParameter("fetch") != null) {
		response.setHeader("Content-Type", "text/javascript");

		//构造json数据
		Iterator<String> iterator = savePath.iterator();

		String dirs = "[";
		while (iterator.hasNext()) {

			dirs += "'" + iterator.next() + "'";
			if (iterator.hasNext()) {
				dirs += ",";
			}
		}
		dirs += "]";
		response.getWriter().print("updateSavePath( " + dirs + " );");
		return;

	}

	Uploader up = new Uploader(request);

	// 获取前端提交的path路径
	String dir = request.getParameter("dir");
	
	//普通请求中拿不到参数， 则从上传表单中拿
	if (dir == null) {
		dir = up.getParameter("dir");
	}

	if (dir == null || "".equals(dir)) {

		//赋予默认值
		dir = savePath.get(0);

		//安全验证
	} else if (!savePath.contains(dir)) {

		response.getWriter()
				.print("{'state':'\\u975e\\u6cd5\\u4e0a\\u4f20\\u76ee\\u5f55'}");
		return;

	}
    // dir:上传图片存储的目录，为了跟AppConfig.STATIC_DOMAIN(在ueditor.config.js文件中设置了imagePath属性，表示预览图片时的路径)一致
    // 不得不在ueditor.Uploader的getPhysicalPathByRoot()方法中加入了“wechat”，不然图片上传路径跟预览路径不一致，导致预览失败
    // 当然image属于静态资源文件，此时还需要设置URL Mapping，否则即使路径一致，也无法加载图片。
    // 在sping-mvc.xml设置<mvc:resource/>属性来完成URL映射
	dir = "/upload/images/" + dir;
	
	up.setSavePath(dir);
	//up.setMySavePath(mySavePath);
	String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
	up.setAllowFiles(fileType);
	up.setMaxSize(500 * 1024); //单位KB
    up.myUpload(false);//true:上传到非站点服务器（apache静态资源服务器）；false:上传到站点服务器
	String resObject = "{'url':'" + up.getUrl() + "','fileType':'" + up.getType() + "','fileName':'" + up.getFileName()+"','title':'" + up.getTitle()
			+ "','state':'" + up.getState() + "','original':'" + up.getOriginalName() + "'}";
	System.out.println("图片上传返回参数："+resObject);
	response.getWriter().print(resObject);
	
%>
