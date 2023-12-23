package sv.com.bandesal.pruebatecnica.service;

import java.util.List;

public interface ICRUD<T, ID> {

	List<T> findAll();
	
	T findById(ID id) ;

	void delete(ID id) ;
}