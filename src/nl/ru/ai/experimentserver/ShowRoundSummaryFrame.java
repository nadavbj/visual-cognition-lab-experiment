package nl.ru.ai.experimentserver;

import javax.swing.*;

public class ShowRoundSummaryFrame extends JFrame {
    public ShowRoundSummaryFrame(String message, int x, int y){
        super();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


        JLabel messageLable=new JLabel(message);
        getContentPane().add(messageLable);
        setLocation(x,y);
        setSize(500,100);
        setVisible(true);
        setAlwaysOnTop(true);
    }
}
