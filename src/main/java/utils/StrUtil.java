package utils;

import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dayong.gao on 2016/12/8.
 */
public class StrUtil {
    private static final Logger logger = LoggerFactory.getLogger(StrUtil.class);

    /**
     * 对List<String> 中的所有String进行分割,并返回要求index的数据
     * PS：函数不完全，适用性较差，仅针对作业文件
     *
     * @param datalist , 需要分割的字符List，
     * @param index    , 所需数据的下标
     * @param c        , 分割依据字符
     * @return 返回要求Index的数据List
     */
    public static List<String> getListOfSplit(List<String> datalist, int index, char c) {
        List<String> tem = null;
        List<String> result_1 = new ArrayList<String>();
        List<String> result_2 = new ArrayList<String>();
        for (String s : datalist) {
            tem = Splitter.on(c).omitEmptyStrings().splitToList(s);
            result_1.add(tem.get(0));
            if (tem.size() >= 2) {
                //result_1.add(tem.get(0));
                result_2.add(tem.get(1));
            }
        }
        if (index == 1) {
            return result_1;
        } else if (index == 2) {
            return result_2;
        } else {
            logger.warn("参数错误！");
            return null;
        }
    }
}
