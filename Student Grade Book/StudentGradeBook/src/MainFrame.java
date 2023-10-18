import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MainFrame extends JFrame {

    // Font variable for consistency and convenience

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    final private Font testFont = new Font("Helvetica", Font.BOLD, 40);

    // global scope variables

    JTextField tfFirstName, tfLastName, tfTest1, tfTest2, tfTest3, tfFinal, tfID;
    JLabel lbWelcome, lbTest1, lbTest2, lbTest3, lbFinal, lbID, lbAcknowledge, lbStudentCount;
    String outputBook = "Student Grade Book";
    int count = 0;
    String lastName="";

    public void initialize(){

        // Student info labels and textfields initialized and defaulted

        JLabel lbFirstName = new JLabel("Student's First Name");
        lbFirstName.setFont(mainFont);
        lbFirstName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbLastName = new JLabel("Student's Last Name");
        lbLastName.setFont(mainFont);     

        JLabel lbID = new JLabel("Student's ID");
        lbID.setFont(mainFont);

        tfFirstName = new JTextField();
        tfFirstName.setFont(mainFont);

        tfLastName = new JTextField();
        tfLastName.setFont(mainFont);

        tfID = new JTextField();
        tfID.setFont(mainFont);

        // Test Labels and text fields initialized and defaulted

        JLabel lbTest1 = new JLabel("Test 1");
        lbTest1.setFont(mainFont);

        JLabel lbTest2 = new JLabel("Test 2");
        lbTest2.setFont(mainFont);

        JLabel lbTest3 = new JLabel("Test 3");
        lbTest3.setFont(mainFont);

        JLabel lbFinal = new JLabel("Final");
        lbFinal.setFont(mainFont);
        lbFinal.setLayout(new GridBagLayout());

        tfTest1 = new JTextField();
        tfTest1.setFont(testFont);

        tfTest2 = new JTextField();
        tfTest2.setFont(testFont);

        tfTest3 = new JTextField();
        tfTest3.setFont(testFont);

        tfFinal = new JTextField();
        tfFinal.setFont(testFont);
  
        // Header & Footer Labels initialized and set to default language

        lbWelcome = new JLabel("Welcome to your Student Calculator!");
        lbWelcome.setFont(mainFont);

        lbAcknowledge = new JLabel("Please Enter All Student Information");
        lbAcknowledge.setFont(mainFont);
        
        lbStudentCount = new JLabel("Students Entered: " + count);
        lbStudentCount.setFont(mainFont);


        // wrapper class for better containment of elements
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BorderLayout());

        // formatting for the header

        JPanel header = new JPanel();
        header.setBorder(new EmptyBorder(10, 0, 10, 0));
        header.setLayout(new GridBagLayout());
        header.add(lbWelcome);

        // Formatting for the footer 

        JPanel footer = new JPanel();
        footer.setLayout(new GridLayout(2,1,10,20));
        footer.setBorder(new EmptyBorder(10, 20, 10, 0));
        footer.add(lbAcknowledge);
        footer.add(lbStudentCount);


        // Formatting and adding elements to the Student information field
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3,2,5,5));  //row, column, margin between components
        formPanel.setBorder(new EmptyBorder(10,10,10,10));
        formPanel.add(lbFirstName);
        formPanel.add(tfFirstName);
        formPanel.add(lbLastName);
        formPanel.add(tfLastName);
        formPanel.add(lbID);
        formPanel.add(tfID);

        // Formatting adding elements to the Test field 
        JPanel formPanel2 = new JPanel();
        formPanel2.setLayout(new GridLayout(2,4,10,20));
        formPanel2.setBorder(new EmptyBorder(60,10,70,10));
        formPanel2.setSize(100, 100);
        formPanel2.add(lbTest1);
        formPanel2.add(lbTest2);
        formPanel2.add(lbTest3);
        formPanel2.add(lbFinal);
        formPanel2.add(tfTest1);
        formPanel2.add(tfTest2);
        formPanel2.add(tfTest3);
        formPanel2.add(tfFinal);

        // Formatting for action buttons 
        JButton btnOK = new JButton("Save");
        btnOK.setFont(mainFont);
        btnOK.setMargin(new Insets(10, 0, 10, 0));

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(mainFont);
        btnClear.setMargin(new Insets(10, 0, 10, 0));

        JButton saveFile = new JButton("Print to File");
        saveFile.setFont(mainFont);
        saveFile.setMargin(new Insets(10,0,10,0));

        // Code for when the OK button is pushed
        btnOK.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub


                // Check to make sure all fields are completed
                if(tfFirstName.getText().equals("") || tfLastName.getText().equals("") || tfID.getText().equals("") || tfTest1.getText().equals("")||tfTest2.getText().equals("")|| tfTest3.getText().equals("") || tfFinal.getText().equals("")){
                        lbAcknowledge.setText("Please complete all fields before hitting Save");
                    
                }
                // runs the code for calculating students average
                else{
                    // inc student count for display
                    count ++;

                    // retrieve student information
                    String firstName = tfFirstName.getText();
                    String lastName = tfLastName.getText();
                    String studentID = tfID.getText();

                    // convert string entry to float so math can be applied
                    Float test1f = Float.parseFloat(tfTest1.getText());
                    Float test2f = Float.parseFloat(tfTest2.getText());
                    Float test3f = Float.parseFloat(tfTest3.getText());
                    Float finalf = Float.parseFloat(tfFinal.getText());

                    // Calculate the Students average
                    Float averagef = (test1f +test2f +test3f +finalf)/4;

                    // Update display for acknowledgement of student entry
                    lbAcknowledge.setText("Student: " +firstName + " "+ lastName + " with an average of " + averagef);
                    lbStudentCount.setText("Students Entered: " + count);

                    // Add new student to output variable to be used for full student body print out
                    outputBook +=  "\n" + lastName + ", " + firstName + " - " +studentID +" | Test 1: " + test1f + " Test 2: " + test2f+ " Test 3: " + test3f + " Final: " + finalf + " | Average: " + averagef;
                    
                    // Resets text fields for next entry
                    tfFirstName.setText("");
                    tfLastName.setText("");
                    tfID.setText("");
                    tfTest1.setText("");
                    tfTest2.setText("");
                    tfTest3.setText("");
                    tfFinal.setText("");
                }
             
                }
            
            
        });

        // Code for when Clear button is pushed
        btnClear.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                // Resets all text fields
               tfFirstName.setText("");
               tfLastName.setText("");
               tfID.setText("");
               tfTest1.setText("");
               tfTest2.setText("");
               tfTest3.setText("");
               tfFinal.setText("");

            }

        });

        // Code for when Print button is pushed
        saveFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                // Prints to file code
                PrintWriter outputStudents;
                try {
                    outputStudents = new PrintWriter(new FileOutputStream(new File("StudentsGrades.txt"), false));
                    outputStudents.println(outputBook);
                    outputStudents.close();

                    // reset variables and labels
                    lbAcknowledge.setText("Student Grade Book Saved!");
                    count = 0;
                    lbStudentCount.setText("Students Entered: " + count);


                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
         
            

        });



        // User interface edding elements, builds and formatting
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3, 5, 5));
        buttonsPanel.add(btnOK);
        buttonsPanel.add(btnClear);
        buttonsPanel.add(saveFile);

        wrapper.add(formPanel, BorderLayout.NORTH);
        wrapper.add(formPanel2,BorderLayout.CENTER);
        wrapper.add(buttonsPanel, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(wrapper, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);

        //  main Panel default settings

        setTitle("Student Grade Book");
        setSize(500,600);
        setMinimumSize(new Dimension(300,300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
    }
// Starting the program calling and establishing the mainframe
    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();
        myFrame.initialize();
   
    }
}

