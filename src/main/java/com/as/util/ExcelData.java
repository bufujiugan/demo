package com.as.util;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Author: 灵枢
 * Date: 2018/12/05
 * Time: 17:21
 * Description:读取Excel数据
 */
public class ExcelData {
    private XSSFSheet sheet;

    /**
     * 构造函数，初始化excel数据
     *
     * @param filePath  excel路径
     * @param sheetName sheet表名
     */
    ExcelData(String filePath, String sheetName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
            sheet = sheets.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据行和列的索引获取单元格的数据
     *
     * @param row
     * @param column
     * @return
     */
    public String getExcelDateByIndex(int row, int column) {
        XSSFRow row1 = sheet.getRow(row);
        String cell = row1.getCell(column).toString();
        return cell;
    }

    /**
     * 根据某一列值为“******”的这一行，来获取该行第x列的值
     *
     * @param caseName
     * @param currentColumn 当前单元格列的索引
     * @param targetColumn  目标单元格列的索引
     * @return
     */
    public String getCellByCaseName(String caseName, int currentColumn, int targetColumn) {
        String operateSteps = "";
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(currentColumn).toString();
            if (cell.equals(caseName)) {
                operateSteps = row.getCell(targetColumn).toString();
                break;
            }
        }
        return operateSteps;
    }

    //打印excel数据
    public void readExcelData() {
//        int[] tool = new int[]{0, 2, 9, 8, 13, 14, 18, 17, 16, 12, 24, 25, 26, 27, 28, 29, 19, 20, 21, 0, 23, 30, 1};
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        String sql = "INSERT INTO t_contract_checkout_fee_item (`id`, `checkout_id`, `fee_id`, `room_name`, `room_id`, `fee_item_id`, `fee_item_name`, `fee_rate`, `fee_amount`, `no_fee_amount`, `amount`, `belong_type`, `remark`, `descrite`, " +
                "`created_by`, `creation_date`, `last_updated_by`, `last_update_date`, `void_flag`, `fee_item_att_id`, `is_pre_pay`, `bill_id`, `relief_status`, `is_reduction`, " +
                "`reduction_type`, `modify_group_id`, `workflow_status`, `file_id`, `checkout_no`, `modify_parent_id`, `finance_period`) VALUES \n";
        String strs = "";
        for (int i = 0; i < rows; i++) {
            String str = "('%s', '%s', NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', 0," +
                    " '%s', '%s', '%s', '%s', '%s', '%s', 0, NULL, 1, '%s', '%s', '%s', '%s', '%s', '%s', UUID(), '2021-07-01 00:00:00'),\n";
            //获取列数
            XSSFRow row = sheet.getRow(i);
            String str0 = row.getCell(0).toString();
            String str2 = row.getCell(2).toString();
            String str9 = row.getCell(9).toString();
            String str8 = row.getCell(8).toString();
            String str13 = row.getCell(13).toString();
            String str14 = row.getCell(14).toString();
            String str18 = row.getCell(18).toString();
            double str15 = row.getCell(16).getNumericCellValue() - row.getCell(17).getNumericCellValue();
            String str17 = row.getCell(17).toString();
            String str16 = row.getCell(16).toString();
            String str12 = row.getCell(12).toString();
            String str24 = row.getCell(24).toString();

            String str25 = row.getCell(25).toString();
            String str26 = row.getCell(26).toString();
            Date javaDate = HSSFDateUtil.getJavaDate(row.getCell(26).getNumericCellValue());
            try {
                str26 = DateUtil.format(javaDate, "yyyy-MM-dd HH:mm:ss");
            } catch (Exception e) {
                e.printStackTrace();
            }
            String str27 = ObjectUtil.isNull(row.getCell(27)) ? "tool" : row.getCell(27).toString();
            Date last = HSSFDateUtil.getJavaDate(row.getCell(28).getNumericCellValue());
            String str28 = DateUtil.format(last, "yyyy-MM-dd HH:mm:ss");
            Double numericCellValue = row.getCell(29).getNumericCellValue();
            int str29 = numericCellValue.intValue();
            Object str19;
            try {
                Double numericCellValue2 = row.getCell(19).getNumericCellValue();
                str19 = numericCellValue2.intValue();
            } catch (Exception e) {
                str19 = row.getCell(19).toString();
            }

            Double numericCellValue1 = row.getCell(20).getNumericCellValue();
            int str20 = numericCellValue1.intValue();
            String str21 = row.getCell(21).toString();
            String str22 = row.getCell(0).toString();
            String str23 = ObjectUtil.isNull(row.getCell(23)) ? "tool" : row.getCell(23).toString();
            String str30 = ObjectUtil.isNull(row.getCell(30)) ? "tool" : row.getCell(30).toString();
            String str1 = row.getCell(1).toString();
            strs += String.format(str, str0, str2, str9, str8, str13, str14, str18, str15, str17, str16, str12, str24, str25, str26, str27, str28, str29, str19, str20, str21, str22, str23, str30, str1);
//            int columns = row.getPhysicalNumberOfCells();
//            for (int j = 0; j < columns; j++) {
//                String cell = row.getCell(j).toString();
//                System.out.println(cell);
//            }
        }
//        strs = strs.substring(0, strs.length() - 1) + ";";
        System.out.println(sql + strs);
    }

    //测试方法
    public static void main(String[] args) throws ParseException {
//        ExcelData sheet1 = new ExcelData("E:/1.xlsx", "Sheet1");
//        sheet1.readExcelData();

//        for (int i = 0; i < 200; i++)
//            System.out.println("INSERT INTO `db`.`a` (`num`) VALUES (" + i + ");");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2021-07-15");
        Date d2 = sdf.parse("2021-08-15");

        System.out.println(DateUtil.between(d1, d2, DateUnit.DAY, false));

//        List<String> list = new ArrayList<>();
//        list.add("123");
//        list.add(null);
//        list.removeAll(Collections.singleton(null));
//        System.out.println(list.toString());

    }
}


