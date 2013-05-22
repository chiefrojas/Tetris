import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
public class Bg extends JPanel {
    public  int _blockSize = 20; //  size of bricks|blocks
 
    public int _x = 11;
    private int Y = 15;
    private int _padding = 2;
    private int screenMid = 115; // 230 / 2
    private PieceFactory[] _blocks = null;
    private Main main = null; // make sure is empty
    
    public Bg(Main main) {
        this.main = main; // ref
        this._blocks = new PieceFactory[_x * Y]; 
        setPreferredSize(new Dimension(230, 310)); // set panel size
        setBackground(Color.black);

    }
    public void purgeRow() {//clear
        for(int i = 0; i < _blocks.length; i++) {
            _blocks[i] = null; // empties row
        }
    }

    /* Grounds the piece in place where ever it lands
     * this creates a hit test which tests the collision
     * between two objects (piece to piece || piece to gameboard)
     */
    
    public boolean check(PieceFactory type, int x, int y, int rotation) {
        if(x < -type.screenMinX(rotation) || x + type.getHeight() - type.screenMaxX(rotation) >= _x) {
            return false;
        }
        if(y < -type.screenMaxY(rotation) || y + type.getHeight() - type.screenMinY(rotation) >= Y) {
            return false;
        }
        for(int i = 0; i < type.getHeight(); i++) {
            for(int j = 0; j < type.getHeight(); j++) {
                if(type.moveOkay(i, j, rotation)){
                    if(validMove(x + i, y + j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    

    
    public void ground(PieceFactory type, int x, int y, int rotation) {

        for(int i = 0; i < type.getHeight(); i++) {
            for(int j = 0; j < type.getHeight(); j++) {
                if(type.moveOkay(i, j, rotation)) {
                    setblock(i + x, j + y, type);
                }
            }
        }
    }
    //*********************************************************************************************
    // These test for the completeness of the lines, which output later to award points to the user
    
    public int test() {//linecomp
        int lines = 0;
        for(int i = 0; i < Y; i++) {
            if(testCompleted(i)) {
                lines++;
            }
        }
        return lines;
    }
    private boolean testCompleted(int line) {
        for(int i = 0; i < _x; i++) {
            if(!validMove(i, line)) {
                return false;
            }
        }
        for(int i = line - 1; i >= 0; i--) {
            for(int height = 0; height < _x; height++) {
                setblock(height, i + 1, getblock(height, i));
            }
        }
        return true;
    }
    
    //*********************************************

    // Tests for valid moves through hit detection
    private boolean validMove(int x, int y) {
        return _blocks[y * _x + x] != null;
    }
    
    // Sets the block after hit detection confirms
    private void setblock(int  x, int y, PieceFactory type) {
        _blocks[y * _x + x] = type;
    }
        
    // Reference for the block object
    public PieceFactory getblock(int x, int y) {
        return _blocks[y * _x + x];
    }
    
    // paint
    public void paintComponent(Graphics aBrush) {
        super.paintComponent(aBrush);
        // better brush
        java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
        
        // if game is paused, then print "Pause?" on screen
        if(main.gamePaused()) {
            betterBrush.setColor(Color.WHITE);
            String message = "Pause?";
            betterBrush.drawString(message, (screenMid - 20), 150);
            // if game is ended OR game is restarted turn screen black, then output specified message
        } else if( (main.gameEnded()==true) || (main.gameRestarted()==true) ) {
            setBackground(Color.black);
            
            String message = null;

            
            if(main.gameRestarted()){
               message = "Tetris!!!";
               
            }else{
               message = "Rerun program";

            }

            betterBrush.drawString(message, (screenMid - 20), 150);

        
        } else {
            for(int i = 0; i < _x; i++) {
                for(int j = 0; j < Y; j++) {
                    PieceFactory block = getblock(i, j);
                    if(block != null) {
                        build(block, i * _blockSize, (j) * _blockSize, betterBrush);
                    }
                }
            }
            
            // get block, find it's position 
            PieceFactory type = main.getBlock();
            int newYLocation = main.getY();
            int newXLocation = main.getX();
            int rotation = main.getCurrAngle();
            
            for(int i = 0; i < type.getHeight(); i++) {
                for(int j = 0; j < type.getHeight(); j++) {
                    if(newXLocation + j >= 2) {
                        if(type.moveOkay(i, j, rotation)){
                            build(type, (newYLocation + i) * _blockSize, (newXLocation + j) * _blockSize, betterBrush);
                        }
                    }
                }
            }
            
        Color marker = type.getFillColor();
            

        betterBrush.setColor(Color.WHITE);
        betterBrush.drawString("Press 'A' to change song.", (screenMid - 70), 150);
        betterBrush.drawString("Now Playing:", (screenMid - 70), 200);
        // <song> betterBrush.drawString(main.getSong(), (screenMid - 70), 210);
        }
    }
    
    
    // drawing pieces and such
    private void build(PieceFactory type, int x, int y, Graphics betterBrush) {
        build(type.getFillColor(), x, y, betterBrush);
    }

    private void build(Color marker, int x, int y, Graphics betterBrush) {
            betterBrush.setColor(marker);
            betterBrush.fillRect(x, y, _blockSize, _blockSize);
    }
}
