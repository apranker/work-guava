package uriquery;

import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CharsetUtil;
import utils.ListUtil;
import utils.MapUtil;
import utils.StrUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by dayong.gao on 2016/12/8.
 * 请求统计类
 */
public class Query {
    private List<String> uri_type = new ArrayList<String>(); //存放uri请求类型
    private List<String> uri_value = new ArrayList<String>();//存放Http请求接口

    private static final Logger logger = LoggerFactory.getLogger(Query.class);

    public Query(String filepath) throws IOException {
        File testFile = new File(filepath);
        List<String> lines = Files.readLines(testFile, Charset.forName(CharsetUtil.getCharset(filepath)));
        uri_type = StrUtil.getListOfSplit(lines, 1, ' ');
        uri_value = StrUtil.getListOfSplit(lines, 2, ' ');
    }

    /**
     * 统计请求总数
     */
    public int countQuery() {
        logger.info("Count:{}", uri_value.size());
        return uri_value.size();
    }

    /**
     * 获取top n的Http接口
     *
     * @param n 要查询的数量
     */
    public Map<String, Integer> topNOfHttp(int n) {
        List<String> valuelist = new ArrayList<String>();
        valuelist = StrUtil.getListOfSplit(uri_value, 1, '?');
        Map<String, Integer> resultmap = new HashMap<String, Integer>();
        List<Map.Entry<String, Integer>> resultlist = new LinkedList<Map.Entry<String, Integer>>();
        resultlist = MapUtil.valuesort(ListUtil.countListToMap(valuelist));
        /* 遍历结果Map,获取Top n*/
        Iterator<Map.Entry<String, Integer>> result = resultlist.iterator();
        for (int i = 0; i < n && result.hasNext(); i++) {
            Map.Entry<String, Integer> value = result.next();
            resultmap.put(value.getKey(), value.getValue());
            logger.info("uri_value:{},num:{}", value.getKey(), value.getValue());
        }
        return resultmap;
    }

    /**
     * 按请求类型统计数量
     *
     * @return 结果Map, key为类型，Value为数量
     */
    public Map<String, Integer> countOfType() {
        Map<String, Integer> resultmap = new HashMap<String, Integer>();
        resultmap = ListUtil.countListToMap(uri_type);
         /* 遍历结果Map,输出数据*/
        Iterator iter = resultmap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            logger.info("Type:{},Count:{}", entry.getKey(), entry.getValue());
        }
        return resultmap;
    }

    /**
     * 按/AAA 类型统计数量
     *
     * @return 结果Map, key为类型，Value为数量
     */
    public Map<String, Integer> countOfAAA() {
        List<String> valuelist = new ArrayList<String>();
        valuelist = StrUtil.getListOfSplit(uri_value, 1, '/');
        Map<String, Integer> resultmap = new HashMap<String, Integer>();
        resultmap = ListUtil.countListToMap(valuelist);
        /* 遍历结果Map,输出数据*/
        Iterator iter = resultmap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            logger.info("Type:{},Count:{}", entry.getKey(), entry.getValue());
        }
        return resultmap;
    }

    @Override public String toString() {
        return "Query{" +
                "uri_type=" + uri_type +
                ", uri_value=" + uri_value +
                '}';
    }

}
