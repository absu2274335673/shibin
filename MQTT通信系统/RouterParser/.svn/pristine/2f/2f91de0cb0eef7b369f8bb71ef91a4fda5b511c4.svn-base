import java.util.List;

import net.engyne.accountdao.AccountDao;
import net.engyne.domain.Account;

public class Test 
{
	@org.junit.Test
	public void test1()
	{
		String topic="/engyne/1/161/169575fa084a886473797959";
		String arr[]=topic.split("/");
		String teamid="1";
		String username="shibin";
		String topic2="/engyne/"+teamid+"/"+arr[3]+"/"+username;
		System.out.println(topic2);
	}
	@org.junit.Test
	public void test2()
	{
		List<Account> accounts=AccountDao.getStaffList("1", 1);
		for(int i=0;i<accounts.size();i++)
		{
			System.out.println(accounts.get(i).getUsername());
		}
	}
}
