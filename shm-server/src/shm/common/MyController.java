/**
 * 
 */
package shm.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.jdo.CurrentPersistenceManager;
import org.slim3.util.BeanMap;
import org.slim3.util.BeanUtil;

/**
 * @author Tsuyoshi
 * 
 */
public abstract class MyController extends Controller {

    private static final Logger logger =
        Logger.getLogger(MyController.class.getName());

    public Navigation run() {
        begin();
        Navigation navigation = runInTx();
        commit();
        return navigation;
    }

    protected final void begin() {
        currentTx().begin();
        logger.fine("Transaction started.");
    }

    protected final void commit() {
        currentTx().commit();
        logger.fine("Transaction committed.");
    }

    protected final void rollback() {
        Transaction tx = currentTx();
        if (tx.isActive()) {
            tx.rollback();
        }
        logger.fine("Transaction rollbacked.");
    }

    private Transaction currentTx() {
        return pm().currentTransaction();
    }

    private PersistenceManager pm() {
        return CurrentPersistenceManager.get();
    }

    protected Navigation runInTx() {
        throw new SystemException(
            "runInTx method must be overrided by subclass, when run method is NOT overrided.");
    }

    @Override
    protected void setUp() {
        super.setUp();
        logRequest();
    }

    private void logRequest() {
        logger.fine(request.getParameterMap().toString());
    }

    /**
     * ベースパスを基準にフォワードする<br>
     * 
     * @param path
     *            フォワード先パス
     * @return 行き先
     */
    protected final Navigation forwardBase(String path) {
        return forward(basePath + path);
    }

    /**
     * モデルのリストをBeanMapのリストにコピーする
     * 
     * @param modelList
     *            モデルのリスト
     * @return BeanMapのリスト
     */
    protected final List<BeanMap> detachAndCopy(List<?> modelList) {
        pm().detachCopyAll(modelList);
        return copy(modelList);
    }
    
    protected final List<BeanMap> copy(List<?> modelList) {
        List<BeanMap> beanMapList = new ArrayList<BeanMap>(modelList.size());
        for (Object model : modelList) {
            BeanMap map = detachAndCopy(model);
            beanMapList.add(map);
        }
        return beanMapList;
    }

    protected final BeanMap detachAndCopy(Object bean) {
        pm().detachCopy(bean);
        return copy(bean);
    }
    
    protected final BeanMap copy(Object bean) {
        BeanMap map = new BeanMap();
        BeanUtil.copy(bean, map);
        return map;
    }

    protected final void saveMessages(String... msgs) {
        List<String> messageList = Arrays.asList(msgs);
        requestScope(Const.MESSAGE_KEY, messageList);
    }

}
