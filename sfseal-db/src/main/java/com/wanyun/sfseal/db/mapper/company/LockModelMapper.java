package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.LockModel;import com.wanyun.sfseal.db.model.company.LockSearchModel;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface LockModelMapper {
    int deleteByPrimaryKey(String rfid);

    int insert(LockModel record);

    int insertSelective(LockModel record);

    LockModel selectByPrimaryKey(String rfid);

    int updateByPrimaryKeySelective(LockModel record);

    int updateByPrimaryKey(LockModel record);

    List<LockModel> getLocksByCondition(LockSearchModel condition);

    /**
     * Add in batch
     *
     * @param records the records
     * @return int
     */
    int addInBatch(@Param("records") List<LockModel> records);

    int updateLockStatus(@Param("rfidList") List<String> rfidList, @Param("statusCode") Long statusCode, @Param("updateUser") String updateUser);

    int deleteLocks(@Param("rfidList") List<String> rfidList);

    int updateLockStatusByCompanyId(@Param("companyId") Long companyId, @Param("dispenseRecordId") Long dispenseRecordId, @Param("statusCode") Long statusCode, @Param("updateUser") String updateUser);

    List<String> getPurchLockRfids(@Param("purchCount") int purchCount);

    List<LockModel> getLocksByWorkOrderId(Long workOrderId);

    List<LockModel> getLocksForWorkOrder(LockSearchModel searchModel);

    List<LockModel> getLocksByRfids(@Param("rfidList") List<String> rfidList);

    LockModel getLockSealInfoByBarCode(String barcode);
}
