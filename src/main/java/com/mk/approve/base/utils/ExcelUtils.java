package com.mk.approve.base.utils;

import com.aspose.cells.ImageOrPrintOptions;
import com.aspose.cells.SheetRender;
import com.mk.approve.entity.SteelDamageInfo;
import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
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

    public static void main(String[] args) {
        writeExcel(new SteelDamageInfo());
    }
        public static Workbook writeExcel(SteelDamageInfo steelDamageInfo){
            OutputStream out = null;
            HSSFWorkbook workBook = new HSSFWorkbook();
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
                //表头 第一行
                Row row0 = sheet.createRow(0);
                Cell c01 = row0.createCell(0);   c01.setCellValue("重伤钢轨存根");
                HSSFCellStyle style01 = workBook.createCellStyle();
                style01.setAlignment(HorizontalAlignment.CENTER);
                style01.setVerticalAlignment(VerticalAlignment.CENTER);
                HSSFFont hssfFont01 = workBook.createFont();
                hssfFont01.setFontHeightInPoints((short) 12);
                hssfFont01.setBold(true); //加粗
                hssfFont01.setColor(Font.COLOR_NORMAL);//黑色
                hssfFont01.setFontName("黑体");
                style01.setFont(hssfFont01);
                c01.setCellStyle(style01);
                //第二行 日期 编号
                Row row1 = sheet.createRow(1);
                Cell c11 = row1.createCell(0);  c11.setCellValue("日期："+steelDamageInfo.getSteelFlowDate()+ "  编号：" + steelDamageInfo.getSteelId());
                HSSFCellStyle style1 = workBook.createCellStyle();
                style1.setAlignment(HorizontalAlignment.RIGHT);
                c11.setCellStyle(style1);
                //第三行
                Row row2 = sheet.createRow(2);
                row2.createCell(0).setCellValue("发现时间");
                row2.createCell(1).setCellValue("工区别");
                row2.createCell(2).setCellValue("位置");
                row2.createCell(3);
                row2.createCell(4);
                row2.createCell(5);
                row2.createCell(6).setCellValue("曲直线");
                row2.createCell(7).setCellValue("钢轨");
                row2.createCell(8);
                row2.createCell(9);
                row2.createCell(10).setCellValue("仪器号");
                row2.createCell(11).setCellValue("显示波");
                row2.createCell(12).setCellValue("折损原因");
                row2.createCell(13).setCellValue("备注");

                //第二行
                Row row3 = sheet.createRow(3);
                row3.createCell(0);
                row3.createCell(1);
                row3.createCell(2).setCellValue("线名");
                row3.createCell(3).setCellValue("里程");
                row3.createCell(4).setCellValue("股别");
                row3.createCell(5).setCellValue("铁号");
                row3.createCell(7).setCellValue("出厂名");
                row3.createCell(8).setCellValue("长度及类型");
                row3.createCell(9).setCellValue("日期");
                row3.createCell(10);
                row3.createCell(11);
                row3.createCell(12);
                row3.createCell(13);

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
                row4.createCell(9).setCellValue(steelDamageInfo.getSteelDate());
                row4.createCell(10).setCellValue(steelDamageInfo.getInstrumentNumber());
                row4.createCell(11).setCellValue(steelDamageInfo.getDisplayWave());
                row4.createCell(12).setCellValue(steelDamageInfo.getDamageReason());
                row4.createCell(13).setCellValue(steelDamageInfo.getRemark());

                //第四行
                Row row5 = sheet.createRow(5);
                row5.createCell(0).setCellValue("伤损位置");
                row5.createCell(1).setCellValue("伤损现状");
                row5.createCell(2).setCellValue("示意图");
                row5.createCell(10).setCellValue("处理意见");
                row5.createCell(11);
                row5.createCell(13);
                row5.createCell(12).setCellValue(steelDamageInfo.getHandleSuggestion());
                // 必须创建空单元格 用于显示 边框
                Row row6 = sheet.createRow(6);
                row6.createCell(0).setCellValue(steelDamageInfo.getDamageLocation());
                row6.createCell(1).setCellValue(steelDamageInfo.getDamageSituation());
                row6.createCell(2);
                row6.createCell(10).setCellValue("被通知人");
                row6.createCell(11);
                row6.createCell(12).setCellValue("调度");
                row6.createCell(13);
                //
                Row row7 = sheet.createRow(7);
                row7.createCell(0);
                row7.createCell(1);
                row7.createCell(2);
                row7.createCell(10);
                row7.createCell(11);
                row7.createCell(12);
                row7.createCell(13);
                //
                Row row8 = sheet.createRow(8);
                row8.createCell(0);
                row8.createCell(1);
                row8.createCell(2);
                row8.createCell(10).setCellValue("发现人");
                row8.createCell(12).setCellValue("执机");
                row8.createCell(13).setCellValue(steelDamageInfo.getZhiji());
                //
                Row row9 = sheet.createRow(9);
                row9.createCell(0);
                row9.createCell(1);
                row9.createCell(2);
                row9.createCell(10);
                row9.createCell(12).setCellValue("助手");
                row9.createCell(13).setCellValue(steelDamageInfo.getZhushou());
                //
                Row row10 = sheet.createRow(10);
                row10.createCell(0);
                row10.createCell(1);
                row10.createCell(2);
                row10.createCell(3);
                row10.createCell(4);
                row10.createCell(5);
                row10.createCell(6);
                row10.createCell(7);
                row10.createCell(8);
                row10.createCell(9);
                row10.createCell(10);
                row10.createCell(11);
                row10.createCell(12).setCellValue("领班");
                row10.createCell(13).setCellValue(steelDamageInfo.getLingban());
                //合并单元格 ============
                // 竖着合并的=====================
                Integer [] craArrs = {0,1,6,10,11,12,13};
                //目前一共14列
                for (int i = 0; i < 14; i++) {
                    if(Arrays.asList(craArrs).contains(i)){
                        CellRangeAddress cra = new CellRangeAddress(2, 3, i, i);
                        sheet.addMergedRegion(cra );
                    }
                }
                //伤损位置 伤损现状
                sheet.addMergedRegion(new CellRangeAddress(6,10,0,0));
                sheet.addMergedRegion(new CellRangeAddress(6,10,1,1));
                //示意图
                sheet.addMergedRegion(new CellRangeAddress(5,10,2,2));
                //
                //单元格 横向合并============================
                //第一二行 表头
                sheet.addMergedRegion(new CellRangeAddress(0,0,0,13));
                sheet.addMergedRegion(new CellRangeAddress(1,1,0,13));
                //第三行 位置- 2-5单元格合并  、钢轨- 7-9单元格合并
                sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 5));
                sheet.addMergedRegion(new CellRangeAddress(2, 2, 7, 9));
                //第六行 处理意见
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 10, 11));
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 12, 13));
                //第七行 被通知人
                sheet.addMergedRegion(new CellRangeAddress(6, 7, 10, 10));
                sheet.addMergedRegion(new CellRangeAddress(6, 7, 11, 11));
                //第八行 调度
                sheet.addMergedRegion(new CellRangeAddress(6, 7, 12, 12));
                sheet.addMergedRegion(new CellRangeAddress(6, 7, 13, 13));
                //第九行 发现人
                sheet.addMergedRegion(new CellRangeAddress(8, 10, 10, 11));


                for (int i = 2; i <= 10; i++) {
                    Row row = sheet.getRow(i);
                    Iterator<Cell> it = row.cellIterator();
                    while (it.hasNext()) {
                        Cell cell = it.next();
                        cell.setCellStyle(buildContextStyle(workBook));
                    }
                }
                //设置自适应行高
                setCustomHeight(workBook);
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

    private static void setCustomHeight(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        //for (int i = 0; i <= 10; i++) {
        //    Row row = sheet.getRow(i);
        //    int enterCnt = 0;
        //    int colIdxOfMaxCnt = 1;
        //    for (int j = 0; j <= 10; j++) {
        //        if(row.getCell(j) == null){
        //            row.createCell(j);
        //        }
        //        int rwsTemp = row.getCell(j).toString().split("\n").length;
        //        if (rwsTemp > enterCnt) {
        //            enterCnt = rwsTemp;
        //            colIdxOfMaxCnt = j;
        //        }
        //    }
        //    System.out.println(colIdxOfMaxCnt + "列的行数：" + enterCnt);
        //    row.setHeight((short) (enterCnt * 228));
        //}
        sheet.setColumnWidth(4,1700);
        sheet.setColumnWidth(5,1700);
        sheet.setColumnWidth(6,1700);
        sheet.setColumnWidth(7,1700);
        sheet.setColumnWidth(8,3000);
        sheet.setColumnWidth(9,1700);
        sheet.getRow(4).setHeightInPoints(110);
        sheet.getRow(5).setHeightInPoints(38);//处理意见
        sheet.getRow(6).setHeightInPoints(90);//被通知人 签字图片
        sheet.getRow(7).setHeightInPoints(0);//调度  签字图片
        sheet.getRow(8).setHeightInPoints(30);//发现人-执机
        sheet.getRow(9).setHeightInPoints(30);//发现人-助手
        sheet.getRow(10).setHeightInPoints(30);//发现人-领班
    }
        public String addPictureToExcel(Workbook workbook,HSSFSheet hssfSheet,String ImgName,String hand1Name,String hand2Name,Integer steelId){
            System.out.println("进入addPictureToExcel()方法");
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

                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 11, 6, (short) 12, 8);//被通知人
                HSSFClientAnchor diaoduAnchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 13, 6, (short) 14, 8);//调度
                HSSFClientAnchor sketchAnchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 5, (short) 10, 11);//示意图
// 插入图片

                patriarch.createPicture(sketchAnchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
                patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut1.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
                patriarch.createPicture(diaoduAnchor, workbook.addPicture(byteArrayOut2.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));

                File file = new File(finalFilePath);

                file.createNewFile();

                fileOut = new FileOutputStream(file);

// 写入excel文件

                workbook.write(fileOut);

                System.out.println("----Excel文件已生成------");
//创建excel转图片
  this.excelToPicture(finalFilePath,steelId);
            } catch (Exception e) {
                System.out.println("addPictureToExcel 方法内部抛出异常");
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

        private void excelToPicture(String finalFilePath,Integer steelId){
            //最后把/file/excel目录下 转成图片 存到/file/img目录
            String finalDownloadPicturePath = filepath + "file/img/"+ steelId+"-excel.png";
            File file = new File(finalDownloadPicturePath);
            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                fos = new FileOutputStream(file);
                fis = new FileInputStream(finalFilePath);
                com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook(fis);
                //获取图片写入对象
ImageOrPrintOptions imgOption = new ImageOrPrintOptions();

imgOption.setCellAutoFit(true);
imgOption.setOnePagePerSheet(true);
imgOption.setDefaultFont("200");
//将sheet1写入到图片对象中
                SheetRender render = new SheetRender(workbook.getWorksheets().get(0), imgOption);

//将图片写入到输出文件中
                render.toImage(0, fos);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    fis.close();
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    private static HSSFCellStyle buildContextStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); //横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER); //竖向居中
//设置边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
//字体样式
        HSSFFont font = workbook.createFont();
        //font.setBold(true); //加粗
        //font.setColor(Font.COLOR_NORMAL);//黑色
        //font.setFontName("黑体");
        //font.setFontHeightInPoints((short) 12); //字体大小
        //设置自动换行;
        style.setWrapText(true);
        style.setFont(font);
        return style;
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
