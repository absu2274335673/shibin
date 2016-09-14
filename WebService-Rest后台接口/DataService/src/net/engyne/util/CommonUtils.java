package net.engyne.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.engyne.po.Area;
import net.engyne.po.OneDimension;
import net.engyne.po.RealArea;
import net.engyne.po.RealOneDimension;
import net.engyne.po.RealRule;
import net.engyne.po.RealTemplate;
import net.engyne.po.RealVote;
import net.engyne.po.Rule;
import net.engyne.po.Template;
import net.engyne.po.Vote;

public class CommonUtils {
	public static RealArea ConvertArea(List<Area> areas){
		RealArea ra=new RealArea();
		ra.setTotal(areas.size());
		ra.setList(areas);
		ra.setResult("SUCCESS");
		return ra;
	}
	public static RealRule ConvertRule(List<Rule> rules){
		RealRule rr=new RealRule();
		rr.setTotal(rules.size());
		rr.setList(rules);
		rr.setResult("SUCCESS");
		return rr;
	}
	public static RealTemplate ConvertTemplate(List<Template> templates){
		RealTemplate rt=new RealTemplate();
		rt.setTotal(templates.size());
		rt.setList(templates);
		rt.setResult("SUCCESS");
		return rt;
	}
	public static RealVote ConvertVote(List<Vote> votes){
		RealVote rv=new RealVote();
		rv.setTotal(votes.size());
		rv.setList(votes);
		rv.setResult("SUCCESS");
		return rv;
	}
	public static RealOneDimension ConvertOneDimension(List<OneDimension> oneDimensions){
		RealOneDimension rod=new RealOneDimension();
		List<List<String>> data=new ArrayList<List<String>>();
		for(int i=0;i<oneDimensions.size();i++)
		{
			List<String> map=new ArrayList<String>();
			map.add(oneDimensions.get(i).getGrade());
			map.add(oneDimensions.get(i).getCount()+"");
			data.add(map);
		}
		rod.setData(data);
		rod.setResult("SUCCESS");
		return rod;
	}
}
