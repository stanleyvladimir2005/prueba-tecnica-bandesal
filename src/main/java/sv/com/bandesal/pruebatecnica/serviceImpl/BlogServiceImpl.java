package sv.com.bandesal.pruebatecnica.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.com.bandesal.pruebatecnica.model.Blog;
import sv.com.bandesal.pruebatecnica.model.BlogReader;
import sv.com.bandesal.pruebatecnica.repository.IBlogRepository;
import sv.com.bandesal.pruebatecnica.repository.IGenericRepository;
import sv.com.bandesal.pruebatecnica.service.IBlogService;
import java.util.Optional;

@Service
public class BlogServiceImpl extends CRUDImpl<Blog, Integer> implements IBlogService {

    @Autowired
    private IBlogRepository repo;

    @Override
    protected IGenericRepository<Blog, Integer> getRepo() {
        return repo;
    }

    public void createOrUpdateBlog(Blog entity){
        if(entity.getId()  == null){
            entity = repo.save(entity);
        } else {
            Optional<Blog> blog = repo.findById(entity.getId());
            if(blog.isPresent()){
                Blog newEntity = blog.get();
                newEntity.setTitle(entity.getTitle());
                newEntity.setDescription(entity.getDescription());
                newEntity = repo.save(newEntity);
            } else {
                entity = repo.save(entity);
            }
        }
    }
}