package com.wanyun.sfseal.company.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wanyun.sfseal.db.model.company.AttachmentModel;
import com.wanyun.sfseal.db.mapper.company.AttachmentModelMapper;
import com.wanyun.sfseal.company.service.AttachmentModelService;

@Service
public class AttachmentModelServiceImpl implements AttachmentModelService {

    @Resource
    private AttachmentModelMapper attachmentModelMapper;

    @Override
    public int deleteByPrimaryKey(Long attachmentId) {
        return attachmentModelMapper.deleteByPrimaryKey(attachmentId);
    }

    @Override
    public int insert(AttachmentModel record) {
        return attachmentModelMapper.insert(record);
    }

    @Override
    public int insertSelective(AttachmentModel record) {
        return attachmentModelMapper.insertSelective(record);
    }

    @Override
    public AttachmentModel selectByPrimaryKey(Long attachmentId) {
        return attachmentModelMapper.selectByPrimaryKey(attachmentId);
    }

    @Override
    public int updateByPrimaryKeySelective(AttachmentModel record) {
        return attachmentModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AttachmentModel record) {
        return attachmentModelMapper.updateByPrimaryKey(record);
    }

}

