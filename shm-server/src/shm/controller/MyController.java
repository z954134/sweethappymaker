/**
 * 
 */
package shm.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import javax.servlet.http.HttpServletResponse;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.jdo.CurrentPersistenceManager;

import shm.cool.common.Const;
import shm.cool.common.SecurityViolationException;
import shm.cool.common.SystemException;

/**
 * @author Tsuyoshi
 * 
 */
public abstract class MyController extends Controller {

    private static final Logger logger =
        Logger.getLogger(MyController.class.getName());

    @Override
    public Navigation run() {
        begin();
        Navigation navigation = runInTx();
        commit();
        return navigation;
    }

    protected final void begin() {
        tx().begin();
        logger.fine("Transaction started.");
    }

    protected final void commit() {
        tx().commit();
        logger.fine("Transaction committed.");
    }

    protected final void rollback() {
        Transaction tx = tx();
        if (tx.isActive()) {
            tx.rollback();
        }
        logger.fine("Transaction rolledback.");
    }

    private Transaction tx() {
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
    protected Navigation handleError(Throwable error) {
            
        if (error instanceof SecurityViolationException) {
            pretendToBe404();
            return null;
        }
        return super.handleError(error);
    }
    
    private void pretendToBe404() {
        try {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (IOException e) {
            logger.log(Level.WARNING, "予期しない例外", e);
        }
    }

    protected final void saveMessages(String... msgs) {
        List<String> messageList = Arrays.asList(msgs);
        saveMessages(messageList);
    }

    protected final void saveMessages(List<String> msgs) {
        requestScope(Const.MESSAGE_KEY, msgs);
    }
    
}
