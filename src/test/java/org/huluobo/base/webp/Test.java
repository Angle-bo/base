package org.huluobo.base.webp;

import com.luciad.imageio.webp.WebPWriteParam;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        encodeTest();
    }

   static void encodeTest() throws IOException {
       String inputPngPath = "C:/Users/G8670/Pictures/Saved Pictures/素材/200031.png";
       //String inputJpgPath = "test_pic/test.jpg";
       String outputWebpPath = "C:/Users/G8670/Pictures/Saved Pictures/素材/200031.webp";

       // Obtain an image to encode from somewhere
       BufferedImage image = ImageIO.read(new File(inputPngPath));

       // Obtain a WebP ImageWriter instance
       ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();

       // Configure encoding parameters
       WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
       writeParam.setCompressionMode(WebPWriteParam.MODE_DEFAULT);

       // Configure the output on the ImageWriter
       writer.setOutput(new FileImageOutputStream(new File(outputWebpPath)));

       // Encode
       long st = System.currentTimeMillis();
       writer.write(null, new IIOImage(image, null, null), writeParam);
       System.out.println("cost: " + (System.currentTimeMillis() - st));
    }
}
