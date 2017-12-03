import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/* File: View.java
* Author: Hunter Smith
* Date: 12/03/17
* Purpose: In addition to the main method and a constructor to build the GUI, the class contains
* handlers for the Compute button click and window closing.
*/

public class View {

    //Class Fields of GUI Components
    private JFrame frame = new JFrame("Project 3");
    private JPanel panel = new JPanel();
    private JButton computeButton = new JButton("Compute");
    private ButtonGroup radGroup = new ButtonGroup();
    private JRadioButton itButton = new JRadioButton("Iterative");
    private JRadioButton reButton = new JRadioButton("computeRecursive");
    private JTextField inputText = new JTextField();
    private JTextField outputText = new JTextField();
    private JTextField efficiencyText = new JTextField();
    private JLabel label1 = new JLabel("Enter n:");
    private JLabel label2 = new JLabel("Result:");
    private JLabel label3 = new JLabel("Efficiency:");
    private JLabel blank0 = new JLabel("");
    private JLabel blank1 = new JLabel("");
    private JLabel blank2 = new JLabel("");

    /* Constructor */
    private View(){

        //Panel Setup, would have used GridBag but this was shorter/more condensed
        panel.setLayout(new GridLayout(0,2,5,5));
        panel.add(blank1);
        panel.add(itButton);
        panel.add(blank0);
        panel.add(reButton);
        panel.add(label1);
        panel.add(inputText);
        panel.add(blank2);
        panel.add(computeButton);
        panel.add(label2);
        panel.add(outputText);
        panel.add(label3);
        panel.add(efficiencyText);

        //TextFields Edibility
        outputText.setEditable(false);
        efficiencyText.setEditable(false);

        //RadioGroup Setup
        radGroup.add(itButton);
        radGroup.add(reButton);
        itButton.setSelected(true);

        //Handler setup
        frame.addWindowListener( new CloseApp());
        computeButton.addActionListener(e -> buttonClick());

        //Frame Properties
        frame.add(panel);
        frame.setSize(350,200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    /* On computeButton click, determines whether to pass user input(inputText) to the iterative or recursive sequence
     * methods and place result in the outputText/efficiencyText fields Otherwise, if the user input couldn't be parsed
     * to int or is invalid it receives the value -1 and resets the textFields . If stack overflow displays message*/
    private void buttonClick(){
        if (getInput() == -1) { //invalid input was found
            inputText.setText("");
            outputText.setText("");
            efficiencyText.setText("");
        }
        else if (itButton.isSelected()) { //pass to iterative method
            outputText.setText(
                    String.valueOf(Sequence.computeIterative(getInput())));
            efficiencyText.setText(
                    String.valueOf(Sequence.getEfficiency())); //gets&reset efficiency token.
        }
        else {
            outputText.setText( //pass to recursive method
                    String.valueOf(Sequence.computeRecursive(getInput())));
            efficiencyText.setText(
                    String.valueOf(Sequence.getEfficiency())); //gets&reset efficiency token.
        }
    }


    /* Retrieves and parses user input(inputText field) and provides exception checking.
     * If an exception is found or value less than 0 returns sentinel value -1 */
    private long getInput(){
        try {
            long input = Long.parseLong(inputText.getText());
            if (input < 0){
                throw new NumberFormatException();
            } else {
             return input;
            }
        }catch (NumberFormatException ex){ //if caught display msg and return sentinel value
            JOptionPane.showMessageDialog(frame,"Invalid Input");
        }
        return -1; //Only reached if exception caught
    }


    /* Main Method */
    public static void main(String[] args) {
        View view = new View();
    }


    /*When the window is closed, computes efficiency values of terms 0 to 10. The results are written to a myfile.txt.
     * Each line of myfile contains the value of the term, the efficiency of the iterative method and the efficiency
     * of the recursive method separated by commas so as to allow opening with excel. */
    public class CloseApp extends WindowAdapter{

        @Override
        public void windowClosing(WindowEvent e) {
            //create StringBuilder to append results
            StringBuilder sb = new StringBuilder();
            sb.append("Value, Iterative Efficiency, computeRecursive Efficiency\n"); //file header
            for (int i = 0; i < 11; i++) { //iterates through n 1-10
                sb.append(String.valueOf(Sequence.computeIterative(i))).append(", ");//
                sb.append(String.valueOf(Sequence.getEfficiency())).append(", ");
                Sequence.computeRecursive(i); //called to get efficiency but not recorded itself
                sb.append(String.valueOf(Sequence.getEfficiency())).append("\n");
            }
            // Writes file to /src/myFile.txt
            try (BufferedWriter br = new BufferedWriter(new FileWriter("myFile.txt")))  {
                br.write(sb.toString());
            }
            catch (IOException ex){
                JOptionPane.showMessageDialog(null,"Error Writing File");
                System.exit(1);
            }
        }
    }
}
