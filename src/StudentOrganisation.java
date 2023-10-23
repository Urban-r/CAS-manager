import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentOrganisation {
    public ArrayList<String> student;// saves the students name in an array list
    public ArrayList<Integer> event; // saves the events done in an array list
    public StudentProfile[] profile = new StudentProfile[100];
    private String feedback;

    public StudentOrganisation(String filename) throws InterruptedException {
        System.out.println("Welcome Mr Auckland to the CAS portfolio manager");
        boolean flag = false;
        while (!flag) {
            student = new ArrayList<String>();
            event = new ArrayList<Integer>();
            nameGrab(filename);
            studentBubbleSort();
            display();
            binarySearch();
            feedbackTools();
            flag = condition();
        }
        System.exit(0); // ends program when out of loop
    }

    private void nameGrab(String filename) {
        try {
            int i = 0; // counter
            InputStream stream = getClass().getClassLoader().getResourceAsStream(
                    filename);
            Reader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader); // buffers the file


            String data = br.readLine(); // saves the buffered read onto a string
            while (data != null) { // loop while there is text in the file
                String[] parts = data.split(",");
                student.add(i, parts[0]); // adds the data at a incrementing index to the array list
                int eventNum;
                try{
                    eventNum = Integer.parseInt(parts[1]); // converts String into an integer
                    event.add(i,eventNum);
                } catch (NumberFormatException e){// handles the exception
                    e.printStackTrace();
                }

                data = br.readLine(); // reads the next line
                i++;
            }

            br.close(); // closes the file reader
        } catch (IOException e) { // catches any errors where the file cannot be found
            System.out.println("An error occurred");
            e.printStackTrace();
        }// catches any errors where there is a IO Exception

    }
    private void studentBubbleSort() { // using bubble sort to sort the student list alphabetically by name
        String tempStr;
        int tempInt;
        int n = student.size();
        for (int i = 0; i<n-1;i++) {
            for (int j=0;j<n-i-1;j++) {
                if (student.get(j).compareTo(student.get(j+1)) >0) {
                    // swap names at variable j and the adjacent value
                    tempStr = student.get(j);
                    student.set(j,student.get(j+1));
                    student.set(j+1,tempStr);
                    //swap events at similar variables as they have same index
                    tempInt = event.get(j);
                    event.set(j,event.get(j+1));
                    event.set(j+1,tempInt);

                }
            }
        }

    }
    public void display(){
        System.out.println("Student name   Events done  profile status    Feedback     Attention");

        for (int j = 0; j < student.size();j++) {
            if (event.get(j) == 4 || event.get(j) == 5) {
                profile[j] = new Excellent(student.get(j),event.get(j),(new File(student.get(j)+".txt").exists()),"Excellent");
                System.out.println(profile[j].getName()+"         "+profile[j].getEvent()+"         "+profile[j].getProgress()+"         "+profile[j].getFeedback()+"         "+profile[j].attention());
            }
            if (event.get(j) == 2 || event.get(j) == 3) {
                profile[j] = new OnTrack(student.get(j),event.get(j),(new File(student.get(j)+".txt").exists()),"On-track");
                System.out.println(profile[j].getName()+"         "+profile[j].getEvent()+"         "+profile[j].getProgress()+"         "+profile[j].getFeedback()+"         "+profile[j].attention());
            }
            if (event.get(j) == 0 || event.get(j) ==1) {
                profile[j] = new Concern(student.get(j),event.get(j),(new File(student.get(j)+".txt").exists()),"Concern");
                System.out.println(profile[j].getName()+"         "+profile[j].getEvent()+"         "+profile[j].getProgress()+"         "+profile[j].getFeedback()+"         "+profile[j].attention());
            }
        }
    }
    public String binarySearch() throws InterruptedException { // search algorithm to find student in arraylist
        boolean flag = false;
        Scanner obj = new Scanner(System.in);
        System.out.println("what name would you like to give feedback to?");
        String nameSearch = obj.nextLine();
        feedback = nameSearch; // String saved outside method
        String key = nameSearch;
        int first = 0; // first index value
        int last = (student.size() - 1); // last index value
        while (first <= last) { // loops the function until the key (name) is found or not found
            int mid = first + (last - first) / 2; // middle index value
            int position = key.compareTo(student.get(mid));

            //check if key is present at mid
            if (position == 0) {
                System.out.println("Name is present");
                return "name present";
            }
            // if position greater, ignore the left half
            if (position > 0) {
                first = mid + 1;
            } else {
                last = mid - 1;
            }
        }
        System.out.println("name is not present");
        return "name not present";
    }
    public void feedbackTools() throws InterruptedException {
        Scanner obj = new Scanner(System.in);
        boolean flag = false;
        while (flag == false){
            System.out.println("would you like to give feedback to this student?  \n(type yes to continue or anything else to restart)");
            String response = obj.nextLine();
            if (response.equals("yes")) {
                try { // create file under the student's name who needs feedback
                    File myObj = new File(feedback+".txt");
                    if (myObj.createNewFile()) {
                        System.out.println("Feedback file created: "+ myObj.getName());
                    } else { // prints weather file is created or already exists
                        System.out.println("file already exists.");
                    }
                    FileWriter myWriter = new FileWriter(myObj.getName());
                    System.out.println("Type here:");
                    // writes user input onto the file
                    String comment = obj.nextLine();
                    System.out.println("--------------\n"+"would you like to save this?\n(type yes to confirm or no to cancel)");
                    String confirm = obj.nextLine();
                    if(confirm.equals("yes")) {
                        // confirmation of feedback text
                        myWriter.write(comment);
                        myWriter.close();
                        // writes onto file and closes
                        System.out.println("\n" +
                                "successfully written");
                        System.out.println(comment);
                        flag = true;
                        return;

                    }
                    if (confirm.equals("no")) {
                        Thread.sleep(1000); // user-friendly
                        System.out.println("~~~~~~~~~~~\n-Type restart if you want the program to restart\n-Type anything else to continue giving feedback");
                        String condition = obj.nextLine();
                        if (condition.equals("restart")) {
                            flag = true;
                        }

                    } else {
                        Thread.sleep(1000); // user-friendly
                        System.out.println("~~~~~~~~~~~\n-Type restart if you want the program to restart\n-Type anything else to continue giving feedback");
                        String condition = obj.nextLine();
                        if (condition.equals("restart")) {
                            flag = true;
                        }

                    }

                } catch (IOException e) {
                    System.out.println("An error occurred");
                    e.printStackTrace();
                }

            }else {
                Thread.sleep(1000); // user-friendly
                System.out.println("~~~~~~~~~~~\n-Type restart if you want the program to restart\n-Type anything else to continue giving feedback");
                String condition = obj.nextLine();
                if (condition.equals("restart")) {
                    flag = true;
                }
            }
        }

     }
     private boolean condition() { // false if user keeps wanting program to run and true if user wants to terminate program
        Scanner obj = new Scanner(System.in);
        System.out.println("~~~~~~~~~~~~\n-Type stop if you want the program to terminate\n-Type anything else to continue giving feedback");
        String confirmation = obj.nextLine();
        if (confirmation.equals("stop")) {
            System.exit(0);
            return true;
        }else{
            return false;
        }
     }
}
