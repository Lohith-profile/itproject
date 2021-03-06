import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.Scanner;

public class Bill {
    private MedicalRecords medRecord;

    public Bill(MedicalRecords medRecords) {
        this.medRecord = medRecords;
    }

    public void generateBill() {
        double docFee = medRecord.getDoctor().getFees(), bill;
        double roomFee = medRecord.getHospital().getCostPerRoom();
        double labTestsCharge = 0;
        LabTests[] tests = medRecord.getFineLabTest();
        LabTests[] tests2 = medRecord.getNeedDoctorAttentionTest();

        for (int i = 0; i < medRecord.getFineLabTest().length; i++) {
            if (tests[i] == null) {
                break;
            }
            labTestsCharge += tests[i].getCost();
        }

        for (int i = 0; i < medRecord.getNeedDoctorAttentionTest().length; i++) {
            if (tests2[i] == null) {
                break;
            }
            labTestsCharge += tests2[i].getCost();
        }

        int numberOfDays;
        if (medRecord.isBolAdmitted()) {
            numberOfDays = medRecord.getDoctor().recomStayInHospital();
        } else {
            numberOfDays = 0;
        }
        bill = docFee + roomFee * numberOfDays + labTestsCharge;

        bill += (bill * 18) / 100;

        System.out.println("---------------Bill Explained---------------\n" +
                "Doctors fee - " + docFee +
                "\nNumber of days admitted - " + numberOfDays
        );
        if (medRecord.isBolAdmitted()) {
            System.out.println("Room fee per day - " + roomFee +
                    "\nRoom charges(Number of days admitted * charges per day) - " + (roomFee * numberOfDays)
            );
        }
        System.out.println("Lab Tests Charges - " + labTestsCharge +
                "\nTotal Bill (Hospital Charges + 18 % GST) - " + bill
        );


    }

    public boolean isBillPaid() {
        return true;
    }

    public String billPayMode() {
        System.out.println("\n\nChoose one of the following payment mode: ");
        System.out.println("\n 1. Card \n 2. NetBanking \n 3. Cash \n 4. Cheque \n \n Enter the number here : ");

        int input;
        while(true){
            try {
            Scanner scan1 = new Scanner(System.in);
            input = scan1.nextInt();
            break;
        } catch (InputMismatchException i) {
            System.out.println("*Invalid inout choose from 1 to 4");
        }
        }

        switch (input) {
            case 1:
                return "Card";
            case 2:
                return "NetBanking";
            case 3:
                return "Cash";
            case 4:
                return "Cheque";
            default:
                return "Invalid Input";
        }

    }
}

