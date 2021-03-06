package net.engyne.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class FileUtil 
{
	public static String getProperty(String name) 
	{
		Properties p = new Properties();
//		FileInputStream in=null;
//		try {
//			String path=new File("").getAbsoluteFile().getParentFile().toString();
//			System.out.println("path:"+path);
//			in = new FileInputStream(path+"/config/config.properties");
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}  
		File f = new File("/Users/binshi/Documents/workspace/config.properties");   
		InputStream in=null;
		try {
			in = new FileInputStream(f);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		String result=null;
		try {
			p.load(in);
			in.close();
			result=p.getProperty(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/*
	public static void setC3P0() throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document doc=db.parse(FileUtil.class.getResourceAsStream("/c3p0-cofig.xml"));
		NodeList list=doc.getElementsByTagName("property");
		for(int i=0;i<list.getLength();i++)
		{
			Element ele=(Element)list.item(i);
			if(ele.getAttribute("name").equals("jdbcUrl"))
			{
				System.out.println(ele.getTextContent());
				ele.setTextContent(FileUtil.getProperty("jdbcUrl"));
				System.out.println(ele.getTextContent());
			}
			else if(ele.getAttribute("name").equals("user"))
			{
				ele.setTextContent(FileUtil.getProperty("user"));
			}
			else if(ele.getAttribute("name").equals("password"))
			{
				ele.setTextContent(FileUtil.getProperty("password"));
			}
			
		}
	TransformerFactory transformerFactory=TransformerFactory.newInstance();
	Transformer transformer=transformerFactory.newTransformer();
	DOMSource domSource=new DOMSource(doc);
	transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	InputStream inputStream2 = FileUtil.class.getResourceAsStream("/in.properties");  
	inputStream2.getClass().getResource("");
	StreamResult result=new StreamResult(new FileOutputStream("/Users/binshi/Documents/workspace/ProtocolParser/src/c3p0-config.xml"));
	transformer.transform(domSource,result);
		
	}
	*/
	
	
}
