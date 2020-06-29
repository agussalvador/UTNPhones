package edu.utn.utnphones.service;

import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.dto.TariffRequestDto;
import edu.utn.utnphones.exceptions.CityNotFoundException;
import edu.utn.utnphones.exceptions.TarriffAlreadyExistsException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.repository.CityDao;
import edu.utn.utnphones.repository.TariffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService {

    private static TariffDao tariffDao;
    private final CityDao cityDao;

    @Autowired
    public TariffService(TariffDao tariffDao, CityDao cityDao) {
        this.tariffDao = tariffDao;
        this.cityDao = cityDao;
    }

    /*CREATE*/
    public Tariff createTariff(TariffRequestDto tariff) throws CityNotFoundException, ValidationException, TarriffAlreadyExistsException {

        if(tariff.getPrice()==null || tariff.getCostPrice()==null ){
            throw new ValidationException("Error - cost or price, cannot be null");
        }else if(tariff.getCostPrice() <= tariff.getPrice()) {
            City cityOrigin = cityDao.findById(tariff.getCityOriginId()).orElseThrow(() -> new CityNotFoundException());
            City cityDestination = cityDao.findById(tariff.getCityDestinationId()).orElseThrow(() -> new CityNotFoundException());
            try{
                Long idTariff = tariffDao.create(cityOrigin.getCityId(), cityDestination.getCityId(), tariff.getCostPrice(), tariff.getPrice());
                return tariffDao.getById(idTariff);
            }catch (DataAccessException ex){
                throw new TarriffAlreadyExistsException();
            }
        }else{
            throw new ValidationException("Error - the price must be a most value than cost");
        }
    }

    /*READ*/
    public List<Tariff> readTariff(){
        return tariffDao.getAllTariffs();
    }

//    /*UPDATE*/
//    public void updateTariff(TariffRequestDto tariff) throws JpaSystemException{
//        dao.update(tariff.getCityOrigin().getCityId(), tariff.getCityDestination().getCityId(),
//                tariff.getCostPrice(),tariff.getPrice());
//    }
//
//    /*DELETE*/
//    public void deleteTariff(Integer idCityOrigin, Integer idCityDestination)throws JpaSystemException{
//        dao.delete(idCityOrigin,idCityDestination);
//    }


}
