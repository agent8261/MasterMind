package us.mastermind.view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import us.mastermind.MasterMind_AppController;
import us.mastermind.RoundResultPanel;
import us.mastermind.Code.Pegs;
import us.mastermind.Code.Result;

public class ViewMasterMind extends JPanel
{
  private static final long serialVersionUID = 548894285026742559L;
  
  static final int NUM_ROUNDS = 12;
  
  JPanel roundPanel = new JPanel();
  BorderLayout layout = new BorderLayout();
  BoxLayout roundLayout = new BoxLayout(roundPanel, BoxLayout.PAGE_AXIS);
  RoundResultPanel roundResultPanels[] = new RoundResultPanel[NUM_ROUNDS];
  public final GuessControlPanel guessControlPanel = new GuessControlPanel();
  
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  
  public ViewMasterMind(MasterMind_AppController appCtrl)
  {
    super();
    setLayout(layout);
    roundPanel.setLayout(roundLayout);
    add(roundPanel, BorderLayout.CENTER);
    
    for(int i=0; i < NUM_ROUNDS; i++)
    {
      roundResultPanels[i] = new RoundResultPanel();
      roundResultPanels[i].setRoundNumber(i + 1);
      roundPanel.add(roundResultPanels[i]);
    }
    
    add(guessControlPanel, BorderLayout.SOUTH);
    guessControlPanel.setActionListener(appCtrl.actListener);
    
  }
  
  public void setResult(int round, Pegs guess, Result r)
  {
    if((round < 0) || (round >= NUM_ROUNDS))
    { return; }
    
    roundResultPanels[round].setResult(guess, r);
  }
  
  public void reset()
  {
    for(int i=0; i < NUM_ROUNDS; i++)
    { roundResultPanels[i].reset(); }
  }
  
  // ---------------------------------------------------------------------------
}
