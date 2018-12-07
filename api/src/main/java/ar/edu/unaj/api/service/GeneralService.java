package ar.edu.unaj.api.service;

import java.util.List;

public interface GeneralService<T, I> {

    public void create (T target) throws Exception;
    public List<T> findAll ();
    public T findById (I id);
    public void update (T target) throws Exception;
    public void delete (I id) throws Exception;
    public void disable (I id) throws Exception;
}
