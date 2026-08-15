package Stage_3;

import java.util.ArrayList;
import java.util.Scanner;

class expression{
    String exp;
    expression(String exp){
        this.exp = exp;
    }

    public void expressionEvaluation(String exp){
        String[] expNew = exp.split(" ");
        ArrayList<Object> finalExp = new ArrayList<>();

        try{
            int j = expNew.length - 1;
            if(expNew[j].matches("[+\\-*/]")){
                throw new IllegalArgumentException("The expression must be in the form of a number, an operator and then a number");
            }

            for (String string : expNew) {
                if (!string.matches("[+\\-*/]|-?\\d+(\\.\\d+)?")) {
                    throw new IllegalArgumentException("The expression must be in the form of a number, an operator and then a number");
                }
            }

            // left to right evaluation of multiplication and division
            for(int i = 0; i < expNew.length; i++){
                if(expNew[i].equals("*")){
                    int n = finalExp.size() ;
                    double m = mul(finalExp.get(n-1),expNew[i+1]);
                    finalExp.set(n-1,m);
                    i++;
                }

                else if (expNew[i].equals("/")) {
                    int n = finalExp.size();
                    double d = div(finalExp.get(n-1),expNew[i+1]);
                    finalExp.set(n-1,d);
                    i++;
                }
                else {
                    finalExp.add(expNew[i]);
                }
            }

            // left to right evaluation of addition and Subtraction
            int i = 0;
            while(i < finalExp.size()) {
                if (finalExp.get(i).equals("+")) {
                    double a = add(finalExp.get(i-1), finalExp.get(i+1));
                    finalExp.set(i-1, a);
                    finalExp.remove(i+1);
                    finalExp.remove(i);
                }
                else if (finalExp.get(i).equals("-")) {
                    double s = sub(finalExp.get(i-1), finalExp.get(i+1));
                    finalExp.set(i-1, s);
                    finalExp.remove(i+1);
                    finalExp.remove(i);
                }
                else i++;
            }
            System.out.println("Solution: "+Double.parseDouble(finalExp.get(0).toString()));
        }
        catch (ArithmeticException | IllegalArgumentException | IndexOutOfBoundsException e){
            System.out.println("Error caught: "+e.getMessage());
            System.out.println("Invalid Expression");
        }
    }
    public double add(Object a, Object b){
        double c = Double.parseDouble(a.toString());
        double d = Double.parseDouble(b.toString());
        return c + d;
    }
    public double sub(Object a, Object b){
        double c = Double.parseDouble(a.toString());
        double d = Double.parseDouble(b.toString());
        return c - d;
    }
    public double mul(Object a, Object b){
        double c = Double.parseDouble(a.toString());
        double d = Double.parseDouble(b.toString());
        return c * d;
    }
    public double div(Object a, Object b){
        double c = Double.parseDouble(a.toString());
        double d = Double.parseDouble(b.toString());
        if (d == 0){
            throw new ArithmeticException("Invalid expression: Cannot divide a number by 0");
        }
        return c / d;
    }
    public void solution(){
        String exp = this.exp;
        System.out.println("Expression: "+exp);
        expressionEvaluation(exp);
    }
}
public class S_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Choose from the menu");
            System.out.println("1.Calculate Expression \n2.Exit");
            int menu = input.nextInt();
            input.nextLine();
            if (menu == 1) {
                System.out.println("Enter the expression to evaluate");
                String exp = input.nextLine();
                expression exp1 = new expression(exp);
                exp1.solution();
            }
            else if (menu == 2) {
                System.out.println("Exiting the program ...");
                break;
            }
            else {
                    System.out.println("Invalid input");
            }
        }
        input.close();
    }
}
