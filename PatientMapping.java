import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PatientMapping {
    private Hospital[] HspArray;
    private int totalHospitals = 0;

    public void HospitalBuilder(String filename) throws FileNotFoundException {
        Hospital[] HspArrayLocal = new Hospital[10000];
        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);
        int i = 0;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            System.out.println(line);
            String[] HspStr = line.split("\\|");
            Hospital H1 = new Hospital(Integer.parseInt(HspStr[0]),
                    HspStr[1],
                    Double.parseDouble(HspStr[2]),
                    Double.parseDouble(HspStr[3]),
                    Double.parseDouble(HspStr[4]),
                    HspStr[5],
                    HspStr[6],
                    HspStr[7],
                    Double.parseDouble(HspStr[8]),
                    HspStr[9]);

            HspArrayLocal[i] = H1;
            i++;
            totalHospitals++;
        }
        this.HspArray = HspArrayLocal;
    }

    public Hospital[] recommend(Location l, int n) {
        System.out.printf("\n\nTop %d nearest hospitals are:\n\n", n);
        Hospital temp;
        Hospital[] nearest = new Hospital[totalHospitals];
        for (int i = 0; i < totalHospitals; i++) {
            for (int j = i + 1; j < totalHospitals; j++) {
                if (HspArray[i].getDistance(l) > HspArray[j].getDistance(l)) {
                    temp = HspArray[i];
                    HspArray[i] = HspArray[j];
                    HspArray[j] = temp;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            nearest[i] = HspArray[i];
            System.out.println("Hospital " + (i + 1) + " " + HspArray[i].getName() + "  " + HspArray[i].getDistance(l) + "\n");
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        return nearest;
    }

    public void selectHsp(int index) {

        switch (index) {
            case 1:
                System.out.println(HspArray[0]);
                break;
            case 2:
                System.out.println(HspArray[1]);
                break;
            case 3:
                System.out.println(HspArray[2]);
                break;
            case 4:
                System.out.println(HspArray[3]);
                break;
            case 5:
                System.out.println(HspArray[4]);
                break;
            case 6:
                System.out.println(HspArray[5]);
                break;
            case 7:
                System.out.println(HspArray[6]);
                break;
            case 8:
                System.out.println(HspArray[7]);
                break;
            case 9:
                System.out.println(HspArray[8]);
                break;
            case 10:
                System.out.println(HspArray[9]);
                break;
            default:
                System.out.println("No choice was selected");

        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        PatientMapping p1 = new PatientMapping();
        Location l1 = new Location(28.6052154684654, 77.2155121525);
        p1.HospitalBuilder("C:\\Users\\Praveen Raj HL\\Desktop/Hospitals.txt");
        p1.recommend(l1, 10);
        p1.selectHsp(Integer.parseInt(args[0]));
        for (int i= 0;i<p1.totalHospitals;i++){
            System.out.println("--------------------------------------------------------------------");
            System.out.println(p1.HspArray[i]);
        }


    }
}