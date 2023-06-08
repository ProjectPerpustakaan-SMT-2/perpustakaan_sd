/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author Hafidz Fadhillah
 */
public class BarcodeImage {

    public static void createImage(String file_name, String code_barcode) {
        try {
            Code128Bean code128 = new Code128Bean();
            code128.setHeight(13f);
            code128.setModuleWidth(0.2); // Adjust the width for 80mm width
            code128.setQuietZone(10);
            code128.doQuietZone(true);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            code128.generateBarcode(canvas, code_barcode);
            canvas.finish();

            // Get the barcode image from the canvas
            BufferedImage barcodeImage = canvas.getBufferedImage();

            int textHeight = 80; // Height of the text area in pixels
            int additionalTextHeight = 40; // Height of the additional text
            int spaceBelowBarcode = 40; // Space below the barcode in pixels
            int imageWidth = barcodeImage.getWidth();
            int imageHeight = barcodeImage.getHeight() + textHeight + additionalTextHeight + spaceBelowBarcode;

            BufferedImage finalImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g2d = finalImage.createGraphics();

            // Set background color to white
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, imageWidth, imageHeight);

            // Draw the barcode image
            g2d.drawImage(barcodeImage, 0, textHeight + additionalTextHeight, null);

            // Add text to the top of the image
            String text = "PERPUSTAKAAN SDN 1 SUMBERSARI";
            String additionalText = "Barcode Peminjaman Buku";
            Font font = new Font("Calisto MT", Font.PLAIN, 20);
            FontMetrics fontMetrics = g2d.getFontMetrics(font);

            int textWidth = fontMetrics.stringWidth(text);
            int additionalTextWidth = fontMetrics.stringWidth(additionalText);
            int textX = (imageWidth - textWidth) / 2; // Center text horizontally
            int additionalTextX = (imageWidth - additionalTextWidth) / 2; // Center additional text horizontally
            int textY = (textHeight - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent(); // Center text vertically
            int additionalTextY = 50 + (additionalTextHeight - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent(); // Position of additional text below the first text

            g2d.setFont(font);
            g2d.setColor(Color.BLACK);
            g2d.drawString(text, textX, textY);
            g2d.drawString(additionalText, additionalTextX, additionalTextY);

            g2d.dispose();

            //write to png file
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Hafidz Fadhillah\\Downloads\\Library Perpustakaan_SD\\barcode\\" + file_name);
            ImageIO.write(finalImage, "png", fos);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();

            // Automatic printing
            PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob printJob = defaultPrintService.createPrintJob();

            // Prepare the print document
            DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
            DocAttributeSet attributes = new HashDocAttributeSet();
            InputStream inputStream = new FileInputStream("C:\\Users\\Hafidz Fadhillah\\Downloads\\Library Perpustakaan_SD\\barcode\\" + file_name);
            Doc doc = new SimpleDoc(inputStream, flavor, attributes);

            // Print the document
            printJob.print(doc, null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
