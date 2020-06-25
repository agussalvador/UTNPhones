package edu.utn.utnphones.service;

import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.dto.TariffRequestDto;
import edu.utn.utnphones.projection.TariffView;
import edu.utn.utnphones.repository.TariffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
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
    public void createTariff(TariffRequestDto tariff) throws JpaSystemException {
        dao.create(tariff.getCityOrigin().getCityId(), tariff.getCityDestination().getCityId(),
                tariff.getCostPrice(),tariff.getPrice());
    }

    /*READ*/
    public List<TariffView> readTariff()throws JpaSystemException{
        return dao.getAllTariffs();
    }

    /*UPDATE*/
    public void updateTariff(TariffRequestDto tariff) throws JpaSystemException{
        dao.update(tariff.getCityOrigin().getCityId(), tariff.getCityDestination().getCityId(),
                tariff.getCostPrice(),tariff.getPrice());
    }

    /*DELETE*/
    public void deleteTariff(Integer idCityOrigin, Integer idCityDestination)throws JpaSystemException{
        dao.delete(idCityOrigin,idCityDestination);
    }
}
