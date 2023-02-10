package sv.com.bandesal.pruebatecnica.service;

import sv.com.bandesal.pruebatecnica.model.Reader;

public interface IReaderService extends ICRUD<Reader, Integer> {
    void createOrUpdateReader(Reader reader);
}