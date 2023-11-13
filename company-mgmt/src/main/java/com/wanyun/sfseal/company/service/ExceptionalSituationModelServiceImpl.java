/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.db.model.company.ExceptionalSituationSearchModel;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wanyun.sfseal.db.model.company.ExceptionalSituationModel;
import com.wanyun.sfseal.db.mapper.company.ExceptionalSituationModelMapper;
import com.wanyun.sfseal.company.service.ExceptionalSituationModelService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExceptionalSituationModelServiceImpl extends BaseServiceImpl implements ExceptionalSituationModelService {

    @Resource
    private ExceptionalSituationModelMapper exceptionalSituationModelMapper;

    @Override
    public int deleteByPrimaryKey(Long exceptionId) {
        return exceptionalSituationModelMapper.deleteByPrimaryKey(exceptionId);
    }

    @Override
    public int insert(ExceptionalSituationModel record) {
        return exceptionalSituationModelMapper.insert(record);
    }

    @Override
    public int insertSelective(ExceptionalSituationModel record) {
        return exceptionalSituationModelMapper.insertSelective(record);
    }

    @Override
    public ExceptionalSituationModel selectByPrimaryKey(Long exceptionId) {
        return exceptionalSituationModelMapper.selectByPrimaryKey(exceptionId);
    }

    @Override
    public int updateByPrimaryKeySelective(ExceptionalSituationModel record) {
        return exceptionalSituationModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ExceptionalSituationModel record) {
        return exceptionalSituationModelMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public List<ExceptionalSituationModel> getExceptionalSituationByCondition(ExceptionalSituationSearchModel condition) {
        condition.setCompanyId(this.getCurrentLoginInfo().getCompanyId());

        return exceptionalSituationModelMapper.getExceptionalSituationByCondition(condition);
    }

}





