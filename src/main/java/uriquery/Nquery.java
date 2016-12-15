package uriquery;

import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.io.Files;
import entity.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CharsetUtil;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by dayong.gao on 2016/12/15.
 */
public class Nquery {

    List<UriInfo> uriInfos =new ArrayList<UriInfo>(); //存储URI信息
    private static final Logger logger = LoggerFactory.getLogger(Nquery.class);

    public Nquery(String filepath) throws IOException {
        File testFile = new File(filepath);
        List<String> lines = Files.readLines(testFile, Charset.forName(CharsetUtil.getCharset(filepath))); //获取编码方式进行相应的读取
        List<String> tem = new ArrayList<String>();
        for (String line : lines) {
            tem = Splitter.on(' ').omitEmptyStrings().splitToList(line);
            if(tem.size()!=2){
                throw new RuntimeException("日志数据异常！");
            }
            uriInfos.add(new UriInfo(tem.get(0),tem.get(1)));
        }
    }
    /**
     * 统计请求总数
     */
    public int countQuery() {
        logger.info("Count:{}", uriInfos.size());
        return uriInfos.size();
    }
    /**
     * 获取topN 的Http接口
     *
     * @return Http接口的排序TreeMap Key为统计数量 value为Http接口值
     */
    public TreeMap<Integer,String> topNOfHttp() {
        Multiset<String> resultSet = HashMultiset.create();
        TreeMap<Integer,String> sortMap = new TreeMap<Integer, String>(new Comparator<Integer>(){
            /*改写比较器 ，TreeMap降序输出 */
            public int compare(Integer a,Integer b){
                return b-a;
            }
        });
        for (UriInfo uriInfo : uriInfos) {
            resultSet.add(uriInfo.getUrivalue());
        }
        for (String s : resultSet.elementSet()) {
            sortMap.put(resultSet.count(s),s);
        }
        /*遍历结果TreeMap 输出测试 */
        Iterator it = sortMap.entrySet().iterator();
        for (int i = 0; i <10&&it.hasNext() ; i++) {
            Map.Entry entry =(Map.Entry) it.next();
            logger.info("Http :{} , Count :{}",entry.getValue(),entry.getKey());
        }
        return sortMap;
    }
    /**
     * 获取根据请求类型统计
     */
    public Multiset<String> countOfType() {
        Multiset<String> countSet = HashMultiset.create();
        for (UriInfo uriInfo : uriInfos) {
            countSet.add(uriInfo.getUriType());
        }
        logger.info("GET :{}",countSet.count("GET"));
        logger.info("POST :{}",countSet.count("POST"));
        return countSet;
    }
    /**
     * 获取URI值的第一个字段进行统计
     */
    public Multiset<String> countOfAAA() {
        Multiset<String> resultSet = HashMultiset.create();
        for (UriInfo uriInfo : uriInfos) {
           List<String> resultList = Splitter.on('/').omitEmptyStrings().splitToList(uriInfo.getUrivalue()); //分割每个URI Http接口
            resultSet.add(resultList.get(0));
        }
        /* 遍历输出结果 */
        for (String s : resultSet.elementSet()) {
            logger.info("Http AAA:{},Count:{}",s,resultSet.count(s));
        }
        return resultSet;
    }

}
