package us.mastermind;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class GuessControlPanel extends JPanel
{
  private static final long serialVersionUID = 2028750821090845736L;
  static final String colors [] = 
    {"Red", "Blue", "Yellow", "Green", "White", "Orange"};
  
  FlowLayout layout = new FlowLayout();
  Label lblHelp = new Label("Guess the Code:");
  JComboBox<String> jcbPosition1 = new JComboBox<String>(colors),
                    jcbPosition2 = new JComboBox<String>(colors),
                    jcbPosition3 = new JComboBox<String>(colors),
                    jcbPosition4 = new JComboBox<String>(colors);
  Button btnSubmit = new Button("Submit");
  
  // ---------------------------------------------------------------------------
  // ---------------------------------------------------------------------------
  
  public GuessControlPanel()
  {
    setLayout(layout);
    add(lblHelp);
    add(jcbPosition1);
    add(jcbPosition2);
    add(jcbPosition3);
    add(jcbPosition4);
    add(btnSubmit);
  }
  
  Code.Pegs getGuess()
  {
    Code.Pegs guess = new Code.Pegs();
    guess.colors[0] = getColorFromComboBox(jcbPosition1);
    guess.colors[1] = getColorFromComboBox(jcbPosition2);
    guess.colors[2] = getColorFromComboBox(jcbPosition3);
    guess.colors[3] = getColorFromComboBox(jcbPosition4);
    return guess;
  }
  
  Code.Color getColorFromComboBox(JComboBox<String> jcb)
  {
    String s = (String)jcb.getSelectedItem();
    assert(s != null);
    switch(s)
    {
      case "Red": return Code.Color.RED; 
      case "Blue": return Code.Color.BLUE;
      case "Yellow": return Code.Color.YELLOW;
      case "Green": return Code.Color.GREEN;
      case "White": return Code.Color.WHITE;
      case "Orange": return Code.Color.ORANGE;
      default: return null;
    }
  }
  
  // ---------------------------------------------------------------------------
}
