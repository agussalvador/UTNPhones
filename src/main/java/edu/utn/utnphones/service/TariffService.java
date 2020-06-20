package edu.utn.utnphones.service;

import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.repository.TariffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService {

    private static TariffDao dao;

    @Autowired
    public TariffService(TariffDao dao) {
        this.dao = dao;
    }

    /*CREATE*/
    public void createTariff(Tariff tariff) {
        dao.create(tariff.getCityOrigin().getCityId(), tariff.getCityDestination().getCityId(),
                tariff.getCostPrice(),tariff.getPrice());
    }

    /*READ*/
    public List<Tariff> readTariff(){
        return dao.findAll();
    }

    /*UPDATE*/
    public void updateTariff(Tariff tariff) {
        dao.update(tariff.getCityOrigin().getCityId(), tariff.getCityDestination().getCityId(),
                tariff.getCostPrice(),tariff.getPrice());
    }

    /*DELETE*/
    public void deleteTariff(Integer idCityOrigin, Integer idCityDestination){
        dao.delete(idCityOrigin,idCityDestination);
    }
}
