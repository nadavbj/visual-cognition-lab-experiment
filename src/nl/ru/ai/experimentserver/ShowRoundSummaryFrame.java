package nl.ru.ai.experimentserver;

import javax.swing.*;
import java.awt.*;

public class ShowRoundSummaryFrame extends JFrame {
    public ShowRoundSummaryFrame(String message, int x, int y){
        super();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


        JLabel messageLable=new JLabel(message);
        messageLable.setFont(new Font("david",Font.BOLD,24));
        getContentPane().add(messageLable);
        setLocation(x,y);
        setSize(700,100);
        setVisible(true);
        setAlwaysOnTop(true);
    }
}
