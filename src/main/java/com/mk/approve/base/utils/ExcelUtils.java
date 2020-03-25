package com.mk.approve.base.utils;

import com.mk.approve.entity.SteelDamageInfo;
import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * Description:
 * version:1.0.0
 * Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 23:10 2020/3/17
 */
@Service
public class ExcelUtils {

    @Value("${path.filepath}")
    private String filepath;
        private static final String EXCEL_XLS = "xls";
        private static final String EXCEL_XLSX = "xlsx";


        public static Workbook writeExcel(SteelDamageInfo steelDamageInfo){
            //OutputStream out = null;
            Workbook workBook = new HSSFWorkbook();
            try {

                // 读取Excel文档
               // File finalXlsxFile = new File(finalXlsxPath);
                ;//getWorkbok(finalXlsxFile);
                // sheet 对应一个工作页
                Sheet sheet = workBook.createSheet();
                /**
                 * 删除原有数据，除了属性列
                 */
                //int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
                //System.out.println("原始数据总行数，除属性列：" + rowNumber);
                //for (int i = 1; i <= rowNumber; i++) {
                //    Row row = sheet.getRow(i);
                //    sheet.removeRow(row);
                //}
                /**
                 * 往Excel中写新数据
                 */
                Row row0 = sheet.createRow(0);

                Row row2 = sheet.createRow(2);
                Cell c11 = row2.createCell(0);
                c11.setCellValue("发现时间");
                Cell c12 = row2.createCell(1);
                c12.setCellValue("工区别");
                Cell c13 = row2.createCell(2);
                c13.setCellValue("位置");
                Cell c17 = row2.createCell(6);
                c17.setCellValue("曲直线");
                Cell c18 = row2.createCell(7);
                c18.setCellValue("钢轨");
                Cell c111 = row2.createCell(10);
                c111.setCellValue("仪器号");
                Cell c112 = row2.createCell(11);
                c112.setCellValue("显示波");
                Cell c113 = row2.createCell(12);
                c113.setCellValue("折损原因");
                Cell c114 = row2.createCell(13);
                c114.setCellValue("备注");

                //第二行
                Row row3 = sheet.createRow(3);
                Cell c23 = row3.createCell(2);
                c23.setCellValue("线名");
                Cell c24 = row3.createCell(3);
                c24.setCellValue("里程");
                Cell c25 = row3.createCell(4);
                c25.setCellValue("股别");
                Cell c26= row3.createCell(5);
                c26.setCellValue("铁号");
                Cell c28 = row3.createCell(7);
                c28.setCellValue("出厂名");
                Cell c29= row3.createCell(8);
                c29.setCellValue("长度及类型");
                Cell c210 = row3.createCell(9);
                c210.setCellValue("日期");

                //第三行 数据
                Row row4 = sheet.createRow(4);
                row4.createCell(0).setCellValue(steelDamageInfo.getDiscoveryTime());
                row4.createCell(1).setCellValue(steelDamageInfo.getJobDifferent());
                row4.createCell(2).setCellValue(steelDamageInfo.getLineName());
                row4.createCell(3).setCellValue(steelDamageInfo.getMileage());
                row4.createCell(4).setCellValue(steelDamageInfo.getGubie());
                row4.createCell(5).setCellValue(steelDamageInfo.getIronNumber());
                row4.createCell(6).setCellValue(steelDamageInfo.getCurvedLine());
                row4.createCell(7).setCellValue(steelDamageInfo.getFactoryName());
                row4.createCell(8).setCellValue(steelDamageInfo.getSteelLength());
                row4.createCell(9).setCellValue(steelDamageInfo.getSteelFlowDate());
                row4.createCell(10).setCellValue(steelDamageInfo.getInstrumentNumber());
                row4.createCell(11).setCellValue(steelDamageInfo.getDisplayWave());
                row4.createCell(12).setCellValue(steelDamageInfo.getDamageReason());
                row4.createCell(13).setCellValue(steelDamageInfo.getRemark());

                //合并单元格 ============
                //第一列 竖着合并的
                Integer [] craArrs = {0,1,6,10,11,12,13};
                //目前一共14列
                for (int i = 0; i < 14; i++) {
                    if(Arrays.asList(craArrs).contains(i)){
                        CellRangeAddress cra = new CellRangeAddress(0, 1, i, i);
                        sheet.addMergedRegion(cra );
                    }
                }
                //第一行 位置- 2-5单元格合并
                CellRangeAddress cra1 = new CellRangeAddress(0, 0, 2, 5);
                sheet.addMergedRegion(cra1 );
                //第一行 钢轨- 7-9单元格合并
                CellRangeAddress cra2 = new CellRangeAddress(0, 0, 7, 9);
                sheet.addMergedRegion(cra2 );



                //out =  new FileOutputStream("D://workbook.xls");
                //workBook.write(out);

            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                //try {
                //    if(out != null){
                //        out.flush();
                //        out.close();
                //    }
                //} catch (IOException e) {
                //    e.printStackTrace();
                //}
            }
            System.out.println("数据导出成功");
            return workBook;
        }

        public String addPictureToExcel(Workbook workbook,HSSFSheet hssfSheet,String ImgName,String hand1Name,String hand2Name,Integer steelId){
            String finalFilePath = filepath + "file/excel/"+ steelId+".xls";
            FileOutputStream fileOut = null;


// 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray

            try {

                ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                ByteArrayOutputStream byteArrayOut1 = new ByteArrayOutputStream();
                ByteArrayOutputStream byteArrayOut2 = new ByteArrayOutputStream();

                BufferedImage bufferImg = ImageIO.read(new File(filepath+ "file/img/"+ImgName));
                BufferedImage bufferImg1 = ImageIO.read(new File(filepath+"file/img/"+hand1Name));
                BufferedImage bufferImg2 = ImageIO.read(new File(filepath+"file/img/"+hand2Name));
                ImageIO.write(bufferImg, "png", byteArrayOut);
                ImageIO.write(bufferImg1, "png", byteArrayOut1);
                ImageIO.write(bufferImg2, "png", byteArrayOut2);
// 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）

                HSSFPatriarch patriarch = hssfSheet.createDrawingPatriarch();

// anchor主要用于设置图片的属性

                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 1, 1, (short) 2, 3);
                HSSFClientAnchor anchor1 = new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 4, (short) 6, 8);
                HSSFClientAnchor anchor2 = new HSSFClientAnchor(0, 0, 0, 0, (short) 9, 9, (short) 15, 18);
// 插入图片

                patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
                patriarch.createPicture(anchor1, workbook.addPicture(byteArrayOut1.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
                patriarch.createPicture(anchor2, workbook.addPicture(byteArrayOut2.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));

                File file = new File(finalFilePath);

                file.createNewFile();

                fileOut = new FileOutputStream(file);

// 写入excel文件

                workbook.write(fileOut);

                System.out.println("----Excel文件已生成------");

            } catch (Exception e) {

                e.printStackTrace();

            } finally {

                if (fileOut != null) {

                    try {

                        fileOut.close();

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                }
                return finalFilePath;
            }
        }
    public static void main(String[] args) {
        FileOutputStream fileOut = null;
        BufferedImage bufferImg = null;
        try {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            //加载图片
            //bufferImg = ImageIO.read(new File("f:file/img/e59615b76d314add9b33c89fd259a251.jpg"));
            bufferImg = ImageIO.read(new File("f:file/img/e59615b76d314add9b33c89fd259a251.jpg"));
            ImageIO.write(bufferImg, "jpg", byteArrayOut);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet1 = wb.createSheet("sheet1");
            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
            /**
             dx1 - the x coordinate within the first cell.//定义了图片在第一个cell内的偏移x坐标，既左上角所在cell的偏移x坐标，一般可设0
             dy1 - the y coordinate within the first cell.//定义了图片在第一个cell的偏移y坐标，既左上角所在cell的偏移y坐标，一般可设0
             dx2 - the x coordinate within the second cell.//定义了图片在第二个cell的偏移x坐标，既右下角所在cell的偏移x坐标，一般可设0
             dy2 - the y coordinate within the second cell.//定义了图片在第二个cell的偏移y坐标，既右下角所在cell的偏移y坐标，一般可设0
             col1 - the column (0 based) of the first cell.//第一个cell所在列，既图片左上角所在列
             row1 - the row (0 based) of the first cell.//图片左上角所在行
             col2 - the column (0 based) of the second cell.//图片右下角所在列
             row2 - the row (0 based) of the second cell.//图片右下角所在行
             */
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,(short) 2, 2, (short) 5, 8);
            //插入图片
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
            fileOut = new FileOutputStream("d:/workbook.xls");
            // 输出文件
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        /**
         * 判断Excel的版本,获取Workbook
         * @return
         * @throws IOException
         */
        public static Workbook getWorkbok(File file) throws IOException {
            Workbook wb = null;
            FileInputStream in = new FileInputStream(file);
            if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
                wb = new HSSFWorkbook();
            }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
                //wb = new WORK(in);
            }
            return wb;
        }

}
