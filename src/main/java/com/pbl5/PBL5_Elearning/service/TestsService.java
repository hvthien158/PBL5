package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Test;
import com.pbl5.PBL5_Elearning.repository.TestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestsService implements TestsServiceImp{
    @Autowired
    TestsRepository testsRepository;
    @Override
    public List<Test> findAll() {
        return testsRepository.findAll();
    }
}
