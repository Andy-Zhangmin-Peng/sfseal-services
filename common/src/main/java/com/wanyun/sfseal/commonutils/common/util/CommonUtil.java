/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.common.util;

import com.wanyun.sfseal.commonutils.common.contants.CommonConstants;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * All common methods used
 *
 * @author WanYun
 */
public class CommonUtil {

    protected static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * Check if String is not empty
     *
     * @param str         the sting
     * @param ignoreSpace teh ignore space
     * @return boolean
     */
    public static boolean isStringEmpty(String str, boolean ignoreSpace) {
        if (str == null) {
            return true;
        }
        if (ignoreSpace) {
            str = str.trim();
        }
        return "".equals(str);
    }

    /**
     * check if a list is empty or not
     *
     * @param list the list
     * @return boolean
     */
    public static boolean isListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isMapEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean compareObjects(Object obj1, Object obj2, String... format) {
        // two null objects, return true
        if (obj1 == null && obj2 == null) {
            return true;
        }
        // only one object is null, return false
        if (obj1 == null) {
            return false;
        }
        if (obj2 == null) {
            return false;
        }
        // String objects
        if (obj1 instanceof String && obj2 instanceof String) {
            return obj1.equals(obj2);
        }
        // Timestamp objects
        if (obj1 instanceof Timestamp && obj2 instanceof Timestamp) {
            SimpleDateFormat sf;
            if (format != null && format.length != 0) {
                sf = new SimpleDateFormat(format[0]);
            } else {
                sf = new SimpleDateFormat(CommonConstants.DATE_FORMAT_YYYY_MM_DD_SLASH);
            }
            String strFromDate = sf.format(new Date(((Timestamp) obj1).getTime()));
            String strToDate = sf.format(new Date(((Timestamp) obj2).getTime()));
            return strFromDate.equals(strToDate);
        }
        // Date objects
        if (!(obj1 instanceof Timestamp) && !(obj2 instanceof Timestamp) && obj1 instanceof Date && obj2 instanceof Date) {
            return ((Date) obj1).compareTo((Date) obj2) == 0;
        }
        // Long objects
        if (obj1 instanceof Long && obj2 instanceof Long) {
            return ((Long) obj1).longValue() == ((Long) obj2).longValue();
        }
        // Boolean objects
        if (obj1 instanceof Boolean && obj2 instanceof Boolean) {
            return ((Boolean) obj1).booleanValue() == ((Boolean) obj2).booleanValue();
        }
        // Integer objects
        if (obj1 instanceof Integer && obj2 instanceof Integer) {
            return ((Integer) obj1).intValue() == ((Integer) obj2).intValue();
        }
        // can't compare other types
        return false;
    }

    public static String getCellValueAsString(XSSFRow row, int i) {
        String strCellValue = null;
        XSSFCell cell = row.getCell(i);
        if (cell != null) {
            CellType cellType = cell.getCellType();
            if (cellType == CellType.STRING) {
                strCellValue = cell.toString();
            } else if (cellType == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    strCellValue = dateFormat.format(cell.getDateCellValue());
                    System.out.println(strCellValue);
                } else {
                    Double value = cell.getNumericCellValue();
                    long longValue = value.longValue();
                    strCellValue = String.valueOf(longValue);
                }
            } else if (cellType == CellType.BOOLEAN) {
                strCellValue = String.valueOf(cell.getBooleanCellValue());
            } else if (cellType == CellType.BLANK) {
                strCellValue = "";
            } else if (cellType == CellType.FORMULA) {
                strCellValue = cell.getRawValue();
            }
        }
        return strCellValue;
    }
}
