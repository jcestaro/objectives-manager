package com.github.jcestaro.objectivesmanager.model.repository;

import com.github.jcestaro.objectivesmanager.model.entity.Objective;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Integer> {

}
