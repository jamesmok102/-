package cn.minishop.shop.util;

import cn.minishop.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ShopcarCookieUtil {
    public static void addCar(HttpServletRequest request, HttpServletResponse response, int cid, int count) {
        CookieUtil.save(response,request,"sc-"+String.valueOf(cid), String.valueOf(count),50000);
    }
    public static Map<Integer,Integer> loadCar(HttpServletRequest request) {
        Map<Integer,Integer> result = new HashMap<Integer,Integer>();
        Map<String,String> map = CookieUtil.load(request);
        Iterator<Map.Entry<String,String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String,String> me = iter.next();
            if (me.getKey().startsWith("sc-")){
                result.put(Integer.parseInt(me.getKey().split("-")[1]),Integer.parseInt(me.getValue()));
            }
        }
        return result;
    }
    public static void removeCar(HttpServletRequest request, HttpServletResponse response, Set<Integer> ids) {
        Iterator<Integer> iter = ids.iterator();
        while (iter.hasNext()) {
            CookieUtil.save(response,request,"sc-"+String.valueOf(iter.next()),"0",0);
        }
    }

    public static void clearCar(HttpServletRequest request, HttpServletResponse response){
        CookieUtil.clear(request,response);
    }
}
