package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import shm.cool.common.Const;
import shm.model.Member;
import shm.model.OkDialy;


public class SaveController extends OkDialyController {

    @Override
    public Navigation runInTx() {

        List<String> items = createItemsFromRequest();
        if (items.isEmpty()) {
            return null;
        }
        Date dialyDate = asDate("okDialyDate", Const.DATE_FORMAT);
        OkDialy okDialy = getDialy(dialyDate);
        okDialy.setItems(items);
        okDialyDao.makePersistent(okDialy);

        return null;
    }
    
    private OkDialy getDialy(Date dialyDate) {
        Member member = getMember();
        OkDialy okDialy = okDialyDao.find(member, dialyDate);
        if (okDialy == null) {
            okDialy = new OkDialy();
            okDialy.setMember(member);
            okDialy.setDialyDate(dialyDate);
        }
        return okDialy;
    }
    
    private List<String> createItemsFromRequest() {
        String[] param = paramValues("item");
        if (param == null) {
            return new ArrayList<String>(0);
        }
        
        List<String> items = new ArrayList<String>(10);
        boolean empty = true;
        for (String item : param) { 
            if (!StringUtil.isEmpty(item)) {
                empty = false;
            }
            items.add(item);
        }
        return empty ? new ArrayList<String>(0) : items;
    }
}
