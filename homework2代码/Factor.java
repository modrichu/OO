import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Factor {
    private BigInteger index;
    private BigInteger coef;
    private int kind = -1;
    private String str;

    public BigInteger getIndex() {
        return index;
    }

    public BigInteger getCoef() {
        return coef;
    }

    public int getKind() {
        return kind;
    }

    public Factor(String str) {
        this.str = str;
        ClassifyFactor();
        if (kind == 1) {
            index = BigInteger.ZERO;
            Pattern p = Pattern.compile("[+-]?[0-9]+");
            Matcher m = p.matcher(str);
            if (m.find()) {
                coef = new BigInteger(m.group());
            }
        } else if (kind == 2 || kind == 3 || kind == 4) {
            if (str.charAt(0) == '+') {
                coef = BigInteger.ONE;
            }
            Pattern p = Pattern.compile("[+-]?[0-9]+");
            Matcher m = p.matcher(str);
            if (m.find()) {
                index = new BigInteger(m.group());
            } else {
                index = BigInteger.ONE;
            }
        } else {
            return;
        }
    }

    public boolean CheckFactor() {
        if (kind > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void ClassifyFactor() {
        Pattern p0 = Pattern.compile("[+-]?[0-9]+");//常数项
        Matcher m0 = p0.matcher(str);
        if (m0.matches()) {
            kind = 1;
        }
        Pattern p1 = Pattern.compile("x(\\^[+-]?[0-9]+)?");//幂函数
        Matcher m1 = p1.matcher(str);
        if (m1.matches()) {
            kind = 2;
        }
        Pattern p2 = Pattern.compile("sin\\(x\\)(\\^[+-]?[0-9]+)?");//sinx
        Matcher m2 = p2.matcher(str);
        if (m2.matches()) {
            kind = 3;
        }
        Pattern p3 = Pattern.compile("cos\\(x\\)(\\^[+-]?[0-9]+)?");//cosx
        Matcher m3 = p3.matcher(str);
        if (m3.matches()) {
            kind = 4;
        }
    }

    public void setCoef(BigInteger coef) {
        this.coef = coef;
    }

    public void setIndex(BigInteger index) {
        this.index = index;
    }

}
