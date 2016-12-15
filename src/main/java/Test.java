import org.slf4j.Logger;
import uriquery.Nquery;


import java.io.IOException;

/**
 * Created by dayong.gao on 2016/12/8.
 */
public class Test {
    Logger logger = org.slf4j.LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws IOException {
        Nquery query = new Nquery("D://test.txt");
        query.countQuery();
        query.topNOfHttp();
        query.countOfType();
        query.countOfAAA();
    }

}
