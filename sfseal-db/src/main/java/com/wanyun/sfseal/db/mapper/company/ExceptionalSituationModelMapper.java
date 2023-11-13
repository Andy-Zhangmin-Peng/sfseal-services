package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.ExceptionalSituationModel;import com.wanyun.sfseal.db.model.company.ExceptionalSituationSearchModel;import java.util.List;

public interface ExceptionalSituationModelMapper {
    int deleteByPrimaryKey(Long exceptionId);

    int insert(ExceptionalSituationModel record);

    int insertSelective(ExceptionalSituationModel record);

    ExceptionalSituationModel selectByPrimaryKey(Long exceptionId);

    int updateByPrimaryKeySelective(ExceptionalSituationModel record);

    int updateByPrimaryKey(ExceptionalSituationModel record);

    List<ExceptionalSituationModel> getExceptionalSituationByCondition(ExceptionalSituationSearchModel condition);
}