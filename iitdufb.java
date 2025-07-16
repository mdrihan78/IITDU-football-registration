import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class iitdufb {
     public static void main(String[] args) {
         JFrame frame = new JFrame("IIT DU Football Registration Form");
         frame.setSize(700,700);
         frame.setLayout(null);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         

         JLabel nameLabel = new JLabel("Name :");
         nameLabel.setBounds(30,20,100,25);
         frame.add(nameLabel);

         JTextField nameField = new JTextField();
         nameField.setBounds(150,20,300,25);
         frame.add(nameField);

         JLabel phonLabel = new JLabel("Phone :");
         phonLabel.setBounds(30,60,100,25);
         frame.add(phonLabel);
        
         JTextField phonField = new JTextField();
         phonField.setBounds(150,60,300,25);
         frame.add(phonField);

         JLabel emailLabel=new JLabel("Email :");
         emailLabel.setBounds(30,100,100,25);
         frame.add(emailLabel);

         JTextField emailField = new JTextField();
         emailField.setBounds(150,100,300,25);
         frame.add(emailField);

         JLabel addressLabel= new JLabel("Address :");
         addressLabel.setBounds(30,140,100,25);
         frame.add(addressLabel);

         JTextField addressField = new JTextField();
         addressField.setBounds(150,140,300,25);
         frame.add(addressField);

         JLabel genderLabel= new JLabel("Gender :");
         genderLabel.setBounds(30,180,100,25);
         frame.add(genderLabel);

         JRadioButton male = new JRadioButton("Male");
         male.setBounds(150,180,60,25);
        
         JRadioButton female = new JRadioButton("Female");
         female.setBounds(220,180,100,25);

         JRadioButton other = new JRadioButton("Other");
         other.setBounds(330,180,60,25);

         ButtonGroup gendeGroup = new ButtonGroup();
         gendeGroup.add(male);
         gendeGroup.add(female);
         gendeGroup.add(other);
         frame.add(genderLabel);
         frame.add(male);
         frame.add(female);
         frame.add(other);

         JLabel dobLabel = new JLabel("Date of Birth :");
         dobLabel.setBounds(30,220,100,25);
         String[] days = new String[31];
         for(int i=1;i<=31;i++){
            days[i-1]=String.valueOf(i);
         }
         String[] months ={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
         String[] years = new String[100];
         for(int i=1;i<=100;i++){
            years[i-1]=String.valueOf(2026-i);
         }

         JComboBox<String> dayBox = new JComboBox<>(days);
         dayBox.setBounds(150,220,60,25);

         JComboBox<String> monthBox= new JComboBox<>(months);
         monthBox.setBounds(220,220,60,25);

         JComboBox<String> yearBox = new JComboBox<>(years);
         yearBox.setBounds(290,220,60,25);
         
         frame.add(dobLabel);
         frame.add(dayBox);
         frame.add(monthBox);
         frame.add(yearBox);

        
         JLabel degreeLabel= new JLabel("Degree :");
         degreeLabel.setBounds(30,260,100,25);
         String[] degrees = {"BSSE","MSSE","MIT"};
         JComboBox<String> degreeBox = new JComboBox<>(degrees);
         degreeBox.setBounds(150,260,60,25);

         frame.add(degreeLabel);
         frame.add(degreeBox);

         JLabel fileLabel = new JLabel("Picture :");
         fileLabel.setBounds(30,300, 100,25);
         JButton fileButton = new JButton("Choose File");
        fileButton.setBounds(150,300, 100,25);
        JLabel filePathLabel = new JLabel();
        filePathLabel.setBounds(270,300,100,25);

        frame.add(fileLabel);
        frame.add(fileButton);
        frame.add(filePathLabel);

        final String[] selectedFilePath = {""};
        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                selectedFilePath[0] = file.getAbsolutePath();
                filePathLabel.setText(file.getName());
            }
        });

        JButton registrationButton = new JButton("Register");
        registrationButton.setBounds(300,340,100,25);
        frame.add(registrationButton);

        registrationButton.addActionListener(e -> {
          String name = nameField.getText();
          String phone = phonField.getText();
          String email = emailField.getText();
          String address = addressField.getText();
          String gender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : other.isSelected() ? "Others" : "Not selected";
          String dob =dayBox.getSelectedItem()+"-"+monthBox.getSelectedItem()+"-"+yearBox.getSelectedItem();
          String degree =(String) degreeBox.getSelectedItem();
          String photo = selectedFilePath[0].isEmpty() ? "Not Selected" :selectedFilePath[0];

          if(name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || gender.equals("Not selected") || degree.equals("Select")){
            JOptionPane.showMessageDialog(frame, "Please fill all fields !", "Error",JOptionPane.ERROR_MESSAGE);
          }
          else{
              try{
                FileWriter writer = new FileWriter("registration.txt",true);
                writer.write("Name :"+name+" \n");
                writer.write("Phone number : "+phone+" \n");
                writer.write("Email :"+email+" \n");
                writer.write("Address :"+address+" \n");
                writer.write("Gender :"+gender+" \n");
                writer.write("Date of Birth :"+dob+" \n");
                writer.write("Degree :"+degree+" \n");
                writer.write("Picture :"+photo+" \n");
                for(int i=0;i<30;i++){
                    writer.write("-");
                }
                writer.write("\n\n");
                writer.close();
                 
                 JOptionPane.showMessageDialog(frame, "Registration Saved Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                  
                    nameField.setText("");
                    emailField.setText("");
                    phonField.setText("");
                    addressField.setText("");
                    gendeGroup.clearSelection();
                    dayBox.setSelectedIndex(0);
                    monthBox.setSelectedIndex(0);
                    yearBox.setSelectedIndex(0);
                    degreeBox.setSelectedIndex(0);
                    filePathLabel.setText("");
                    selectedFilePath[0] = "";
              }
              catch(IOException ex){
                JOptionPane.showMessageDialog(frame, "Error writing to file !", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
              }
          }

        });



       frame.setVisible(true);

        

      }
}
