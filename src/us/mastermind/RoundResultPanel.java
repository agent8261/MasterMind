package us.mastermind;

import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JPanel;

import us.mastermind.Code.Color;
import us.mastermind.Code.Pegs;
import us.mastermind.Code.Result;

public class RoundResultPanel extends JPanel
{
  private static final long serialVersionUID = -2211270132689936714L;
  
  FlowLayout layout = new FlowLayout();
  Label lblRound = new Label("Round ##"), 
        lblCodePeg1 = new Label("None"), 
        lblCodePeg2 = new Label("None"), 
        lblCodePeg3 = new Label("None"), 
        lblCodePeg4 = new Label("None"), 
        lblResult = new Label("Result");
  
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  
  public RoundResultPanel()
  {
    setLayout(layout);
    add(lblRound);
    add(lblCodePeg1);
    add(lblCodePeg2);
    add(lblCodePeg3);
    add(lblCodePeg4);
    add(lblResult);
  }
  
  // ---------------------------------------------------------------------------
  
  public void setRoundNumber(int roundNumber)
  {
    lblRound.setText("Round " + roundNumber);
  }
  
  public void reset()
  {
    lblCodePeg1.setText("None");
    lblCodePeg2.setText("None");
    lblCodePeg3.setText("None");
    lblCodePeg4.setText("None");
    lblResult.setText("Result");
  }
  
  public void setResult(Pegs guess, Result r)
  {
    if((r == null) || (guess == null))
    { return; }
    
    lblCodePeg1.setText(Color.toString(guess.colors[0]));
    lblCodePeg2.setText(Color.toString(guess.colors[1]));
    lblCodePeg3.setText(Color.toString(guess.colors[2]));
    lblCodePeg4.setText(Color.toString(guess.colors[3]));
    
    lblResult.setText(String.format("%d Position and Color\n%d color only", 
      r.numCorrectPos, r.numCorrectColor));
  }
  // ---------------------------------------------------------------------------
}
