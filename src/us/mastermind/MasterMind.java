package us.mastermind;

import java.util.Scanner;
import java.util.regex.Pattern;

import us.mastermind.Code.*;

public class MasterMind
{
  private static final int NUM_PEGS = 4;
  private static final int NUM_TURNS = 2;
  
  static final String PEG_REG_EX = "[rbygwo]";
  static final Pattern PEG_PATTERN = Pattern.compile(PEG_REG_EX);
  
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  
  public static void main(String [] args)
  {
    ViewMasterMind.Runner runner = new ViewMasterMind.Runner();
    javax.swing.SwingUtilities.invokeLater(runner);
    //MasterMind m = new MasterMind();
    //m.startGame();
  }
  
  // ---------------------------------------------------------------------------
  
  void startGame()
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
