package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

public class ActionButton extends JButton {
    public static final int DEFAULT_WIDTH = 170;
    public static final int DEFAULT_HEIGHT = 50;
    public static final int DEFAULT_FONT_SIZE = 16;
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(240, 140, 170);
    public static final Color DEFAULT_TEXT_COLOR = Color.WHITE;
    public static final Font DEFAULT_FONT = new Font("Modern No. 20", Font.BOLD, DEFAULT_FONT_SIZE);
    public static final int DEFAULT_BORDER_THICKNESS = DEFAULT_HEIGHT / 7;
    public static final int DEFAULT_IMAGE_HEIGHT = DEFAULT_HEIGHT - DEFAULT_BORDER_THICKNESS;

    public ActionButton(String text){
        // Set the text of the icon
        super(text);

        // Set font and colors
        this.setFont(DEFAULT_FONT);
        this.setForeground(DEFAULT_TEXT_COLOR);
        setBackground(DEFAULT_BACKGROUND_COLOR);

        // Set the size of the button
        this.setMaximumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    public ActionButton(String text, ImageIcon icon){
        // Get the original icon's size
        double originIconWidth = icon.getIconWidth();
        double originIconHeight = icon.getIconHeight();

        // Calculate the ratio of the icon's width and height
        double ratio = originIconWidth / originIconHeight;

        // Resize the icon according to the ratio
        Image resizedBackImage = icon.getImage().getScaledInstance((int) (DEFAULT_IMAGE_HEIGHT * ratio),
                DEFAULT_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedBackIcon = new ImageIcon(resizedBackImage);

        // Set the icon
        this.setIcon(resizedBackIcon);

        // Set the text of the icon
        this.setText(text);

        // Set font and colors
        this.setFont(DEFAULT_FONT);
        this.setForeground(DEFAULT_TEXT_COLOR);
        setBackground(DEFAULT_BACKGROUND_COLOR);

        // Set the maximum size of the button
        this.setMaximumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

}
