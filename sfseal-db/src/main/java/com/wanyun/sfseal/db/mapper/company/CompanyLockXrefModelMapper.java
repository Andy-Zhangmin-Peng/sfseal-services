package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.CompanyLockXrefModel;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface CompanyLockXrefModelMapper {
    int deleteByPrimaryKey(Long xrefId);

    int insert(CompanyLockXrefModel record);

    int insertSelective(CompanyLockXrefModel record);

    CompanyLockXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(CompanyLockXrefModel record);

    int updateByPrimaryKey(CompanyLockXrefModel record);

    /**
     * Add in batch
     *
     * @param records the records
     * @return int
     */
    int addInBatch(@Param("records") List<CompanyLockXrefModel> records);

    int dispenseLock(@Param("companyId") Long companyId, @Param("rfidList") List<String> rfidList,
                     @Param("createUser") String createUser , @Param("dispenseRecordId") Long dispenseRecordId);

    List<String> getAllRfidsByDispenseRecordId(Long dispenseRecordId);

    int updateXrefStatusByDispensenRecordId (@Param("dispenseRecordId") Long dispenseRecordId, @Param("status") Boolean status, @Param("companyId") Long companyId,@Param("updateUser") String updateUser);
}