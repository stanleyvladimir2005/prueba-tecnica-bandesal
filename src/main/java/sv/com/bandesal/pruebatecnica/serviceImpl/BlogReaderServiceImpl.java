package sv.com.bandesal.pruebatecnica.serviceImpl;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.com.bandesal.pruebatecnica.model.BlogReader;
import sv.com.bandesal.pruebatecnica.repository.IBlogReaderRepository;
import sv.com.bandesal.pruebatecnica.repository.IGenericRepository;
import sv.com.bandesal.pruebatecnica.service.IBlogReaderService;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogReaderServiceImpl extends CRUDImpl<BlogReader, Integer> implements IBlogReaderService {

    private final IBlogReaderRepository repo;

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
    }

    @Override
    public void deleteBlogReader(String br) {
        List<String> list = Arrays.asList(br.split(","));
        var blog = list.get(1).trim();
        var reader = list.get(4).trim();
        var idBlog = Integer.parseInt(blog.substring(blog.indexOf("id=")+3));
        var idReader = Integer.parseInt(reader.substring(reader.indexOf("id=")+3));
        repo.deleteTransactional(idBlog, idReader);
    }
}