package sv.com.bandesal.pruebatecnica.repository;

import sv.com.bandesal.pruebatecnica.model.Role;

public interface IRoleRepository extends IGenericRepository<Role,Integer> {
    Role findByName(String name);
}