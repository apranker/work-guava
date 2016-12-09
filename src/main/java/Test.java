import org.slf4j.Logger;
import uriquery.Query;

import java.io.IOException;

/**
 * Created by dayong.gao on 2016/12/8.
 */
public class Test {
    Logger logger = org.slf4j.LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws IOException {
        Query query = new Query("D://test.txt");
        query.countQuery();
        query.topNOfHttp(10);
        query.countOfType();
        query.countOfAAA();
    }

}
