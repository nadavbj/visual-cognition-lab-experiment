package nl.ru.ai.experimentserver;

import javax.swing.*;
import java.awt.*;
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
showMessage("המתן לתגובת השחקן השני");
                clicked=true;

            }
        });
    getContentPane().add(startBtn);
    setLocation(x,y);
    setSize(250,150);
    setAlwaysOnTop(true);
    setVisible(true);
    }

    public void showMessage(String messge) {
        getContentPane().removeAll();
        JLabel waitForOtherPlayerLabel=new JLabel(messge);
        waitForOtherPlayerLabel.setFont(new Font("david",Font.BOLD,20));
        getContentPane().add(waitForOtherPlayerLabel);
        waitForOtherPlayerLabel.setVisible(true);
        // Works better than repaint()
        setSize(249,150);
        setSize(250,150);
            }
}
