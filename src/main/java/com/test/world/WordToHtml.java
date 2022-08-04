package com.test.world;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class WordToHtml {
//    public static void main(String argv[]) {
//        try {
//            // 方法一：
//             String content=wordToHtml("D:\\Desktop\\test.docx");
//             System.out.println(content);
//
//            HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream("D:\\Desktop\\test.doc"));
//
//            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
//                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
//                            .newDocument());
//            wordToHtmlConverter.processDocument(wordDocument);
//            Document htmlDocument = wordToHtmlConverter.getDocument();
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            DOMSource domSource = new DOMSource(htmlDocument);
//            StreamResult streamResult = new StreamResult(out);
//
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer serializer = tf.newTransformer();
//            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//            serializer.setOutputProperty(OutputKeys.METHOD, "html");
//            serializer.transform(domSource, streamResult);
//            out.close();
//
//            String result = new String(out.toByteArray());
//            System.out.println(result);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
    public static String wordToHtml(String filePath) throws Exception{
        if(filePath.endsWith(".doc")){
            String content=convert2Html(filePath);
            return content;
        }
        if(filePath.endsWith(".docx")){
            String content=docxToHtml(filePath);
            return content;
        }
        return null;
    }
    //docx转html
    //生成html文件
    //输出html标签和内容
    public static String docxToHtml(String sourceFileName) throws Exception {
        String htmlPath=sourceFileName.substring(0,sourceFileName.indexOf("."))+".html";
        XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
        XHTMLOptions options = XHTMLOptions.create().indent(4);
        File outFile = new File(htmlPath);
        outFile.getParentFile().mkdirs();
        OutputStream out = new FileOutputStream(outFile);
        XHTMLConverter.getInstance().convert(document,out, options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(document, baos, options);
        baos.close();
        String content =new String(baos.toByteArray());
        //替换UEditor无法识别的转义字符
        String htmlContent1=content.replaceAll("&ldquo;","\"").replaceAll("&rdquo;","\"").replaceAll("&mdash;","-");
        return htmlContent1;
    }
    //doc 转 html
    public static String convert2Html(String fileName)
            throws TransformerException, IOException,
            ParserConfigurationException {

        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));
        //兼容2007 以上版本
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.processDocument(wordDocument);
        //解析html
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "HTML");
        serializer.transform(domSource, streamResult);
        out.close();
        String htmlContent=new String(out.toByteArray());
        //替换UEditor无法识别的转义字符
        String htmlContent1=htmlContent.replaceAll("&ldquo;","\"").replaceAll("&rdquo;","\"").replaceAll("&mdash;","-");
        return htmlContent1;
    }

}
