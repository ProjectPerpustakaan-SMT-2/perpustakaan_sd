/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import javax.print.attribute.standard.MediaPrintableArea;
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

            int topTextHeight = 150; // Height of the topText area in pixels
            int midleTextHeight = 40; // Height of the midleText text
            int spaceBelowBarcode = 70; // Space below the barcode in pixels
            int imageWidth = barcodeImage.getWidth();
            int imageHeight = barcodeImage.getHeight() + topTextHeight + midleTextHeight + spaceBelowBarcode;

            BufferedImage finalImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g2d = finalImage.createGraphics();

            // Set background color to white
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, imageWidth, imageHeight);

            // Draw the barcode image
            g2d.drawImage(barcodeImage, 0, topTextHeight + midleTextHeight, null);

            // Add text to the top of the image
            String topText = "PERPUSTAKAAN SDN 1 SUMBERSARI";
            String midleText = "Barcode Peminjaman Buku";
            String bellowText = "~ SIMPAN BUKTI PEMINJAMAN BUKU ~";
            Font font = new Font("Calisto MT", Font.PLAIN, 20);
            FontMetrics fontMetrics = g2d.getFontMetrics(font);

            int topTextWidth = fontMetrics.stringWidth(topText);
            int midleTextWidth = fontMetrics.stringWidth(midleText);
            int bellowTextWidth = fontMetrics.stringWidth(bellowText);

            int topTextX = (imageWidth - topTextWidth) / 2; // Center topText horizontally
            int midleTextX = (imageWidth - midleTextWidth) / 2; // Center midleText text horizontally
            int bellowTextX = (imageWidth - bellowTextWidth) / 2; // Center text horizontally

            int topTextY = 40 + (topTextHeight - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent(); // Center topText vertically
            int midleTextY = 120 + (midleTextHeight - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent(); // Position of midleText text below the first text
            int barcodeTextY = imageHeight - spaceBelowBarcode / 2;

            g2d.setFont(font);
            g2d.setColor(Color.BLACK);
            g2d.drawString(topText, topTextX, topTextY);
            g2d.drawString(midleText, midleTextX, midleTextY);
            g2d.drawString(bellowText, bellowTextX, barcodeTextY);

            // Load the image to be placed on top
            Image image = ImageIO.read(new FileInputStream("C:\\Users\\Hafidz Fadhillah\\Documents\\NetBeansProjects\\Perpustakaan_SD\\src\\assets\\layouts\\Logo.png"));

            // Calculate the position to place the image
            int imageX = (imageWidth - image.getWidth(null)) / 2; // Center image horizontally
            int imageY = 30; // Distance from the top

            // Draw the image on top of the barcode
            g2d.drawImage(image, imageX, imageY, null);

            g2d.dispose();

            //write to png file
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Hafidz Fadhillah\\Documents\\NetBeansProjects\\Perpustakaan_SD\\src\\assets\\barcode\\" + file_name);
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

            // Set the print job width and height
            attributes.add(new MediaPrintableArea(0, 0, 80, 200, MediaPrintableArea.MM));

            InputStream inputStream = new FileInputStream("C:\\Users\\Hafidz Fadhillah\\Documents\\NetBeansProjects\\Perpustakaan_SD\\src\\assets\\barcode\\" + file_name);
            Doc doc = new SimpleDoc(inputStream, flavor, attributes);

            // Print the document
            printJob.print(doc, null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        String code = "1236728";

        createImage("Barcode.png", code);
        System.out.println("Sukses");
    }
}
