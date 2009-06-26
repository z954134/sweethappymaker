/**
 * 
 */
package shm.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.slim3.controller.JDOController;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanMap;
import org.slim3.util.BeanUtil;

import shm.dao.MemberDao;

/**
 * @author Tsuyoshi
 * 
 */
public abstract class MyJDOController extends JDOController {
    
    private static final Logger logger = Logger.getLogger(MyJDOController.class.getName());
    
    /** メンバーデータアクセス */
    protected MemberDao memberDao;

    
    @Override
    protected void setUp() {
        super.setUp();
        memberDao = new MemberDao(pm);
        logRequest();
    }
    
    private void logRequest() {
        logger.log(Level.FINE, request.getParameterMap().toString());
    }
    
    @Override
    protected Navigation run() {
        tx.begin();
        Navigation navigation = runInTx();
        tx.commit();
        return navigation;
    }
    
    protected Navigation runInTx() {
        String msg = "'runInTx' method must be overrided when 'run' method is NOT overrided.";
        throw new SystemException(msg);
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
    protected final List<BeanMap> copy(List<?> entityList) {
        List<BeanMap> beanMapList = new ArrayList<BeanMap>(entityList.size());
        for (Object e : entityList) {
            BeanMap map = toBeanMap(e);
            beanMapList.add(map);
        }
        return beanMapList;
    }
    
    protected final BeanMap toBeanMap(Object bean) {
        BeanMap map = new BeanMap();
        BeanUtil.copy(bean, map);
        return map;
    }
    
    protected final void saveMessages(String... msgs) {
        List<String> messageList = Arrays.asList(msgs);
        requestScope("messageList", messageList);
    }

}
