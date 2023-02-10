package sv.com.bandesal.pruebatecnica.serviceImpl;

import sv.com.bandesal.pruebatecnica.exceptions.ModelNotFoundException;
import sv.com.bandesal.pruebatecnica.repository.IGenericRepository;
import sv.com.bandesal.pruebatecnica.service.ICRUD;
import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T, ID> {
	
	protected abstract IGenericRepository<T, ID> getRepo();

	public List<T> findAll(){
		return getRepo().findAll();
	}

	public T findById(ID id) {
		return getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: "+id));
	}
	
	public void delete(ID id) {
		getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: "+id));
		getRepo().deleteById(id);
	}
}