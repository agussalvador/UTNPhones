package edu.utn.utnphones.service;

import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.dto.TariffRequestDto;
import edu.utn.utnphones.exceptions.CityNotFoundException;
import edu.utn.utnphones.exceptions.TarriffAlreadyExistsException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.projection.TariffView;
import edu.utn.utnphones.repository.CityDao;
import edu.utn.utnphones.repository.TariffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TariffService {

    private static TariffDao dao;
    private final CityDao cityDao;

    @Autowired
    public TariffService(TariffDao dao, CityDao cityDao) {
        this.dao = dao;
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
                Long idTariff = dao.create(cityOrigin.getCityId(), cityDestination.getCityId(), tariff.getCostPrice(), tariff.getPrice());
                return dao.getById(idTariff);
            }catch (DataAccessException ex){
                throw new TarriffAlreadyExistsException(ex.getMessage());
            }
        }else{
            throw new ValidationException("Error - the price must be a most value than cost");
        }
    }

    /*READ*/
    public List<Tariff> readTariff()throws JpaSystemException{
        return dao.getAllTariffs();
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
