/*
 *                                               
 * 
 * -------------------------------------------------------------------------------------------------------
 *                                                  CONTROLS
 *--------------------------------------------------------------------------------------------------------
 *
 *       Average Gamer
 * ++++++++++++++++++++++++++
 * Up - rotate
 * Left - Move left one grid space
 * Right - Move Right one grid space
 * Down - Quick Drop
 * 
 * ++++++++++++++++++++++++++
 *      Weird Gamer
 * ++++++++++++++++++++++++++
 * J - rotate
 * L - Move left one grid space
 * K - Move Right one grid space
 * Space - Quick Drop
 * 
 * ++++++++++++++++++++++++++
 *          Game
 * ++++++++++++++++++++++++++  
 *  Enter|Return - Start Game
 *  F - pause/unpause Flash
 *  P - pause/unpause Game
 *  R - Restart Game[BUGGY!|Unstable]
 *  ++++++++++++++++++++++++++
 *      Music
 * ++++++++++++++++++++++++++
 *  A - Change Song
 *  M - STOP MUSIC
 * ++++++++++++++++++++++++++
 *      CHEAT
 * ++++++++++++++++++++++++++
 *  Q - MAD POINTS, Yo!
 * ++++++++++++++++++++++++++
 * 
 * -------------------------------------------------------------------------------------------------------
 * *******************************************************************************************************
 */
 
// Imports
import java.awt.Color;
import java.applet.Applet;
import java.net.URL;
import java.awt.BorderLayout;
import java.applet.AudioClip;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import java.util.Random;
import java.awt.event.KeyEvent;

// **************************

public class Main extends JFrame {
       
    private boolean gamePaused, gameEnded, gameRestarted; // Variables to set the state of the game 
    private Random _random; // Random variable for checkFlash and other like functionality
    private int _level, _drop, _score, _x, _y, _i, _currentRotation;
    private Timer _timer; // Milliseconds
    private PieceFactory _currentPiece, _next; // Holds the value for the current and next tiles displayed on screen    
    private float _difficulty; 
    private AudioClip _music; // Music file
    private boolean _checkFlash = true; 
    private int _block = 11; // columns
    private Bg _bg; // references
    private Cp _cp; // references
    
  
	/*
	// Uncomment this if you have audio files and have placed them in the same folder (dont forget to rename them and change the some names)
	

    private String[] song = {"D.wav",
                             "E.wav",
                             "F.wav",
                             "G.wav",
                             "H.wav",
                             "A.wav",
                             "B.wav",
                             "C.wav"}; // array to store sound files
                             
    private String[] songName = {"Tetris",
                                 "Scary Monsters and Nice Sprites",
                                 "Move Like Jagger",
                                 "FireFlies",
                                 "Billy Jean",
                                 "Techno -- Heaven",
                                 "Harlem Shake",
                                 "Madness"}; // array for the matching song titles
								 
	*/
        
    private Main() {
        
        super("Final Project");
        setSize(230,430);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
        //<song> URL fileLocale = Main.class.getResource("/Sound/"+song[_i]); // get the location of THIS file and concatinate with the song at position 'i'
        //<song> _music = Applet.newAudioClip(fileLocale);
        //<song> _music.play(); // activates the song
        
        setLayout(new BorderLayout());
        
        this._bg = new Bg(this);
        this._cp = new Cp(this);
        
        add(_bg, BorderLayout.CENTER);
        add(_cp, BorderLayout.NORTH);

        addKeyListener(new KeyListener());    
        
        pack();
        setVisible(true);    
    }
                
    
    private void isPlaying() {
        if(_bg.check(_currentPiece, _y, _x + 1, _currentRotation)) {
            _x++; 
        } else {
            _bg.ground(_currentPiece, _y, _x, _currentRotation);
            int lines = _bg.test();
                if(lines > 0) {
                    _score += 1000*lines; // Base score of 1000 for complete line multiplied by the number of lines
                    if(lines == 4){
                        _difficulty += 0.02; // make game harder
                        _score += 10000; // Bonus awarded for completing a 'TETRIS'
                    }
                }
                _difficulty += 0.01; // make game harder
                _timer.setSpeed(_difficulty); // apply difficulty
                _timer.tocs();
                _drop = 25;
                build();
        }       
    }
    
    private void run() {
                _random = new Random();
                _difficulty = 1f; // Sets the beginning difficulty level
                _timer = new Timer(_difficulty); // feeds the diffulty into timer
                gameRestarted = true;  
                _timer.stopTimer(true); //
                                        // At the menu screen 
            //    

                while(true) {
                    long start = System.nanoTime(); 
                    _timer.ticks(); // logic to determine if the board is running and if yes, drop piece at a constant rate 
                        if(_timer.whileRunning()) {
                            boardFlash();
                            isPlaying(); // activate game
                        }
                        if(0 < _drop) {
                            _drop--; // drop at constant rate
                        }
                    paint(); // repaint
                 }
        }
   
    private void paint() {
        _bg.repaint();
        _cp.repaint();
    }
    
     
    private class KeyListener extends KeyAdapter {
                    public void keyPressed(KeyEvent e) {
                           
                    switch(e.getKeyCode()) {      
                    // CHEAT CODE
                    case KeyEvent.VK_Q:
                            _score += 2000;
                        break;
                    // End game 
                    case KeyEvent.VK_R:
                            gameEnded = true;
                        break;                    
                    // Change song
					
					/* <song>
                    case KeyEvent.VK_A:
                            _music.stop();
                            _i++;
                            // Track looping logic
                            _i %= 7;                
    
                            URL fileLocale = Main.class.getResource("/Sound/"+song[_i]);
                            _music = Applet.newAudioClip(fileLocale);
                            _music.play();
                        break;  
					*/
					
					/* <song>
                    // Stop|Pause music
                    case KeyEvent.VK_M: 
                            _music.stop();
                        break;
					<song> */

						
                    // Shut off board flash
                    case KeyEvent.VK_F:
                            if(_checkFlash == true){
                                _checkFlash = false;
                            }else{
                                _checkFlash = true;  
                            }
                        break;                
                    // Move left
                    case KeyEvent.VK_LEFT: case KeyEvent.VK_J:
                        if(gamePaused == false){
                            if(_bg.check(_currentPiece, _y - 1, _x, _currentRotation)) {
                                _y--;
                            }
                        }
                        break;                
                    // Move right
                    case KeyEvent.VK_RIGHT: case KeyEvent.VK_L:
                        if(gamePaused == false){
                            if(_bg.check(_currentPiece, _y + 1, _x, _currentRotation)) {
                                _y++;
                            }
                        }
                        break;                    
                    // Quick drop
                    case KeyEvent.VK_DOWN: case KeyEvent.VK_SPACE:
                        if(gamePaused == false){
                            if(_drop == 0){
                                _timer.setSpeed(9999); 
                            }
                        }
                        break;                
                    // Rotate piece    
                    case KeyEvent.VK_UP: case KeyEvent.VK_K:
                        if(gamePaused == false) {
                            rotateCurrentPiece((_currentRotation + 1)%4);
                        }
                        break;                    
                    // Pause game
                    case KeyEvent.VK_P:
                        if(gameEnded == false){
                            if(gamePaused == false){
                                if(gameRestarted == false){
                                    gamePaused = true;
                                    _timer.stopTimer(gamePaused);
                                }
                        }else{
                             gamePaused = false;
                             _timer.stopTimer(gamePaused);
                        }
                        }
                        break;                    
                    // Starts game
                    case KeyEvent.VK_ENTER:
                        if((gameEnded)||(gameRestarted)){
                            resetGame();
                        }
                        break;
                    }
                }
            }
    
    
    private void boardFlash(){
                    if(_checkFlash == true){
                        // pretty flashing colors!
                            
                            Random rand = new Random();
                            // RGB Colors
                            float red = rand.nextFloat();
                            float green = rand.nextFloat();
                            float blue = rand.nextFloat();
                            
                            _bg.setBackground(new Color(red, green, blue));
                            
                            // Use this for preset colors (No chance to get {red, green, blue} series CAN CAUSE SEIZURES)
                            /*
                            int ticker = (int) System.currentTimeMillis() % 5;
                            
                            switch(ticker){
                                case 0:
                                    _bg.setBackground(new Color(200,   0,   0));
                                break;
                                case 1:
                                    _bg.setBackground(new Color(100,   100,   0));
                                break;                                
                                case 2:
                                    _bg.setBackground(new Color(0,   200,   0)); 
                                break;
                                case 3:
                                    _bg.setBackground(new Color(0,   100,   100));
                                break;                                
                                case 4:
                                    _bg.setBackground(new Color(0,   0,   200)); 
                                break;    
                                case 5:
                                    _bg.setBackground(new Color(100,   0,   100)); 
                                break;   
                                default:
                                    // NONE
                                break;
                            }
                            */
                            //*********************************** END (Flashing Colors) ****************************************
                        }else{
                            _bg.setBackground(Color.BLACK);
                        }
    }
    
     // ******************************************* RESET *******************************************
    
       private void resetGame() {
 
        _level = 1;// starting level
        _score = 0;// reset score
        _difficulty = (float) 1.0;
        _next = new PieceFactory();//get the next piece to be displayed
        gameRestarted = false;// logic booleans
        gameEnded = false; // logic booleans        
        _bg.test();// check if any line complete
        _timer.tocs();
        _timer.setSpeed(_difficulty);
        build();
    }
        
    // *************************************** CREATE GAME PIECE ********************************************
    
    private void build() {    
        _currentPiece = _next;
        _y = 5;
        _x = 0;
 
        _currentRotation = 0;
        _next = new PieceFactory();
        
            if(!_bg.check(_currentPiece, _y, _x, _currentRotation)) {
                this.gameEnded = true;
                _timer.stopTimer(true);
                _music.stop();
            }       
    }
    
    // ****************************************************************************************************

    private void rotateCurrentPiece(int angle) {
        // save prev locale
        int newYLocation = _y;
        int newXLocation = _x;
        
        // Get all four corners and implements logic to test and rotate object
        int left = _currentPiece.screenMinX(angle);
        int right = _currentPiece.screenMaxX(angle);
        int top = _currentPiece.screenMaxY(angle);
        int bottom = _currentPiece.screenMinY(angle);
        
        if(-left > _y) {
            newYLocation -= _y - left;
        } else if(_y + _currentPiece.getHeight() - right >= _block) {
            newYLocation -= (_y + _currentPiece.getHeight() - right) - _block + 1;
        }
        
        if(-top > _x) {
            newXLocation -= _x - top;
        } else if(((_x + _currentPiece.getHeight()) - bottom) >= 17) {
            newXLocation -= (_x + _currentPiece.getHeight() - bottom) - 17 + 1;
        }
        
        if(_bg.check(_currentPiece, newYLocation, newXLocation, angle)) {
            _currentRotation = angle;
            _x = newXLocation;
            _y = newYLocation;
        }
    }
    
    // ************************************** GAME STATE ********************************************
    
    public boolean gamePaused() {
        return gamePaused;
    }
   
    public boolean gameRestarted() {
        return gameRestarted;
    }
    
    public boolean gameEnded() {
        return gameEnded;
    }
    

    
    // ************************************** GET METHODS *******************************************
    
    public int getScore() {
        return _score;
    }
    
    public int getX() {
        return _x;
    }
    
    public PieceFactory getBlock() {
        return _currentPiece;
    }
    
    public PieceFactory getNextTile() {
        return _next;
    }
    
    public int getY() {
        return _y;
    }
        
    public int getCurrAngle() {
        return _currentRotation;
    }
    
    
    /* <song>
    public String getSong(){
        return songName[_i];
    }
    */
    
    public static void main(String[] args) {
        Main tetris = new Main();
        tetris.run();
    }

    
}
