package Stage_1;

import java.util.*;

public class S_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String again;
        System.out.println("Choose from the menu");
        System.out.println("1.Calculate Expression \n2.Exit");
        String menu = input.nextLine();
        switch (menu) {
            case "1":
                do{
                    System.out.println("Enter the expression");
                    String exp = input.nextLine();
                    String[] text = exp.split(" ");
                    ArrayList<Object> calculatedExp = new ArrayList<>();
                    // left to right traversal for multiplication and division
                    for (int i = 0; i < text.length; i++) {
                        if (text[i].equals("*")) {
                            int n = calculatedExp.size();
                            double mul = Double.parseDouble(calculatedExp.get(n - 1).toString()) * Double.parseDouble(text[i + 1]);
                            calculatedExp.set(n - 1, mul);
                            i++;
                        } else if (text[i].equals("/")) {
                            if (Double.parseDouble(text[i + 1]) == 0) {
                                System.out.println("Invalid expression cannot divide number by 0");
                                System.exit(0);
                            } else {
                                int n = calculatedExp.size();
                                double div = Double.parseDouble(calculatedExp.get(n - 1).toString()) / Double.parseDouble(text[i + 1]);
                                calculatedExp.set(n - 1, div);
                                i++;
                            }
                        } else {
                            calculatedExp.add((text[i]));
                        }
                    }

                    // left to right traversal for addition and subtraction
                    int i = 0;
                    while (i < calculatedExp.size()) {
                        if (calculatedExp.get(i).equals("+")) {
                            double sum = Double.parseDouble(calculatedExp.get(i - 1).toString()) + Double.parseDouble(calculatedExp.get(i + 1).toString());
                            calculatedExp.set(i - 1, sum);
                            calculatedExp.remove(i + 1);
                            calculatedExp.remove(i);
                        } else if (calculatedExp.get(i).equals("-")) {
                            double sub = Double.parseDouble(calculatedExp.get(i - 1).toString()) - Double.parseDouble(calculatedExp.get(i + 1).toString());
                            calculatedExp.set(i - 1, sub);
                            calculatedExp.remove(i + 1);
                            calculatedExp.remove(i);
                        } else {
                            i++;
                        }
                    }
                    System.out.println("Output: " + calculatedExp.get(0));
                    System.out.println("Choose from the menu");
                    System.out.println("1.Calculate Expression \n2.Exit");
                    again = input.nextLine();
                }
                while(again.equals("1"));
                break;

            case "2":
                System.out.println("Exiting from program ....");
                break;
            default:
                System.out.println("Invalid Input");
        }
        input.close();
    }
}