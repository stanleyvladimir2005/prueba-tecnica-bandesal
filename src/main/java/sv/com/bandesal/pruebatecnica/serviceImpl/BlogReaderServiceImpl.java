package sv.com.bandesal.pruebatecnica.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.com.bandesal.pruebatecnica.model.BlogReader;
import sv.com.bandesal.pruebatecnica.repository.IBlogReaderRepository;
import sv.com.bandesal.pruebatecnica.repository.IGenericRepository;
import sv.com.bandesal.pruebatecnica.service.IBlogReaderService;

import java.util.List;

@Service
public class BlogReaderServiceImpl extends CRUDImpl<BlogReader, Integer> implements IBlogReaderService {

    @Autowired
    private IBlogReaderRepository repo;

    @Autowired
    private IBlogReaderRepository repoBlog;

    @Autowired
    private IBlogReaderRepository repoReader;

    @Override
    protected IGenericRepository<BlogReader, Integer> getRepo() {
        return getRepo();
    }

    @Override
    public void saveTransactional(BlogReader br) {
        if (repoBlog.findById(br.getBlog().getId()).isPresent()){
            if (repoReader.findById(br.getReader().getId()).isPresent())
                repo.register(br.getBlog().getId(), br.getReader().getId());
        }
    }

    @Override
    public List<BlogReader> getBlogReaders() {
        return repo.getBlogReaders();
    }
}