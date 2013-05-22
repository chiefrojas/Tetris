/*
 * This file contains the pieces for the tetris game inside of a factory pattern
 * 
 * Note: This using the enum:Array which can be found @ http://student.cs.wichita.edu/~dacanare/file-browser/view.php?file=/cs411-objectOrientedProgramming/tetris/net/domstyle/projects/tetris//TetrisBlock.java
 * Having that array saved me countless time attempting to think up a method to implement this project
 */
import java.awt.Color;
import java.util.Random;


public class PieceFactory {

    private Color fillColor;
    
    private int BuildLocale;
    
    private int width;
    
    private int height;
    
    private boolean[][] result;
    
    private int _saveMee;

    public PieceFactory() {
        Random rand = new Random();
       // RGB Colors
       _saveMee = rand.nextInt(8);

       // Takes a random number for  Red Green Blue [0, 255] 
       
       float red = rand.nextFloat();
       float green = rand.nextFloat();
       float blue = rand.nextFloat();
                            
       // parses them into a new color for the shape that is outputted by the factory pattern 
       
        fillColor = new Color(red, green, blue);
        
        
        width = width;
        
        // picks a random shape (0, 7) [8 total] and sends it off to the rest of the program
        _randomShape();
        width = width;
        height = height;
        
        // Allows for the program to place the newly generated piece into the middle column OR atleast attempt to
        BuildLocale = 5;
    }
    
    private boolean[][] _randomShape(){
        switch(_saveMee){
//  **** CUSTOM PIECE ***** "T"
            case 0:
            result = new boolean[][] {
                    {
                            true,   false,  false,
                            true,   true,   true,
                            true,  false,  false,
                    },
                    {
                            true,  true,   true,
                            false,  true,   false,
                            false,  true,   false,
                    },
                    {
                            false,  false,  true,
                            true,   true,   true,
                            false,  false,  true,
                    },
                    {
                            false,  true,   false,
                            false,  true,   false,
                            true,   true,   true,
                            
                    }
                };
            width = 3;
            height = 3;
            break;
// C
            case 1:
            result = new boolean[][] {
                        { 
                            
                               false, true,  true,  
                               false, true,  false, 
                               false, true,  true,  
                        },
                        {
                                false, false, false,
                                true,  true, true,
                                true,   false, true,
                                false, false, false,
                        },
                        {
                                false, true, true, 
                                false, false, true, 
                                false, true, true,
     

                        },
                        {
                                false, false, false, 
                                true,  false,  true,  
                                true,  true,  true,  
                                false, false, false, 
                        }
                };
            width = 3;
            height = 3;
            break;
    // J
            case 2:
            result = new boolean[][] {
                    {
                            true,   false,  false,
                            true,   true,   true,
                            false,  false,  false,
                    },
                    {
                            false,  true,   true,
                            false,  true,   false,
                            false,  true,   false,
                    },
                    {
                            false,  false,  false,
                            true,   true,   true,
                            false,  false,  true,
                    },
                    {
                            false,  true,   false,
                            false,  true,   false,
                            true,   true,   false,
                    }
                };
            width = 3;
            height = 2;
            break;
       //L
            case 3:
            result = new boolean[][] {
                    {
                            false,  false,  true,
                            true,   true,   true,
                            false,  false,  false,
                    },
                    {
                            false,  true,   false,
                            false,  true,   false,
                            false,  true,   true,
                    },
                    {
                            false,  false,  false,
                            true,   true,   true,
                            true,   false,  false,
                    },
                    {
                            true,   true,   false,
                            false,  true,   false,
                            false,  true,   false,
                    }
                };
            width = 3;
            height = 2;                
                break;
          // BOX
                case 4:
                result =  new boolean[][] {
                {
                        true,   true,
                        true,   true,
                },
                {
                        true,   true,
                        true,   true,
                },
                {       
                        true,   true,
                        true,   true,
                },
                {
                        true,   true,
                        true,   true,
                }};
            width = 2;
            height = 2;                
                break;
            //Half I    
                case 5:
                result = new boolean[][] {
                {
                        false,  true,   true,
                        true,   true,   false,
                        false,  false,  false,
                },
                {
                        false,  true,   false,
                        false,  true,   true,
                        false,  false,  true,
                },
                {
                        false,  false,  false,
                        false,  true,   true,
                        true,   true,   false,
                },
                {
                        true,   false,  false,
                        true,   true,   false,
                        false,  true,   false,
                }
        };
        width = 3;
        height = 2;
        break;
        
        case 6:
        result = new boolean[][] {
        {
            false,  true,   true,
            true,   true,   false,
            false,  false,  false,
        },
        {
            false,  true,   false,
            false,  true,   true,
            false,  false,  true,
        },
        {
            false,  false,  false,
            false,  true,   true,
            true,   true,   false,
        },
        {
            true,   false,  false,
            true,   true,   false,
            false,  true,   false,
        }};
        width = 3;
        height = 2;
        break;
        case 7:
        result = new boolean[][] {
                {
                        true,   true,   false,
                        false,  true,   true,
                        false,  false,  false,
                },
                {
                        false,  false,  true,
                        false,  true,   true,
                        false,  true,   false,
                },
                {
                        false,  false,  false,
                        true,   true,   false,
                        false,  true,   true,
                },
                {
                        false,  true,   false,
                        true,   true,   false,
                        true,   false,  false,
                }
        };
        width = 3;
        height = 2;
        break;
        }
        return result;
    }
 
   //******************************************************** GETTERS************************************************
    
    public Color getFillColor() {
        return fillColor;
    }
    
    public int getHeight() {
        return width;
    }
    
    public int getBuildLocale() {
        return BuildLocale;
    }
    public int getWidth() {
        return height;
    }
    
    //*******************************************************************************************************
    
    // checks to see if a tile is at the location where rotation + Locale match the block

    public boolean moveOkay(int x, int y, int rotation) {
        return result[rotation][y * width + x];
    }
    
    // ******************************* LEFT **************************************
    
    public int screenMinX(){
      int x = screenMinX(0);
      return x;
    }
    
    public int screenMinX(int rotation) {
    
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < width; j++) {
                if(moveOkay(i, j, rotation)) {
                    return i;
                }
            }
        }
        return -1; //default case, return 0
    }
    
    // ********************************** RIGHT *******************************************

    public int screenMaxX(){
      int x = screenMaxX(0);
      return x;
    }
        
    public int screenMaxX(int rotation) {
    
        for(int x = width - 1; x >= 0; x--) {
            for(int y = 0; y < width; y++) {
                if(moveOkay(x, y, rotation)) {
                    return width - x;
                }
            }
        }
        return -1; //default case, return 0
    }
    
    //***************************************  UP ************************************************

    public int screenMaxY(){
      int x = screenMaxY(0);
      return x;
    }
        
    public int screenMaxY(int rotation) {
    
        for(int y = 0; y < width; y++) {
            for(int x = 0; x < width; x++) {
                if(moveOkay(x, y, rotation)) {
                    return y;
                }
            }
        }
        return -1; //default case, return 0
    }

    // ****************************************************** DOWN *****************************************
    
    public int screenMinY(){
      int x = screenMinY(0);
      return x;
    }
        
    public int screenMinY(int rotation) {
        for(int y = width - 1; y >= 0; y--) {
            for(int x = 0; x < width; x++) {
                if(moveOkay(x, y, rotation)) {
                    return width - y;
                }
            }
        }
        return -1; //default case, return 0
    }
    //******************************************************************************************************
}
