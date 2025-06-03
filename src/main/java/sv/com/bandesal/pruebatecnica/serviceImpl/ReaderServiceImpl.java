package sv.com.bandesal.pruebatecnica.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.com.bandesal.pruebatecnica.model.Reader;
import sv.com.bandesal.pruebatecnica.repository.IGenericRepository;
import sv.com.bandesal.pruebatecnica.repository.IReaderRepository;
import sv.com.bandesal.pruebatecnica.service.IReaderService;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl extends CRUDImpl<Reader,Integer> implements IReaderService {

    private final IReaderRepository repo;

    @Override
    protected IGenericRepository<Reader, Integer> getRepo() {
        return repo;
    }

    public void createOrUpdateReader(Reader entity) {
        if(entity.getId()  == null)
             repo.save(entity);
        else{
            var reader = repo.findById(entity.getId());
            if(reader.isPresent()){
                var readerNew = reader.get();
                readerNew.setName(entity.getName());
                repo.save(readerNew);
            } else
                repo.save(entity);
        }
    }
}