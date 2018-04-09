package nl.ru.ai.experimentserver;

import javax.swing.*;

public class ShowRoundSummary extends JFrame {
    public ShowRoundSummary(String message,int x,int y,int time){
        JLabel messageLable=new JLabel(message);
        getContentPane().add(messageLable);
        setLocation(x,y);
        setSize(300,100);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setVisible(true);
        dispose();
    }
}
