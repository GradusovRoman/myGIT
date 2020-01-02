package Xokyopo.saper;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonRightClickListener implements MouseListener {
    private GameCell gameCell;

    public ButtonRightClickListener(GameCell _gameCell){
        this.gameCell = _gameCell;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) && this.gameCell.isEnabled()){
            String text = (this.gameCell.getText().equals(Character.toString(this.gameCell.getAlert()))) ? "" : Character.toString(this.gameCell.getAlert());
            this.gameCell.setText(text);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
