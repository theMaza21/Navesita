
package state;

import java.awt.Graphics;
import java.util.ArrayList;

import state.Constants;
import grafipx.Assests;
import UI.Action;
import UI.Button;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuState extends State{

 private ArrayList<Button> buttons;
	
	public MenuState() {
		buttons = new ArrayList<Button>();
		
		buttons.add(new Button(
				Assests.greyBtn,
				Assests.blueBtn,
				Constants.Windth / 2 - Assests.greyBtn.getWidth()/2,
				Constants.Height / 2 - Assests.greyBtn.getHeight()*2,
				Constants.PLAY,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new GameState());
					}
				}
				));
		
		buttons.add(new Button(
				Assests.greyBtn,
				Assests.blueBtn,
				Constants.Windth / 2 - Assests.greyBtn.getWidth()/2,
				Constants.Height / 2 + Assests.greyBtn.getHeight()*2 ,
				Constants.EXIT,
				new Action() {
					@Override
					public void doAction() {
						System.exit(0);
					}
				}
				));
                buttons.add(new Button(
				Assests.greyBtn,
				Assests.blueBtn,
				Constants.Windth / 2 - Assests.greyBtn.getWidth()/2,
				Constants.Height / 2,
				Constants.HIGH_SCORES,
				new Action() {
					@Override
					public void doAction() {
                                            try {
                                                State.changeState(new ScoreState());
                                            } catch (FileNotFoundException ex) {
                                                Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
                                            }
					}
				}
				));
		
	}
	
	
	@Override
	public void update() {
		for(Button b: buttons) {
			b.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		for(Button b: buttons) {
			b.draw(g);
		}
	}
    
}
