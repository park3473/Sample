
package com.system.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class SUtil {
	public static final Pattern SCRIPTS = Pattern.compile("<(?i)script[^>]*>.*?</(?i)script>", Pattern.DOTALL);

	public static String getFileToMd5(String fileName) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");

		FileInputStream fis = new FileInputStream(new File(fileName));
		BufferedInputStream bis = new BufferedInputStream(fis);
		DigestInputStream dis = new DigestInputStream(bis, md5);

		while (dis.read() != -1)
			;

		byte[] hash = md5.digest();

		dis.close();
		return byteArray2Hex(hash);
	}

	public static String getParameterValues(String parameter) 
	{

		String rtn = "";
		if (parameter == null) 
		{
			return null;
		}
		
		StringBuffer strBuff = new StringBuffer();
		
		for (int i = 0; i < parameter.length(); i++)
		{
			char c = parameter.charAt(i);

			switch (c) 
			{

				case 60: // '<'

					strBuff.append("&lt;");

					break;

				case 62: // '>'

					strBuff.append("&gt;");

					break;

				case 38: // '&'

					strBuff.append("&amp;");

					break;

				case 34: // '"'

					strBuff.append("&quot;");

					break;

				case 39: // '\''

					strBuff.append("&apos;");

					break;

				default:

					strBuff.append(c);

					break;
			}
		}
		
		return strBuff.toString();
	}
	
	private static String byteArray2Hex(byte[] hash) {
		String resultData;
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02X", b);
		}
		resultData = formatter.toString();
		formatter.close();
		return resultData;
	}
	
	public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
	public boolean CheckNumber(String str) {
		char check;

		if (str.equals("")) {
			return false;
		}

		try
		{
			Double.parseDouble(str);
		}catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}
	
	public int getHaxStringTOInt(String data)
	{
		return Integer.parseInt( data, 16 );	
	}
	public String getIntTOHax(int data)
	{
		return Integer.toHexString( data );
	}	
	public static String analyticsString = "-1";
	public static String parameterLog(Log log, HttpServletRequest request)
	{
		String rtn = "?";
		log.debug(" log.isDebugEnabled() \t:  " + log.isDebugEnabled());
		//System.out.println("log.isDebugEnabled() : " + log.isDebugEnabled());
		if (log.isDebugEnabled()) {
			log.debug(" getMethod GET OR POST URI \t:  "
					+ request.getRequestURI());

			Set<String> keySet = request.getParameterMap().keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String value = request.getParameter(key);
				rtn = rtn + key + "=" + value + "&";
				log.debug("key : " + key + ", value : " + value);
			}
			
		}
		return rtn;
	}
	
	public String getDate ( int iDay ) 
	{
	    Calendar temp=Calendar.getInstance ( );    
	    temp.add ( Calendar.DAY_OF_MONTH, iDay );
	     
	    int nYear = temp.get ( Calendar.YEAR );
	    int nMonth = temp.get ( Calendar.MONTH ) + 1;
	    int nDay = temp.get ( Calendar.DAY_OF_MONTH );
	     
	    StringBuffer sbDate=new StringBuffer ( );
	    sbDate.append ( nYear+"-" );
	     
	    if ( nMonth < 10 ) 
	        sbDate.append ( "0" );
	        sbDate.append ( nMonth+"-" );
	 
	    if ( nDay < 10 ) 
	        sbDate.append ( "0" );
	        sbDate.append ( nDay+"" );
	 
	    return sbDate.toString ( );
	}	
	
	public static String getTagDel_script(String data)
	{
		Matcher m;
		m = SCRIPTS.matcher(data);
		String str = m.replaceAll("");
		return str;
	}
	
	public static String getLangAge(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("ssion_langage");
		if(user_id == null)
		{
			user_id = "kr";
			session.setAttribute("ssion_langage", "kr");
		}
		return user_id;
	}
	public static String getUserId(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String user_id = session.getAttribute("UserId")+"";
		if(user_id.equals("null"))
		{
			user_id = "";
		}
		return user_id;
	}
	public static String getUserLevel(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String level = (String)session.getAttribute("UserLevel");
		return level;
	}
	public  String getLoginCheck(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("Login");
		return login;
	}
	public static String StringReplace(String str){       
		final int CNT_SYS_DATE = 13;				// �뙆�씪紐� �븵�뿉 遺숈� �떆�뒪�뀥 �떆媛�
		final int LMT_CNT_FN = 30 + CNT_SYS_DATE;	// DB�뿉 �뱾�뼱媛� �븳 媛쒖쓽 �뙆�씪紐� 理쒕�湲몄씠 
		final int ERR_CNT_FN = 5;
		int pos = str.lastIndexOf( "." );
		String ext = str.substring( pos + 1 );

		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
	    str =str.replaceAll(match, "");
	    
	    if (str.length() > LMT_CNT_FN) {
	    	str = str.substring(0, LMT_CNT_FN);

	    } else if(str.length() > ERR_CNT_FN) {
	    	str = str.substring(0, str.length()-3);
	    }
	    return str+"."+ext;
	}
	
		
	public static String setFileUpload(MultipartHttpServletRequest multipartRequest, String drv)
	{
		Iterator<String> itr =  multipartRequest.getFileNames();
		String fnames="";
		
	    while (itr.hasNext())
	    { 
	    	String fname="";
	    	MultipartFile mpf = multipartRequest.getFile(itr.next());
		    String originFileName = mpf.getOriginalFilename();
		    
		   // System.out.println("---nks : multipartRequest.getAttributeNames() : " + mpf.getName());
		    
		    if(originFileName.equals(""))
		    {
		    	continue;
		    }
			 File desti = new File(drv);
		  	 if(!desti.exists())
			 {
				desti.mkdirs();
			 }
		  	 
		  	 if( mpf.getName().indexOf("TITLE") > -1)
		  	 {
		  		long time = System.currentTimeMillis();
			  	if(!originFileName.equals(""))
			  	{
			  		fname = "titleimage_"+time+"_"+originFileName;	
			  	} 
		  	 }else
		  	 {
		  		long time = System.currentTimeMillis();
			  	if(!originFileName.equals(""))
			  	{
			  		fname = time+"_"+originFileName;	
			  	}	 
		  	 }
		  	

		  	fname = StringReplace(fname);
		  	
		  	fname = fname.trim(); 
		  	String rname = "";
		  	for(int i = 0; i < fname.length(); i++)
		  	{
		  		if(fname.charAt(i) != ' ')
		  		{
		  			rname += fname.charAt(i);
		  		}
		  	}
		  	fname = rname;
		  	//System.out.println("fname = "+fname);
		  	File file = new File(drv+fname);
			
			if(! file.exists()) 
			{
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}else 
			{
				file.delete();
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			try {
				mpf.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			try {
				fname = URLEncoder.encode(fname, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			fnames += fname + ",";
        }
	    if(fnames.length() > 0)
	    {
	    	fnames = fnames.substring(0, fnames.length()-1);
	    }
	    return fnames;
	}

	
	
	public static String getSHA256(String str){

		String SHA = ""; 

		try{

			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 

			sh.update(str.getBytes()); 

			byte byteData[] = sh.digest();

			StringBuffer sb = new StringBuffer(); 

			for(int i = 0 ; i < byteData.length ; i++){

				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;

	}

	public static String getFileRead(String filePath)
	{
		BufferedReader reader;
		String dataFull = "";
		
		try{
			FileInputStream fis = new FileInputStream(new File(filePath)); 

			InputStreamReader isr = new InputStreamReader(fis,"UTF-8"); 

			BufferedReader br = new BufferedReader(isr);	

			while(true){
				String str = br.readLine();
				if(str == null)
				{
					break;
				}
				dataFull += str + "\n";
				//System.out.println(str);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return dataFull;
	}
	public static void setFileWrite(String filePath, String fileData)
	{
		
		File file = new File(filePath);
		if(!file.isDirectory())
		{
			file.mkdirs();
		}
		
		try {  
			BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(filePath), "UTF8"));
			writer.write(fileData); 
			writer.close();  
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace(); 
		}

	}
	public static String getYEARDate()
	{
	    Date today = new Date();
        
	    SimpleDateFormat date = new SimpleDateFormat("yyyy");

	    return date.format(today);
	    
	}
	public static String getYEARMONTHDate()
	{
	    Date today = new Date();
        
	    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM");

	    return date.format(today);
	    
	}
	public static String getNowDateStart01()
	{
	    Date today = new Date();
        
	    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-01");

		
	    return date.format(today);
	    
	}
	public static String getNowDate()
	{
	    Date today = new Date();
        
	    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

		
	    return date.format(today);
	    
	}
	public static String getNowTime()
	{
	    Date today = new Date();
        
	    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	    return time.format(today);
	}
	
	public static String getNowDateTime()
	{
		
		return getNowDate() + " " + getNowTime();
	}

	public static String getBase64(String pwd) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void AlertAndPageMove(HttpServletResponse response , String alertText , String MovePage) throws IOException{
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String Script = "";
		Script += "<script>alert('";
		Script += alertText;
		Script += "');";
		Script += "location.href='";
		Script += MovePage;
		Script += "';</script>";
		
		System.out.println(Script);
		
		out.println(Script);
		out.flush();
		
		
	}

}
