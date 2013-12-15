package us.mastermind;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewMasterMind extends JPanel
{
  private static final long serialVersionUID = 548894285026742559L;
  static final String coreFrameTitleTxt = "Master Mind";
  static final int NUM_ROUNDS = 12;
  
  JPanel roundPanel = new JPanel();
  BorderLayout layout = new BorderLayout();
  BoxLayout roundLayout = new BoxLayout(roundPanel, BoxLayout.PAGE_AXIS);
  RoundResultPanel roundResultPanels[] = new RoundResultPanel[NUM_ROUNDS];
  GuessControlPanel guessControlPanel = new GuessControlPanel();
  
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  
  public ViewMasterMind()
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
  }
  
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  
  static class Runner implements Runnable
  {
    @Override
    public void run()
    {
      JFrame coreFrame = new JFrame(coreFrameTitleTxt);
      coreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      ViewMasterMind view = new ViewMasterMind();
      coreFrame.add(view);
      coreFrame.pack();
      coreFrame.setVisible(true);
    }
    
  }
  // ---------------------------------------------------------------------------
}
