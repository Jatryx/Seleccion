package com.project.Soltel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Soltel.models.RecruitingModel;
import com.project.Soltel.repositories.IRecruitingRepository;

@Service
public class RecruitingService {

    @Autowired
    private IRecruitingRepository recruitingRepository;

    public List<RecruitingModel> consultarTodosRecruiting(){
        return recruitingRepository.findAll();
    }

    public List<RecruitingModel> consultarRecruitingActivos(){
        return recruitingRepository.findRecruitingActivos();
    }

    public List<RecruitingModel> consultarRecruitingInactivos(){
        return recruitingRepository.findRecruitingInactivos();
    }

    public RecruitingModel guardarRecruiting(RecruitingModel recruiting){
        return recruitingRepository.save(recruiting);
    }

    public RecruitingModel actualizarRecruiting(RecruitingModel recruiting){
        return recruitingRepository.save(recruiting);
    }

}
