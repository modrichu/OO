import java.math.BigInteger;
import java.util.ArrayList;

public class Derivation {
    private ArrayList<Term> ts;
    private ArrayList<Term> newterm = new ArrayList<>();

    public Derivation(ArrayList<Term> terms) {
        ts = terms;
        for (int i = 0; i < ts.size(); i++) {
            if (!ts.get(i).getCon().equals(BigInteger.ZERO)) {
                //第一项：幂函数求导，三角函数不求导
                Term t1 = new Term();
                t1.setCon(ts.get(i).getCon().multiply(ts.get(i).getFun()));
                t1.setFun(ts.get(i).getFun().subtract(BigInteger.ONE));
                t1.setSin(ts.get(i).getSin());
                t1.setCos(ts.get(i).getCos());
                newterm.add(t1);
                //第二项：sinx求导
                Term t2 = new Term();
                t2.setCon(ts.get(i).getCon().multiply(ts.get(i).getSin()));
                t2.setFun(ts.get(i).getFun());
                t2.setSin(ts.get(i).getSin().subtract(BigInteger.ONE));
                t2.setCos(ts.get(i).getCos().add(BigInteger.ONE));
                newterm.add(t2);
                //第三项：cosx求导
                Term t3 = new Term();
                t3.setCon(ts.get(i).getCon().multiply(ts.get(i).getCos()));
                if (!ts.get(i).getCos().equals(BigInteger.ZERO)) {
                    t3.setCon(t3.getCon().multiply(
                            BigInteger.ZERO.subtract(BigInteger.ONE)));
                }
                t3.setFun(ts.get(i).getFun());
                t3.setSin(ts.get(i).getSin().add(BigInteger.ONE));
                t3.setCos(ts.get(i).getCos().subtract(BigInteger.ONE));
                newterm.add(t3);
            }
        }
        Simp sim = new Simp(newterm);
    }

    public String toString() {
        String poly = "";
        for (int i = 0; i < newterm.size(); i++) {
            if (!newterm.get(i).getFlag()) {
                continue;
            }
            if (newterm.get(i).getCon().equals(BigInteger.ZERO)) {
                continue;
            }
            poly += newterm.get(i).getCon().toString();
            if (!newterm.get(i).getFun().equals(BigInteger.ZERO)) {
                poly += "*x^" + newterm.get(i).getFun().toString();
            }
            if (!newterm.get(i).getSin().equals(BigInteger.ZERO)) {
                poly += "*sin(x)^" + newterm.get(i).getSin().toString();
            }
            if (!newterm.get(i).getCos().equals(BigInteger.ZERO)) {
                poly += "*cos(x)^" + newterm.get(i).getCos().toString();
            }
            poly += "+";
        }
        poly = poly.replaceAll("\\^1", "");
        poly = poly.replaceAll("1\\*", "");
        poly = poly.replaceAll("(^\\+)|(\\+$)|(\\-$)", "");
        Simp s = new Simp();
        poly = s.hebing(poly);

        if (poly.equals("")) {
            poly += "0";
        }
        return poly;
    }
}