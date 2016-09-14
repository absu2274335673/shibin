package net.engyne.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.engyne.dao.ClientDao;
import net.engyne.service.ClientService;

@Path("ClientService")
public class ClientServiceImpl implements ClientService {

	private ApplicationContext applicationContext;
	public ClientServiceImpl()
	{
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
	
	@POST
	@Path("getLocationQuery")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getLocationQuery()
	{
		ClientDao clientDao=(ClientDao)applicationContext.getBean("clientDao");
		String result=clientDao.locationQuery();
		return result;
	}
//	public static void main(String[] args) {
//		ClientService cs=new ClientService();
//		String str=cs.getLocationQuery();
//		System.out.println(str);
//	}
}
