package sv.com.bandesal.pruebatecnica.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.com.bandesal.pruebatecnica.model.BlogReader;
import sv.com.bandesal.pruebatecnica.repository.IBlogReaderRepository;
import sv.com.bandesal.pruebatecnica.repository.IGenericRepository;
import sv.com.bandesal.pruebatecnica.service.IBlogReaderService;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class BlogReaderServiceImpl extends CRUDImpl<BlogReader, Integer> implements IBlogReaderService {

    @Autowired
    private IBlogReaderRepository repo;

    @Override
    protected IGenericRepository<BlogReader, Integer> getRepo() {
        return getRepo();
    }

    @Override
    public List<BlogReader> getBlogReaders() {
        return repo.getBlogReaders();
    }

    @Override
    public void createOrUpdateBlog(BlogReader entity) {
           repo.save(entity);

            /*Optional<Blog> blog = repo.findById(entity.getId());
            if(blog.isPresent()){
                Blog newEntity = blog.get();
                newEntity.setTitle(entity.getTitle());
                newEntity.setDescription(entity.getDescription());
                newEntity = repo.save(newEntity);
            } else {
                entity = repo.save(entity);
            }
        }*/
    }

    @Override
    public void deleteBlogReader(String br) {
        System.out.println(br);
        List<String> list = Arrays.asList(br.split(","));
        String blog = list.get(1).trim();
        String reader = list.get(4).trim();
        int idBlog = Integer.parseInt(blog.substring(blog.indexOf("id=")+3));
        int idReader = Integer.parseInt(reader.substring(reader.indexOf("id=")+3));
        repo.deleteTransactional(idBlog, idReader);
    }
}