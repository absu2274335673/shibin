package net.engyne.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.engyne.dao.MongoDao;
import net.engyne.po.RealVote;
import net.engyne.po.RuleQueryVo;
import net.engyne.po.Vote;
import net.engyne.po.VoteQueryVo;
import net.engyne.service.VoteService;
import net.engyne.util.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Path("VoteService")
public class VoteServiceImpl implements VoteService {
	@POST
	@Path("getVoteQuery")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getTemplateQuery(String ids)
	{
		System.out.println(ids);
		JSONObject json=JSONObject.fromString(ids);
		String str=json.getString("ids");
		System.out.println(str);
		JSONArray ja=JSONArray.fromString(str);
		System.out.println(ja.get(0));
		List<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<ja.length();i++)
		{
			System.out.println(ja.get(i));
			list.add((Integer) ja.get(i));
			
		}
		VoteQueryVo vqv=new VoteQueryVo();
		vqv.setIds(list);
		System.out.println(vqv.toString());
		MongoDao mongoDao=MongoDao.getMongoDaoInstance();
		List<Vote> votes=mongoDao.getVoteData("dolina", "vote", vqv.getIds());
		RealVote rv=CommonUtils.ConvertVote(votes);
		 JSONObject jsonObject = JSONObject.fromBean(rv); 
		 return jsonObject.toString();
	}
}
