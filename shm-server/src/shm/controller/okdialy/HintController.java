package shm.controller.okdialy;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

import org.slim3.controller.Navigation;
import org.slim3.util.RuntimeExceptionUtil;

import shm.common.MyController;
import shm.common.SystemException;

public class HintController extends MyController {

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(HintController.class.getName());

    private static Properties hints;

    
    
    @Override
    protected void setUp() {
        super.setUp();
        loadHints();
    }

    @Override
    public Navigation run() {
        String nextHintKey = getNextHintKey(asString("previousHintKey"));
        String nextHint = hints.getProperty(nextHintKey);
        if (nextHint == null) {
            throw new SystemException("Hint [" + nextHintKey + "] does not exists.");
        }
        
        requestScope("hintKey", nextHintKey);
        requestScope("hint", nextHint);
        
        return forward("hint.jsp");
    }

    private synchronized void loadHints() {
        if (hints != null) {
            return;
        }
        
        InputStream is = getClass().getResourceAsStream("hints.properties");
        hints = new Properties();
        try {
            hints.load(is);
        } catch (IOException e) {
            RuntimeExceptionUtil.wrapAndThrow(e);
        }
    }

    private String getNextHintKey(String previousHintKey) {
        
        DecimalFormat df = new DecimalFormat();
        df.applyLocalizedPattern("000");

        int cnt = hints.size();
        if (cnt == 0) {
            throw new SystemException("No Hint exists.");
        }

        Random rand = new Random(System.currentTimeMillis());
        String nextHintKey = null;
        do {
            int i = rand.nextInt(cnt) + 1;
            nextHintKey = "hint." + df.format(i);
        } while (nextHintKey.equals(previousHintKey));
        return nextHintKey;
    }
}
