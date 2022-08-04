//package com.test.excel;
//
//import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hwpf.usermodel.Picture;
//import org.w3c.dom.Document;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.*;
//import java.util.List;
//
//public class ExcelTest {
//    public static void main(String[] args) throws Exception{
//
//        String arg1 = "D:\\360MoveData\\Users\\古康\\Desktop\\test.xlsx";
//        String arg2 = "D:\\360MoveData\\Users\\古康\\Desktop\\test.xls";
//        // 方法一：
//        StringBuilder stringBuilder = new StringBuilder();
//        // 打印实现Appendable 接口，可打印到stringbuilder、stringbuffer、BufferedWriter等
//        ExcelToHtml excelToHtml = ExcelToHtml.create(arg1, stringBuilder);
//        excelToHtml.setCompleteHTML(true);// 是否打印完整html
//        excelToHtml.printPage();// 打印
//        System.out.println(stringBuilder.toString());// 输出打印结果
//
//        // 方法二：
//        // excel2Html(arg2,"test.html","D:\\360MoveData\\Users\\古康\\Desktop");
//
//
//    }
//
//    /**
//     * Excel to HTML
//     *
//     */
//    public static void excel2Html(String filename, String htmlid, String ctxPath) throws Exception{
//        InputStream input=new FileInputStream(filename);
//        HSSFWorkbook excelBook=new HSSFWorkbook(input);
//        ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
//        excelToHtmlConverter.processWorkbook(excelBook);
//        List pics = excelBook.getAllPictures();
//        if (pics != null) {
//            for (int i = 0; i < pics.size(); i++) {
//                Picture pic = (Picture) pics.get (i);
//                try {
//                    pic.writeImageContent(new FileOutputStream(ctxPath + pic.suggestFullFileName() ) );
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Document htmlDocument = excelToHtmlConverter.getDocument();
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        DOMSource domSource = new DOMSource (htmlDocument);
//        StreamResult streamResult = new StreamResult(outStream);
//        TransformerFactory tf = TransformerFactory.newInstance();
//        Transformer serializer = tf.newTransformer();
//        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//        serializer.setOutputProperty(OutputKeys.METHOD, "html");
//        serializer.transform (domSource, streamResult);
//        outStream.close();
//
//        OutputStream os = new FileOutputStream(new File(ctxPath, htmlid+".html"));
//        final PrintStream printStream = new PrintStream(os);
//        String htmlStr = new String(outStream.toByteArray());
//        printStream.print(htmlStr);
//        printStream.close();
//    }
//}
