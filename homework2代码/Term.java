import java.math.BigInteger;
import java.util.ArrayList;

public class Term {
    private int count = 0;//因子的个数
    private String str;//项
    private BigInteger con = BigInteger.ONE;//常数项
    private BigInteger fun = BigInteger.ZERO;//幂函数项的指数
    private BigInteger sin = BigInteger.ZERO;//sinx的指数
    private BigInteger cos = BigInteger.ZERO;//cosx的指数
    private ArrayList<Factor> facs = new ArrayList<>();//因子数组
    private boolean isVaild = true;//合法性
    private boolean flag = true;
    private String term1;
    private String term2;
    private String term3;

    public boolean getFlag() {
        return flag;
    }

    public void setFalg(boolean flag) {
        this.flag = flag;
    }

    public void setCon(BigInteger con) {
        this.con = con;
    }

    public void setFun(BigInteger fun) {
        this.fun = fun;
    }

    public void setCos(BigInteger cos) {
        this.cos = cos;
    }

    public void setSin(BigInteger sin) {
        this.sin = sin;
    }

    public void setTerm1(String term1) {
        this.term1 = term1;
    }

    public void setTerm2(String term2) {
        this.term2 = term2;
    }

    public void setTerm3(String term3) {
        this.term3 = term3;
    }

    public String getTerm1() {
        return term1;
    }

    public String getTerm2() {
        return term2;
    }

    public String getTerm3() {
        return term3;
    }

    public BigInteger getCon() {
        return con;
    }

    public BigInteger getFun() {
        return fun;
    }

    public BigInteger getSin() {
        return sin;
    }

    public BigInteger getCos() {
        return cos;
    }

    public Term(String str) {
        this.str = str;
        breakDown();
        merge();
    }

    public Term() {

    }

    private void breakDown() {
        if (str.length() > 1 && (str.charAt(0) == '+' || str.charAt(0) == '-')
                && (str.charAt(1) > '9' || str.charAt(1) < '0')) {
            StringBuffer sb = new StringBuffer(str);
            sb.insert(1, "1*");
            str = sb.toString();
        }
        if (str.charAt(str.length() - 1) == '*') {
            isVaild = false;
        }
        String[] temp = str.split("\\*");
        for (int i = 0; i < temp.length; i++) {
            facs.add(new Factor(temp[i]));
            count++;
        }
    }

    private void merge() {
        for (int i = 0; i < count; i++) {
            if (facs.get(i).getKind() == 1) {
                con = con.multiply(facs.get(i).getCoef());
            } else if (facs.get(i).getKind() == 2) {
                fun = fun.add(facs.get(i).getIndex());
            } else if (facs.get(i).getKind() == 3) {
                sin = sin.add(facs.get(i).getIndex());
            } else if (facs.get(i).getKind() == 4) {
                cos = cos.add(facs.get(i).getIndex());
            } else {
                isVaild = false;
            }
        }
    }

    public boolean checkTerm() {
        return isVaild;
    }

}