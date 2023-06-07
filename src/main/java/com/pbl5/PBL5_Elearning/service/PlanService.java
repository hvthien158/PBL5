package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Plan;
import com.pbl5.PBL5_Elearning.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService implements PlanServiceImp{
    @Autowired
    PlanRepository planRepository;
    @Override
    public Plan insertNewPlan(Plan plan) {
        return planRepository.saveAndFlush(plan);
    }

    @Override
    public void deletePlan(int id) {
        planRepository.deleteById(id);
    }
}
