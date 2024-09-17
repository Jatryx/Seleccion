package com.project.Soltel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Soltel.models.RecruitingModel;

@Repository
public interface IRecruitingRepository extends JpaRepository<RecruitingModel, Integer> {

    @Query("SELECT a from RecruitingModel a WHERE a.activo = false")
    List<RecruitingModel> findRecruitingActivos();

    @Query("SELECT a from RecruitingModel a WHERE a.activo = true")
    List<RecruitingModel> findRecruitingInactivos();

}
