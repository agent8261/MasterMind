package us.mastermind;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import us.mastermind.Code.*;
import us.mastermind.view.GameMenu;
import us.mastermind.view.ViewMasterMind;

public class MasterMind_AppController
{
  
  public static void main(String [] args)
  {
    final MasterMind_AppController ctrl = new MasterMind_AppController();
    Runnable viewRunner = new Runnable()
    {
      @Override
      public void run()
      {
        ctrl.coreFrame = new JFrame(coreFrameTitleTxt);
        ctrl.coreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ctrl.gameMenu = new GameMenu();
        ctrl.coreFrame.setJMenuBar(ctrl.gameMenu.menubar);
        ctrl.gameMenu.menuItem.addActionListener(ctrl.actListener);
        
        ctrl.viewMasterMind = new ViewMasterMind(ctrl);
        ctrl.coreFrame.add(ctrl.viewMasterMind);
        ctrl.coreFrame.pack();
        ctrl.coreFrame.setVisible(true);
      }
    };
    
    javax.swing.SwingUtilities.invokeLater(viewRunner);
  }
  
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  
  private static final int NUM_PEGS = 4;
  private static final int NUM_TURNS = 2;
  
  static final String coreFrameTitleTxt = "Master Mind";
  
  public static final String SUBMIT_GUESS = "submitGuess";
  public static final String NEW_GAME = "newGame";
  
  static final String PEG_REG_EX = "[rbygwo]";
  static final Pattern PEG_PATTERN = Pattern.compile(PEG_REG_EX);
  
  boolean hasGameStarted = false;
  
  Code currentCode = null;
  int currentTurn = -1;
  
  JFrame coreFrame = null;
  ViewMasterMind viewMasterMind = null;
  GameMenu gameMenu = null;
  
  private MasterMind_AppController(){}
  
  public final ActionListener actListener = new ActionListener()
  { @Override
    public void actionPerformed(ActionEvent e)
    {
      final String s = e.getActionCommand();
      if(s == null)
      { return; }
      
      switch(s)
      {
        case SUBMIT_GUESS:
          evaluateGuess();
          break;
        case NEW_GAME:
          startGame();
          break;
        default:
          break;
      }
    }
  };
  
  void startGame()
  {
    hasGameStarted = true;
    currentCode = new Code();
    currentTurn = 0;
    viewMasterMind.reset();
  }
  
  void evaluateGuess()
  {
    if(!hasGameStarted)
    { return; }
    
    Pegs g = viewMasterMind.guessControlPanel.getGuess();
    Code.Result r = evalGuess(currentCode, g);
    viewMasterMind.setResult(currentTurn, g, r);
    ++currentTurn;
    
    if(r.numCorrectPos == NUM_PEGS)
    {
      JOptionPane.showMessageDialog(coreFrame, "You win!");
    }
    else
    {
      if(!(currentTurn < NUM_TURNS))
      {
        hasGameStarted = false;
        JOptionPane.showMessageDialog(coreFrame, 
          String.format("You Lose!\nThe correct code was %s", currentCode.code.toString()));
      }
      else
      {
        JOptionPane.showMessageDialog
        (coreFrame, 
         String.format("Try to guess again.\n%d guesses left", NUM_TURNS - (currentTurn + 1)));
      }
    }
  }
  
  // ---------------------------------------------------------------------------
  
  void oldstartGame()
  {
    Code code = new Code();
    boolean won = false;
    
    int i = 0;
    while(!won)
    {
      Pegs g = getGuess();
      Code.Result r = evalGuess(code, g);
      if(r.numCorrectPos == NUM_PEGS)
        won = true;
      else
        i++;
      
      if(!(i < NUM_TURNS))
        break;
      else
      {
        System.out.println("Try to guess again.");
        System.out.format("%d guesses left", NUM_TURNS - (i + 1));
      }
    }
    
    if(won)
      System.out.println("You win!");
    else
      System.out.println("You LOSE!!!!");
    System.out.println("The correct code was " + code.code.toString());
  }
  
  // ---------------------------------------------------------------------------
  
  Pegs getGuess()
  {
    @SuppressWarnings("resource")
    Scanner c = new Scanner(System.in);
    
    Code.Pegs guess = new Code.Pegs();
    System.out.println("Enter the first lettter of the color. Seperated by spaces.");
    System.out.println("[ (r)ed (b)lue (y)ellow, (g)reen (w)hite (o)range ]");
    System.out.print("Guess: ");
    
    for(int i = 0; i < NUM_PEGS; i++)
    {
      String s = c.next(PEG_PATTERN);
      char ch = s.charAt(0);
      System.out.format("[%d]: %c\n", i, ch);
      switch(ch)
      {
        case 'r' : guess.colors[i] = Color.RED; break;
        case 'b' : guess.colors[i] = Color.BLUE; break;
        case 'y' : guess.colors[i] = Color.YELLOW; break;
        case 'g' : guess.colors[i] = Color.GREEN; break;
        case 'w' : guess.colors[i] = Color.WHITE; break;
        case 'o' : guess.colors[i] = Color.ORANGE; break;
        default: assert(false); break;
      }
    }
    System.out.println("Your guess was: " + guess.toString());
    return guess;
  }
  
  Result evalGuess(Code correctCode, Pegs guess)
  {
    Result r = eval(correctCode, guess);
    System.out.format("%d pegs with correct position and color\n", r.numCorrectPos);
    System.out.format("%d pegs with correct color\n", r.numCorrectColor);
    return r;
  }
  
  // ---------------------------------------------------------------------------
  
  Result eval(Code correctCode, Pegs guess)
  {
    Code.Result r = new Code.Result();
    Pegs code = correctCode.code;
    Code.Stats stats = new Code.Stats();
    
    for(int i =0; i < NUM_PEGS; i++)
    {
      Color gColor = guess.colors[i];
      if(gColor == code.colors[i])
      {
        r.numCorrectPos++;
      }
      stats.update(gColor);
    }
    r.numCorrectColor = correctCode.stats.getNumCommonColors(stats);
    return r;
  }
  
  // ---------------------------------------------------------------------------
}
