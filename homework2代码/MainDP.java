import java.util.ArrayList;
import java.util.Scanner;

public class MainDP {
    //主函数入口
    private static ArrayList<Term> terms;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextLine()) {
            String inputStr = scan.nextLine();
            CheckPoly po = new CheckPoly(inputStr);
            terms = po.getTerms();
            if (po.check()) {
                Derivation der = new Derivation(terms);
                System.out.println(der);
            } else {
                System.out.println("WRONG FORMAT!");
            }
        } else {
            System.out.println("WRONG FORMAT!");
        }
    }
}
