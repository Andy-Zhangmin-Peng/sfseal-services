package com.wanyun.sfseal.db.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 对象包装类，可以将查询对象包装成可支持PageHelper分页的对象
 *
 * @author WanYun
 * @param <E>
 */
@Data
@EqualsAndHashCode
@ToString
public class PageableModel<E> {

    /**
     * 页码，从1开始
     */
    private int pageNum;

    /**
     * 页面大小
     */
    private int pageSize;

    /**
     * 包含count查询
     */
    private boolean count = true;

    /**
     * 分页合理化, 配置pageNum参数合理化，比如第0页，和超过最后一页，则返会第一页和最后一页
     */
    private Boolean reasonable;

    /**
     * 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
     */
    private Boolean pageSizeZero;

    /**
     * 进行count查询的列名
     */
    private String countColumn;

    /**
     * 排序
     */
    private String orderBy;

    /**
     * 只增加排序
     */
    private boolean orderByOnly;

    /**
     * 过滤条件
     */
    private E condition;
}
