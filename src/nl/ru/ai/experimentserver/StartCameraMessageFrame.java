package nl.ru.ai.experimentserver;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartCameraMessageFrame extends JFrame {
    private JButton startBtn;
public boolean clicked=false;
    public StartCameraMessageFrame(int x,int y) {
        //disable exit button
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        startBtn=new JButton("הקש על מנת להתחיל לשחק");
        startBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getContentPane().remove(startBtn);
                JLabel waitForOtherPlayerLabel=new JLabel("המתן לתגובת השחקן השני");
                getContentPane().add(waitForOtherPlayerLabel);
                waitForOtherPlayerLabel.setVisible(true);
                clicked=true;
                // Works better than repaint()
                setSize(249,150);
            }
        });
    getContentPane().add(startBtn);
    setLocation(x,y);
    setSize(250,150);
    setAlwaysOnTop(true);
    setVisible(true);
    }
}
