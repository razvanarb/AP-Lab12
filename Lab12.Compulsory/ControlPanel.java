package Lab12;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * ControlPanel class that contains the control panel of the app..
 */
public class ControlPanel extends JPanel {

    private String[] swingComponents = {"JButton", "JLabel", "JTextField", "JPasswordField", "JCheckBox", "JRadioButton", "JSlider", "JComboBox", "JProgressBar", "JToggleButton", "JList", "JTabbedPane", "JTextArea", "JTextPane"};
    private JLabel classNameLabel = new JLabel("Desired component: ");
    private JLabel componentText = new JLabel("Component text: ");
    private JTextField componentTTF = new JTextField(10);
    private JComboBox<String> classList = new JComboBox<>(swingComponents);
    private JButton addClass = new JButton("Add component");

    /**
     * The constructor that initializes all the components of the control panel
     */
    public ControlPanel() {
        this.setBorder(BorderFactory.createTitledBorder("Control Panel"));
        add(classNameLabel);
        add(classList);
        add(componentText);
        add(componentTTF);
        add(addClass);
        componentText.setVisible(false);
        componentTTF.setVisible(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 10));
    }

    /**
     * Tests whether the given object has a constructor with a parameter of type string.
     *
     * @param obj
     * @return
     */
    public boolean hasText(Object obj) {
        try {
            Class clazz = Class.forName("javax.swing." + obj);
            Constructor[] constructors = clazz.getConstructors();
            if (constructors.length > 0) {
                for (int i = 0; i < constructors.length; i++) {
                    if (Arrays.toString(constructors[i].getParameterTypes()).contains("java.lang.String"))
                        return true;
                }
            }
            return false;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public JLabel getClassNameLabel() {
        return classNameLabel;
    }

    public JLabel getComponentText() {
        return componentText;
    }

    public JTextField getComponentTTF() {
        return componentTTF;
    }

    public JComboBox<String> getClassList() {
        return classList;
    }

    public JButton getAddClass() {
        return addClass;
    }

}

