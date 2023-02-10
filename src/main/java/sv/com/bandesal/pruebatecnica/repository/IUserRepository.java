package sv.com.bandesal.pruebatecnica.repository;

import sv.com.bandesal.pruebatecnica.model.User;

public interface IUserRepository extends IGenericRepository<User, Integer> {

    User findByEmail(String email);
}