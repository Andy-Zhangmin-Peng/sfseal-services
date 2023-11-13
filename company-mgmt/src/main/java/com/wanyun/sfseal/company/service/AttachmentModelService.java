package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.db.model.company.AttachmentModel;

public interface AttachmentModelService {


    int deleteByPrimaryKey(Long attachmentId);

    int insert(AttachmentModel record);

    int insertSelective(AttachmentModel record);

    AttachmentModel selectByPrimaryKey(Long attachmentId);

    int updateByPrimaryKeySelective(AttachmentModel record);

    int updateByPrimaryKey(AttachmentModel record);

}

