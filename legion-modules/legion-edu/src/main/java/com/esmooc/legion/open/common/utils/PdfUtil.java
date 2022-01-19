package com.esmooc.legion.open.common.utils;

import com.esmooc.legion.open.common.constant.Constants;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdfUtil {

    public static void main(String[] args) throws Exception {
        Map<String, Object> o = new HashMap<>();
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();
        int number2 = 17;
        int znumber2 = (number2 - 1) * 2;
        int number3 = 28;
        int znumber3 = number3 * 2;
        String add = "1测试地址内蒙古呼伦贝尔市海拉尔区浩翔酒店";
        System.out.println(getLength(add) + "add长度" + add.length());
        String tname = "1测试名称青少年儿童竞赛座谈会";
        System.out.println(getLength(tname) + "tname长度" + tname.length());
        String name = "张三";
        String cname = "内蒙古和利基于BIM管理咨询全生命周期管理公司";
        o.put("con", "内工建协学字【 " + year + " 】（ " + 1 + " ）号");
        o.put("name", "姓名：" + name);
        o.put("cname", "单位：" + cname);
        String con1 = "    于 " + year + " 年 " + month + " 月 " + day + " 日 参加在";
        if (add != "" && add.length() > 15) {
            con1 += add.substring(0, 15);
        } else {
            con1 += add;
        }
        o.put("con1", con1);
        String con2 = "";
        if (add != "" && add.length() > 15) {
            if (add.length() > 15 && add.length() < 31) {
                con2 += add.substring(15, add.length());
                for (int i = 0; i < (znumber2 - getLength(add.substring(15, add.length()))); i++) {
                    con2 += " ";
                }
            } else {
                con2 += add.substring(15, 31);
            }
        } else {
            for (int i = 0; i < znumber2; i++) {
                con2 += " ";
            }
        }
        con2 += "举办的";
        if (tname != "" && tname.length() > 12) {
            con2 += tname.substring(0, 12);
        } else {
            con2 += tname;
        }
        o.put("con2", con2);
        String con3 = "";
        if (tname != "" && tname.length() > 12) {
            if (tname.length() > 12 && tname.length() < 40) {
                con3 += tname.substring(12, tname.length());
                for (int i = 0; i < (znumber3 - getLength(tname.substring(12, tname.length()))); i++) {
                    con3 += " ";
                }
            } else {
                con3 += tname.substring(12, 40);
            }
        } else {
            for (int i = 0; i < znumber3; i++) {
                con3 += " ";
            }
        }
        con3 += "培训，";
        o.put("con3", con3);
        o.put("con4", "完成学习。");
        o.put("jie1", "内蒙古自治区工程建设协会");
        o.put("jie2", "2019 年 08 月 30 日");
        String text = "测试二维码";
        // 嵌入二维码的图片路径
        String imgPath = "";
        // 生成的二维码的路径及名称
        String destPath = "C:\\Users\\sunbin\\Desktop\\jam.jpg";
        // 打印出解析出的内容
        fillTemplate(o, "", Constants.FILE_PATH + "/muban.pdf");
    }

    public static int getLength(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

    /**
     * @param o            写入的数据
     * @param out          自定义保存pdf的文件流
     * @param templatePath pdf模板路径
     */
    // 利用模板生成pdf
    public static String fillTemplate(Map<String, Object> o, String imgPath, String templatePath) {
        //生成随机文件名
        String pdf1Name = BaseUtils.getUUID();
        File f = new File(Constants.FILE_PATH + "/" + pdf1Name + ".pdf");
        OutputStream out = null;
        try {
            out = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfReader reader;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                String value = o.get(name) != null ? o.get(name).toString() : null;
                form.setField(name, value);
            }

            //添加图片
            if (imgPath != null && imgPath != "") {
                List<AcroFields.FieldPosition> list = form.getFieldPositions("erweima");
                if (list != null && list.size() > 0) {
                    int pageNo = list.get(0).page;
                    Rectangle signRect = list.get(0).position;
                    float x = signRect.getLeft();
                    float y = signRect.getBottom();
                    Image image = Image.getInstance(imgPath);
                    PdfContentByte under = stamper.getOverContent(pageNo);
                    //设置图片大小
                    image.scaleAbsolute(signRect.getWidth(), signRect.getHeight());
                    //设置图片位置
                    image.setAbsolutePosition(105, 29);
                    under.addImage(image);
                }
            }

            //设置不可编辑
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            Font font = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED, 10f, Font.NORMAL, BaseColor.YELLOW);
            doc.add(new Paragraph("字体测试", font));
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            copy.close();
            stamper.close();
            doc.close();
            out.close();
            bos.close();
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        } catch (DocumentException e) {
            System.out.println(e);
        }
        return pdf1Name + ".pdf";
    }

}


