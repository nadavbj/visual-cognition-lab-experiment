package nl.ru.ai.experimentserver;

import javax.swing.*;

public class ShowRoundSummaryFrame extends JFrame {
    public ShowRoundSummaryFrame(String message, int x, int y){
        //disable exit button
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        JLabel messageLable=new JLabel(message);
        getContentPane().add(messageLable);
        setLocation(x,y);
        setSize(300,100);
        setVisible(true);
        setAlwaysOnTop(true);
    }
}
