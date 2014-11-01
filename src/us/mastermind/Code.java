package us.mastermind;

import java.util.Arrays;
import java.util.Random;

public class Code
{
  public static final int NUM_PEGS = 4;
  public static final int NUM_COLORS = 6;
  static final long RAND_SEED = 10;
  
  Pegs code = new Pegs();
  Stats stats = new Stats();
  
  static final Color [] allColors = 
    {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.WHITE, Color.ORANGE};
  
  // ---------------------------------------------------------------------------
  
  public Code()
  {
    Random r = new Random(RAND_SEED);
    for(int i = 0; i < NUM_PEGS; i++)
    { code.colors[i] = allColors[r.nextInt(NUM_COLORS)]; }
    stats.set(code);
  }
  
  // ===========================================================================
  // ===========================================================================
  
  public static enum Color
  {
    RED(0, 'r'), 
    BLUE(1, 'b'), 
    YELLOW(2, 'y'), 
    GREEN(3, 'g'), 
    WHITE(4, 'w'), 
    ORANGE(5, 'o'), 
    NONE(-1, '-');
    
    // ---------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    
    public final int num;
    private final char c;
    
    // ---------------------------------------------------------------------------
    
    Color(int number, char c)
    {
      this.num = number;
      this.c = c;
    }
    
    // ---------------------------------------------------------------------------
    
    public char toChar()
    {
      return c;
    }
    
    public static String toString(final Color c)
    {
      switch(c)
      {
        case RED:    return "Red";
        case BLUE:   return "Blue";
        case YELLOW: return "Yellow";
        case GREEN:  return "Green";
        case WHITE:  return "White";
        case ORANGE: return "Orange";
        default:     return null;
      }
    }
  } // Color
  
  // ===========================================================================
  // ===========================================================================
  
  public static class Pegs
  {
    public final Color [] colors = new Color[NUM_PEGS];
    
    // ---------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    
    public Pegs()
    {
      Arrays.fill(colors, Color.NONE);
    }
    
    // ---------------------------------------------------------------------------
    
    public String toString()
    {
      StringBuilder b = new StringBuilder();
      for(Color c: colors)
      {
        b.append(c.toChar());
        b.append(" ");
      }
      return b.toString();
    }
  } // Pegs
  
  // ===========================================================================
  // ===========================================================================
  
  public static class Stats
  {
    int [] totals;
    
    public Stats()
    {
      totals = new int[NUM_COLORS];
      Arrays.fill(totals, 0);
    }
    
    public void set(Pegs peg)
    {
      Arrays.fill(totals, 0);
      for(Color c: peg.colors)
      { update(c); }
    }
    
    public void update(Color color)
    {
      assert((color.num >= 0) && (color.num < NUM_COLORS));
      totals[color.num]++;
    }
    
    public int getNumCommonColors(Stats guess)
    {
      int correct = 0;
      for(int i =0; i < 6; i++)
      { correct += Math.min(totals[i], guess.totals[i]); }
      assert(correct >= 0 && correct <= NUM_PEGS);
      return correct;
    }
  } // Stats
  
  // ===========================================================================
  // ===========================================================================
  
  public static class Result
  {
    int numCorrectPos = 0;
    int numCorrectColor = 0;
  }
}
