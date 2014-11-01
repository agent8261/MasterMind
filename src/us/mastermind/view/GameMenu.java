package us.mastermind.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import us.mastermind.MasterMind_AppController;

public class GameMenu
{
  public final JMenuBar menubar = new JMenuBar();
  public final JMenu menu = new JMenu("MasterMind");
  public final JMenuItem menuItem = new JMenuItem("New Game");
  
  public GameMenu()
  {
    menubar.add(menu);
    menu.add(menuItem);
    menuItem.setActionCommand(MasterMind_AppController.NEW_GAME);
  }
}
