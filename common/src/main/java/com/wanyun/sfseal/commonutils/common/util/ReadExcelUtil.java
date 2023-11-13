/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.common.util;

import com.wanyun.sfseal.commonutils.common.contants.CommonConstants;
import com.wanyun.sfseal.commonutils.common.contants.CommonErrorMessage;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.commonutils.model.ImportConfigureModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读excel工具类
 *
 * @author WanYun
 */
public class ReadExcelUtil {
    protected static final Logger logger = LoggerFactory.getLogger(ReadExcelUtil.class);

    public static <T> List<T> readFile(MultipartFile file,
                                       Class<T> claz,
                                       String sheetName,
                                       List<ImportConfigureModel> configureList) throws WanYunBusinessException {
        // 检查上传文件状态
        if (file == null) {
            throw new WanYunBusinessException(CommonErrorMessage.ExcelErrorMessage.NO_FILE_ERROR);
        } else {
            String fileName = file.getOriginalFilename();
            if (!CommonUtil.isStringEmpty(fileName, true)) {
                String fileFormat = fileName.substring(fileName.lastIndexOf("."));
                if (!CommonConstants.EXCEL_FORMAT.equals(fileFormat)) {
                    throw new WanYunBusinessException(CommonErrorMessage.ExcelErrorMessage.FILE_TYPE_ERROR);
                }
            } else {
                throw new WanYunBusinessException(CommonErrorMessage.ExcelErrorMessage.NO_FILE_ERROR);
            }
        }

        // 检查上传文件配置
        if (CommonUtil.isListEmpty(configureList) || claz == null) {
            throw new WanYunBusinessException(CommonErrorMessage.ExcelErrorMessage.FILE_CONFIGURE_ERROR);
        }

        Map<String, ImportConfigureModel> configureMap = new HashMap<>(16);
        configureList.forEach(configure -> {
            configureMap.put(configure.getDisplayName(), configure);
        });

        // 检查sheet
        XSSFSheet sheet;
        try {
            sheet = (new XSSFWorkbook(file.getInputStream())).getSheet(sheetName);
        } catch (IOException e) {
            throw new WanYunBusinessException(e, CommonErrorMessage.ExcelErrorMessage.NO_SHEET_ERROR);
        }
        if (sheet == null) {
            throw new WanYunBusinessException(CommonErrorMessage.ExcelErrorMessage.NO_SHEET_ERROR);
        }

        List<T> list = new ArrayList<>(sheet.getLastRowNum());

        // 获取titles
        XSSFRow titles = sheet.getRow(CommonConstants.TITLE_ROW_NUMBER);
        int lastCellNum = titles.getLastCellNum();

        ReflectUtil<T> reflectUtil = new ReflectUtil<>();
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = sheet.getRow(rowNum);

            T object;
            try {
                object = claz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new WanYunBusinessException(e, CommonErrorMessage.ExcelErrorMessage.FILE_CONFIGURE_ERROR);
            }

            for (int cellNum = 0; cellNum < lastCellNum; cellNum++) {
                ImportConfigureModel configure = configureMap.get(CommonUtil.getCellValueAsString(titles, cellNum));

                Object cellValue = formatCellValue(CommonUtil.getCellValueAsString(row, cellNum), configure);
                if (cellValue != null && object != null) {
                    try {
                        reflectUtil.invokeSetter(object, configure.getFieldName(), cellValue);
                    } catch (NoSuchFieldException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                        logger.warn(e.getMessage());
                    }
                }
            }
            list.add(object);
        }

        return list;
    }

    /**
     * Format cell value
     *
     * @param value     the value
     * @param configure the configure
     * @return object
     */
    private static Object formatCellValue(String value, ImportConfigureModel configure) {
        if (configure.isRequired() && CommonUtil.isStringEmpty(value, true)) {
            return null;
        }

        return value;
    }
}
