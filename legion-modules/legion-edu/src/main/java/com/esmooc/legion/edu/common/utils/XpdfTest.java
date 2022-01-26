package com.esmooc.legion.edu.common.utils;

import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfImageType;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class XpdfTest {
    public static void main(String[] args) throws IOException {
        // 定义保存路径
        final String outputPath = "/Users/leiyanfu/IdeaProjects/我的/脚手架/单体框架/多模块/legion/legion-modules/legion-edu/src/main/java/com/esmooc/legion/edu/common/utils/my.pdf";
        // 定义页眉与页脚字体颜色
        Color headerAndFooterColor = new Color(10, 195, 255);
        // 定义分割线颜色
        Color lineColor = new Color(210, 0, 210);
        // 获取背景图片
        try (InputStream backgroundImageInputStream = new URL("https://i0.hdslb.com/bfs/article/1e60a08c2dfdcfcd5bab0cae4538a1a7fe8fc0f3.png").openStream()) {
            // 设置背景图片
            XEasyPdfHandler.Document.build().setGlobalBackgroundImage(
                    // 构建图片并垂直居中
                    XEasyPdfHandler.Image.build(backgroundImageInputStream, XEasyPdfImageType.PNG).enableVerticalCenterStyle()
                    // 设置全局页眉
            ).setGlobalHeader(
                    // 构建页眉
                    XEasyPdfHandler.Header.build(
                            // 构建页眉文本，并居中显示
                            XEasyPdfHandler.Text.build("这是粗体页眉")
                                    // 设置水平居中
                                    .setStyle(XEasyPdfTextStyle.CENTER)
                                    // 设置字体大小
                                    .setFontSize(20F)
                                    // 设置字体颜色
                                    .setFontColor(headerAndFooterColor)
                                    // 使用粗体字
                                    .setDefaultFontStyle(XEasyPdfDefaultFontStyle.BOLD)
                    )
                    // 设置全局页脚
            ).setGlobalFooter(
                    // 构建页脚
                    XEasyPdfHandler.Footer.build(
                            // 构建页脚文本，并居中显示
                            XEasyPdfHandler.Text.build("这是粗体页脚")
                                    // 设置水平居中
                                    .setStyle(XEasyPdfTextStyle.CENTER)
                                    // 设置字体大小
                                    .setFontSize(20F)
                                    // 设置字体颜色
                                    .setFontColor(headerAndFooterColor)
                                    // 使用粗体字
                                    .setDefaultFontStyle(XEasyPdfDefaultFontStyle.BOLD)
                    )
                    // 添加页面
            ).addPage(
                    // 构建页面
                    XEasyPdfHandler.Page.build(
                            // 构建文本
                            XEasyPdfHandler.Text.build(
                                    "x-easypdf简介（细体）"
                            ).setStyle(XEasyPdfTextStyle.CENTER)
                                    // 设置字体大小
                                    .setFontSize(30F)
                                    // 设置上边距
                                    .setMarginTop(20F)
                                    // 使用细体字
                                    .setDefaultFontStyle(XEasyPdfDefaultFontStyle.LIGHT)
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "x-easypdf是一个基于PDFBOX的开源框架，"
                            ).setFontSize(16F).setFontColor(new Color(51, 0, 153))
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "专注于PDF文件导出功能，"
                            ).enableTextAppend().setFontSize(16F).setFontColor(new Color(102, 0, 153))
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "以组件形式进行拼接，"
                            ).enableTextAppend().setFontSize(16F).setFontColor(new Color(153, 0, 153))
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "简单、方便，功能丰富，"
                            ).enableTextAppend().setFontSize(16F).setFontColor(new Color(204, 0, 153))
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "欢迎大家试用并提出宝贵意见。"
                            ).enableTextAppend().setFontSize(16F).setFontColor(new Color(255, 0, 153))
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "-- by xsx"
                            ).setStyle(XEasyPdfTextStyle.RIGHT).setMarginTop(10F).setMarginRight(10F)
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "2021.10.10"
                            ).setStyle(XEasyPdfTextStyle.RIGHT).setMarginTop(10F).setMarginRight(10F)
                            // 构建实线分割线
                            ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                            // 构建虚线分割线
                            ,XEasyPdfHandler.SplitLine.DottedLine.build().setLineLength(10F).setMarginTop(10F).setLineWidth(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                            // 构建实线分割线
                            ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                    )
                    // 保存并关闭文档
            ).save(outputPath).close();
        }
    }
}
