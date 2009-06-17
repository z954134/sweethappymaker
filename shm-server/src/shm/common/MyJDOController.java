/**
 * 
 */
package shm.common;

import java.util.ArrayList;
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

}
