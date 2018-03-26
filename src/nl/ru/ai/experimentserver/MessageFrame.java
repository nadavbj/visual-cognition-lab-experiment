package nl.ru.ai.experimentserver;

import javax.swing.*;
import java.awt.*;

public class MessageFrame extends JFrame {
    String message;
    public void paint(Graphics g){
        g.drawString(message, 10, 10);
    }
public MessageFrame(String message){
        this.message=message;
}
}
