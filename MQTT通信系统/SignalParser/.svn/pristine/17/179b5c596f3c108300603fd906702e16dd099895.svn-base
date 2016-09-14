package net.engyne.test;



import com.mongodb.util.JSON;
import net.sf.json.JSONObject;

/**
 * Created by shaobin on 16/4/19.
 */
public class JsonOperation {

    /**
     * 将对象转换为Json
     * @param object 要转换对象
     * @return jsonArray 为返回JSONArray格式
     */
    protected static JSONObject ToJson(Object object) {
        String string = JSON.serialize(object);
//        System.out.println("String:" + string);
        JSONObject jsonObject = JSONObject.fromObject(string);
        return jsonObject;
    }

}
