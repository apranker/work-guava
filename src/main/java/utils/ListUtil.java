package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dayong.gao on 2016/12/9.
 */
public class ListUtil {

    /**
     * 对List中数据进行统计，以Map形式返回结果
     *
     * @param datalist 需要统计的list
     * @return 结果Map, key为数据，Value为统计结果
     */
    public static Map<String, Integer> countListToMap(List<String> datalist) {
        HashMap<String, Integer> resultmap = new HashMap<String, Integer>();
        /* 借助Map实现对List的统计 不存在则添加，存在则数量++*/
        for (String s : datalist) {
            if (!resultmap.containsKey(s)) {
                resultmap.put(s, 1);
            } else {
                resultmap.put(s, resultmap.get(s) + 1);
            }
        }
        return resultmap;
    }
}
