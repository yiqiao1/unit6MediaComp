import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to keep only blue values and change others to 0 */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  /** Method to negate all pixels */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int red = pixelObj.getRed();
        int blue = pixelObj.getBlue();
        int green = pixelObj.getGreen();
          
        pixelObj.setRed(255 - red);
        pixelObj.setGreen(255 - green);
        pixelObj.setBlue(255 - blue);
      }
    }
  }
  
  /** Method to turn picture into shades of grey */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int red = pixelObj.getRed();
        int blue = pixelObj.getBlue();
        int green = pixelObj.getGreen();
        int avg = (red + blue + green)/3;
          
        pixelObj.setRed(avg);
        pixelObj.setGreen(avg);
        pixelObj.setBlue(avg);
      }
    }
  }
  
  /** Method to make fish in water easier to see */
  public void fixUnderwater()
  {
    Pixel[][] pixels = this.getPixels2D();
    
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int red = pixelObj.getRed();
        int blue = pixelObj.getBlue();
        int green = pixelObj.getGreen();
          
        pixelObj.setRed(red + 150);
        pixelObj.setBlue(blue - 50);
        pixelObj.setGreen(green - 10);
      }
    }
  }
  
  public void sepia()
  {
    Pixel[][] pixels = this.getPixels2D();

    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {  
        int red = pixelObj.getRed();
        int blue = pixelObj.getBlue();
        int green = pixelObj.getGreen();
        int avg = (red + blue + green)/3;
          
        pixelObj.setRed(avg);
        pixelObj.setGreen(avg);
        pixelObj.setBlue(avg);
      }
    }
    
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int red = (int)(pixelObj.getRed()*.9);
        int blue1 = (int)(pixelObj.getBlue()*.8);
        int blue2 = (int)(pixelObj.getBlue()*.9);
        int green = (int)(pixelObj.getGreen()*.9);
          
        if (red < 60)
        {
          pixelObj.setRed(red);
          pixelObj.setGreen(green);
          pixelObj.setBlue(blue2);
        }
        else if (red < 190)
        {
          pixelObj.setBlue(blue1);    
        }
        else
        {
          pixelObj.setBlue(blue2);   
        }
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from right to left */
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    * from top to bottom */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height / 2; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height - 1 - row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  /** Method that just a square part of the picture from bottom left to top right around a mirror placed
    * on the diagonal line (the diagonal line is the one where the row index equals the column index) */
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel bottomLeftPixel = null;
    Pixel topRightPixel = null;
    int height = pixels.length;
    for (int col = 0; col < height; col++)
    {
      for (int row = 0; row < height; row++)
      {
        bottomLeftPixel = pixels[row][col];
        topRightPixel = pixels[col][row];
        topRightPixel.setColor(bottomLeftPixel.getColor());
        
        if (row == col)
        {
            row++;
            col = 0;
        }
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    * from bottom to top */
  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height / 2; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height - 1 - row][col];
        topPixel.setColor(bottomPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        count++;
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
    
    System.out.println(count);
  }
  
  /** Mirror arms on snowman */
  public void mirrorArms()
  {
    int leftMirrorPoint = 191;
    int rightMirrorPoint = 196;
    Pixel topLeftPixel = null;
    Pixel bottomLeftPixel = null;
    Pixel topRightPixel = null;
    Pixel bottomRightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the cols
    for (int col = 105; col < 170; col++)
    {
      // loop from 13 to just before the mirror point
      for (int row = 159; row < leftMirrorPoint; row++)
      {
        topLeftPixel = pixels[row][col];      
        bottomLeftPixel = pixels[leftMirrorPoint - row + leftMirrorPoint]                       
                         [col];
        bottomLeftPixel.setColor(topLeftPixel.getColor());
      }
    }
    
    // loop through the cols
    for (int col = 239; col < 294; col++)
    {
      // loop from 13 to just before the mirror point
      for (int row = 172; row < rightMirrorPoint; row++)
      {
        topRightPixel = pixels[row][col];      
        bottomRightPixel = pixels[rightMirrorPoint - row + rightMirrorPoint]                       
                         [col];
        bottomRightPixel.setColor(topRightPixel.getColor());
      }
    }
  }
  
  /** Mirror a seagull */
  public void mirrorGull()
  {
    int mirrorPoint = 218;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 234; row < 323; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 238; col < 344; col++)
      {
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
   
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    */
  public void cropAndCopy(Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol, int endSourceCol, int startDestRow, int startDestCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = sourcePicture.getPixels2D();
    
    for (int fromRow = startSourceRow, toRow = startDestRow; 
         fromRow < endSourceRow;
         fromRow++, toRow++)
    {
      for (int fromCol = startSourceCol, toCol = startDestCol; 
           fromCol < endSourceCol;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  public void scaleByHalf(Picture sourcePicture, int startDestRow, int startDestCol)
  {
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = sourcePicture.getPixels2D();
    Pixel fromPixel = null;
    Pixel toPixel = null;
    
    for (int fromRow = 0, toRow = startDestRow;
         fromRow < fromPixels.length;
         fromRow += 2, toRow++)
    {
      for (int fromCol = 0, toCol = startDestCol;
            fromCol < fromPixels[0].length;
            fromCol += 2, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }
  }
  
  public void upsideDown(Picture sourcePicture)
  {
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = sourcePicture.getPixels2D();
    Pixel fromPixel = null;
    Pixel toPixel = null;
    
    for (int fromRow = 0; fromRow < fromPixels.length; fromRow++)
    {
        for (int fromCol = 0; fromCol < fromPixels[0].length; fromCol++)
        {
            fromPixel = fromPixels[fromRow][fromCol];
            toPixel = toPixels[toPixels.length - fromRow - 1][fromCol];
            toPixel.setColor(fromPixel.getColor());
        }
    }
  }
  
  /** Method to create a collage of several pictures */
  public void createCollage(Picture sourcePic)
  {
    Picture pic1 = new Picture(sourcePic);
    pic1.negate();
    pic1.mirrorHorizontal();
    
    Picture pic2 = new Picture(sourcePic);
    pic2.fixUnderwater();
    pic2.mirrorVertical();
    
    Picture pic3 = new Picture(sourcePic);
    pic3.keepOnlyBlue();
    
    Picture pic4 = new Picture(sourcePic);
    pic4.sepia();
    pic4.mirrorHorizontalBotToTop();
    
    Picture pic5 = new Picture(sourcePic);
    pic5.zeroBlue();
    pic5.mirrorVerticalRightToLeft();
    
    Picture pic6 = new Picture(sourcePic);
    pic6.upsideDown(sourcePic);
    
    this.copy(sourcePic, 0, 0);
    this.copy(pic1, 400, 0);
    this.copy(pic2, 0, 600);
    this.scaleByHalf(pic3, 400, 600);
    this.scaleByHalf(pic4, 600, 600);
    this.scaleByHalf(pic5, 400, 900);
    this.scaleByHalf(pic6, 600, 900);
    
    this.write("MyCollage.jpg");

  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
