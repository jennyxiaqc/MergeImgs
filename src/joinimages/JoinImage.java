package joinimages;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * This code try to join two BufferedImage
 * @author wangdq
 * 2013-12-29
 */
public class JoinImage {
    public static void main(String args[])
    {   
                  Scanner console = new Scanner(System.in);
            int qty;
      // Get length from the user.
      System.out.print("Enter qty of images(>1): ");
      qty = console.nextInt();
      if (qty<2) 
           {
               System.out.println("Wrong qty");
               return;
           }

       //String filename = System.getProperty("user.home")+File.separator;
        try {
          //  BufferedImage img1 = ImageIO.read(new File(filename+"1.png")); //C:\Users\jenny\Downloads
          //  BufferedImage img2=ImageIO.read(new File(filename+"2.png"));
      
            BufferedImage img1 = ImageIO.read(new File("C:\\flyerCreate\\images\\1.png")); //C:\Users\jenny\Downloads
           BufferedImage img2=ImageIO.read(new File("C:\\flyerCreate\\images\\2.png"));

           BufferedImage joinedImg = joinBufferedImage(img1,img2);
           String filename;
           for (int i=3;i<=qty;i++)
           {
               filename="C:\\flyerCreate\\images\\"+i+".png";
               img2=ImageIO.read(new File(filename));
                joinedImg = joinBufferedImage(joinedImg,img2);
                     
           }
            
         //  boolean success = ImageIO.write(joinedImg, "png", new File(filename+"joined.png"));
         
                boolean success = ImageIO.write(joinedImg, "png", new File("C:\\flyerCreate\\images\\joined.png"));
     
            System.out.println("png saved success? "+success);
            //ConvertPngToJpg("C:\\flyerCreate\\images\\joined.png", "C:\\flyerCreate\\images\\joined.jpg");
            ConvertPngToJpg(joinedImg, "C:\\flyerCreate\\images\\joined.jpg");
           
            System.out.println("Jpg image saved at C:\\flyerCreate\\images\\joined.jpg");
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * join two BufferedImage
     * you can add a orientation parameter to control direction
     * you can use a array to join more BufferedImage
     */

    public static BufferedImage joinBufferedImage(BufferedImage img1,BufferedImage img2) {

        //do some calculate first
        int offset  = 5;
        int wid = img1.getWidth()+img2.getWidth()+offset;
        int height = Math.max(img1.getHeight(),img2.getHeight())+offset;
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, wid, height);
        //draw image
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth()+offset, 0);
        g2.dispose();
        return newImage;
    }
    
    /**
     *
     * @param pngFileName
     * @param jpgFileName
     */
    public static void ConvertPngToJpg(String pngFileName,String jpgFileName) {

        try {

            File input = new File(pngFileName);
            File output = new File(jpgFileName);

            BufferedImage image = ImageIO.read(input);
            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            result.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
            ImageIO.write(result, "jpg", output);

        }  catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 
     * @param image
     * @param jpgFileName 
     */
  
    public static void ConvertPngToJpg(BufferedImage image,String jpgFileName) {

        try {

            //File input = new File(pngFileName);
            File output = new File(jpgFileName);

            //BufferedImage image = ImageIO.read(input);
            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            result.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
            ImageIO.write(result, "jpg", output);

        }  catch (IOException e) {
            e.printStackTrace();
        }

    }
}