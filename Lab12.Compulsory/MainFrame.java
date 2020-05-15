package Lab12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

/**
 * MainFrame class that contains the main function and the mainframe of the application.
 */
public class MainFrame extends JFrame {

    private int coorX, coorY; /* the coordinates at which the component will be placed */

    public static void main(String[] args) {

        new MainFrame().setVisible(true);
    }

    private static ControlPanel controlPanel;
    private static DesignPanel designPanel;

    public MainFrame() {
        super("Lab12 Swing App");
        init();
    }

    private void init() {
        /**
         * Initializes all the elements of the app including the buttons from the control panel
         */
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        controlPanel = new ControlPanel();
        designPanel = new DesignPanel();
        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.CENTER);
        controlPanel.getAddClass().addActionListener(actionListener);
        controlPanel.getClassList().addActionListener(actionListener);
        Insets insets = MainFrame.designPanel.getInsets(); /* the variable retains the designPanel margins. */
        coorX = insets.left;
        coorY = insets.top;
    }

    /**
     * Action listener for all the functions of the app.
     */
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String chosenComponent = (String) MainFrame.controlPanel.getClassList().getSelectedItem(); /* the chosen component from the drop down menu */
            String componentText = controlPanel.getComponentTTF().getText(); /* the text for the component if available */

            Class clazz = null;
            try {
                clazz = Class.forName("javax.swing." + chosenComponent);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            if (e.getSource() == controlPanel.getClassList()) {
                if (controlPanel.hasText(chosenComponent)) {
                    controlPanel.getComponentText().setVisible(true);
                    controlPanel.getComponentTTF().setVisible(true);
                } else {
                    controlPanel.getComponentText().setVisible(false);
                    controlPanel.getComponentTTF().setVisible(false);
                }

            }

            if (e.getSource() == controlPanel.getAddClass()) {
                try {
                    if (controlPanel.hasText(chosenComponent)) {

                        JComponent component = (JComponent) clazz.getConstructor(String.class).newInstance(componentText);
                        MainFrame.designPanel.add(component);
                        component.setBounds(coorX, coorY, 100, 50); /* after the component is added, */
                        coorX += 100; /*the coordinates change so that the next component doesn't overlap with the previous one. */
                        coorY += 50;
                    } else {

                        JComponent component = (JComponent) clazz.getConstructor().newInstance();
                        MainFrame.designPanel.add(component);
                        component.setBounds(coorX, coorY, 100, 50);
                        coorX += 100;
                        coorY += 50;
                    }


                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
            }

        }
    };
}
