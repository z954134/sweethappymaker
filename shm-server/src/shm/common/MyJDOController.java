/**
 * 
 */
package shm.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slim3.controller.JDOController;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanMap;
import org.slim3.util.BeanUtil;

/**
 * @author Tsuyoshi
 * 
 */
public abstract class MyJDOController extends JDOController {

    
    
    
    @Override
    protected Navigation run() {
        tx.begin();
        Navigation navigation = runInTx();
        tx.commit();
        return navigation;
    }
    
    protected Navigation runInTx() {
        String msg = "'runInTx' method must be overrided when 'run' method is NOT overrided.";
        throw new RuntimeException(msg);
    }
        
    /**
     * SELECTを発行する。
     * @return SELECT
     */
    protected Select select() {
        return new Select(pm);
    }

    /**
     * ベースパスを基準にフォワードする<br>
     * 
     * @param path フォワード先パス
     * @return 行き先
     */
    protected final Navigation forwardBase(String path) {
        return forward(basePath + path);
    }

    /**
     * エンティティのリストをBeanMapのリストにコピーする
     * @param entityList エンティティのリスト
     * @return BeanMapのリスト
     */
    protected List<BeanMap> copy(List<?> entityList) {
        List<BeanMap> beanMapList = new ArrayList<BeanMap>(entityList.size());
        for (Object e : entityList) {
            BeanMap m = new BeanMap();
            BeanUtil.copy(e, m);
            beanMapList.add(m);
        }
        return beanMapList;
    }
    
    protected void saveMessages(String... msgs) {
        List<String> messageList = Arrays.asList(msgs);
        requestScope("messageList", messageList);
    }

}
