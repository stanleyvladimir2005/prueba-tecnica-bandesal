package sv.com.bandesal.pruebatecnica.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.com.bandesal.pruebatecnica.model.Reader;
import sv.com.bandesal.pruebatecnica.repository.IGenericRepository;
import sv.com.bandesal.pruebatecnica.repository.IReaderRepository;
import sv.com.bandesal.pruebatecnica.service.IReaderService;
import java.util.Optional;

@Service
public class ReaderServiceImpl extends CRUDImpl<Reader,Integer> implements IReaderService {

    @Autowired
    private IReaderRepository repo;

    @Override
    protected IGenericRepository<Reader, Integer> getRepo() {
        return repo;
    }

    public void createOrUpdateReader(Reader entity) {
        if(entity.getId()  == null){
            entity = repo.save(entity);
        }else{
            Optional<Reader> reader = repo.findById(entity.getId());
            if(reader.isPresent()){
                Reader readerNew = reader.get();
                readerNew.setName(entity.getName());
                readerNew = repo.save(readerNew);
            } else {
                entity = repo.save(entity);
            }
        }
    }
}