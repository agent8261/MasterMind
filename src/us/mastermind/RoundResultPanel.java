package us.mastermind;

import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JPanel;

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
  
  // ---------------------------------------------------------------------------
}
