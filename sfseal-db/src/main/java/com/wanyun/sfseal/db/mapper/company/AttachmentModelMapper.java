package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.AttachmentModel;

import java.util.List;

public interface AttachmentModelMapper {
    int deleteByPrimaryKey(Long attachmentId);

    int insert(AttachmentModel record);

    int insertSelective(AttachmentModel record);

    AttachmentModel selectByPrimaryKey(Long attachmentId);

    int updateByPrimaryKeySelective(AttachmentModel record);

    int updateByPrimaryKey(AttachmentModel record);

    List<AttachmentModel>  selectAttachmentsByRfid(String rfid);

    List<AttachmentModel>  selectAttachmentsByExceptionId(Long exceptionId);

}
