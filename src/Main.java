import java.util.Scanner;
import java.util.Random;


    public static void main(String[] args) throws InterruptedException {
        Login user = new Login("LoginFile");
        Scanner obj = new Scanner(System.in);
        boolean flag = false;
        while (flag == false) {
            int entry = obj.nextInt();
            if (entry == 365836) {
                flag = true;
                StudentOrganisation allStudents = new StudentOrganisation("StudentNames");
            }
        }



    }
}
