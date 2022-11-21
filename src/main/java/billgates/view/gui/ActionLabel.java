package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte
 */
public class ActionLabel extends JLabel {

    public static final int DEFAULT_FONT_SIZE = 13;
    public static final Font DEFAULT_FONT = new FontSettings(DEFAULT_FONT_SIZE);

    public static final Color DEFAULT_TEXT_COLOR = Color.GRAY;

    public ActionLabel(String text){
        super(text);
        this.setFont(DEFAULT_FONT);
        this.setForeground(DEFAULT_TEXT_COLOR);
    }
}
