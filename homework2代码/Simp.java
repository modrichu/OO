import java.math.BigInteger;
import java.util.ArrayList;

public class Simp {
    public Simp(ArrayList<Term> tterm) {
        for (int i = 0; i < tterm.size(); i++) {
            if (!tterm.get(i).getFlag()) {
                continue;
            }
            if (tterm.get(i).getCon().equals(BigInteger.ZERO)) {
                tterm.get(i).setFalg(false);
                continue;
            }
            for (int j = i; j < tterm.size(); j++) {
                if (!tterm.get(j).getFlag() || j == i) {
                    continue;
                } else {
                    if (tterm.get(i).getFun().equals(tterm.get(j).getFun())
                            &&
                            tterm.get(i).getSin().equals(tterm.get(j).getSin())
                            &&
                            tterm.get(i).getCos().equals(tterm.get(j).getCos())
                    ) {
                        tterm.get(j).setFalg(false);
                        tterm.get(i).setCon(
                                tterm.get(i).getCon().
                                        add(tterm.get(j).getCon()));
                    }
                }
            }
        }
    }

    public Simp() {

    }

    public String hebing(String str) {
        String tstr = str;
        tstr = tstr.replaceAll("(\\+\\+\\+)|(\\-\\-\\+)" +
                "|(\\+\\-\\-)|(\\-\\+\\-)", "+");//三符号合并
        tstr = tstr.replaceAll("(\\+\\+\\-)|" +
                "(\\-\\+\\+)|(\\+\\-\\+)|(\\-\\-\\-)", "-");
        tstr = tstr.replaceAll("(\\+\\-)|(\\-\\+)", "-");//双符号合并
        tstr = tstr.replaceAll("(\\+\\+)|(\\-\\-)", "+");
        return tstr;
    }
}
