package com.wanyun.sfseal.company.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wanyun.sfseal.db.mapper.company.TransportModelMapper;

import java.sql.Timestamp;
import java.util.List;
import com.wanyun.sfseal.db.model.company.TransportModel;
import com.wanyun.sfseal.company.service.TransportModelService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransportModelServiceImpl extends BaseServiceImpl implements TransportModelService{

    @Resource
    private TransportModelMapper transportModelMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return transportModelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TransportModel record) {
        return transportModelMapper.insert(record);
    }

    @Override
    public int insertSelective(TransportModel record) {
        return transportModelMapper.insertSelective(record);
    }

    @Override
    public TransportModel selectByPrimaryKey(Long id) {
        return transportModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TransportModel record) {
        return transportModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TransportModel record) {
        return transportModelMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<TransportModel> list) {
        return transportModelMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<TransportModel> list) {
        return transportModelMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<TransportModel> list) {
        return transportModelMapper.batchInsert(list);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public TransportModel createTransport(String transportId) {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentUser = this.getCurrentLoginInfo().getAccountName();
        Long companyId = this.getCurrentLoginInfo().getCompanyId();

        TransportModel transportModel = new TransportModel();
        transportModel.setTransprortId(transportId);
        transportModel.setCompanyId(companyId);
        transportModel.setCreateDate(currentTimeStamp);
        transportModel.setLastUpdateDate(currentTimeStamp);
        transportModel.setLastUpdateUser(currentUser);
        transportModelMapper.upsert(transportModel);

        return transportModel;
    }
}
