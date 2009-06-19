package shm.common;

import javax.jdo.PersistenceManager;

import org.slim3.jdo.ModelMeta;
import org.slim3.jdo.SelectQuery;

public class Select {
    private PersistenceManager pm;
    
    public Select(PersistenceManager pm) {
        this.pm = pm;
    }
    
    /**
     * Creates a new {@link SelectQuery}.
     * 
     * @param <M>
     *            the model type
     * @param modelMeta
     *            the meta data of model
     * @return a new {@link SelectQuery}
     */
    public <M> SelectQuery<M> from(ModelMeta<M> modelMeta) {
        return new SelectQuery<M>(pm, modelMeta.getModelClass());
    }

    /**
     * Creates a new {@link SelectQuery}.
     * 
     * @param <M>
     *            the model type
     * @param modelClass
     *            the model class
     * @return a new {@link SelectQuery}
     */
    public <M> SelectQuery<M> from(Class<M> modelClass) {
        return new SelectQuery<M>(pm, modelClass);
    }
}
