import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPoly {
    private String str;
    private StringBuffer strbuf = new StringBuffer("");
    private ArrayList<Term> terms = new ArrayList<>();

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public CheckPoly(String str) {
        this.str = str;
    }

    public boolean check() {
        if (!preCheck()) {         //预检验过关
            return false;
        }
        //去除空格，合并符号
        str = str.replaceAll("[ \t]", "");     //删空格
        Simp sim = new Simp();
        str = sim.hebing(str);        //合并符号
        //排除空串
        if (str.equals("")) {
            return false;
        }
        strbuf.append(str);//使用StringBuffer的方法
        int length = strbuf.length();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                continue;
            } else {
                if ((strbuf.charAt(i) == '+' || strbuf.charAt(i) == '-')
                        && strbuf.charAt(i - 1) != '^'
                        && strbuf.charAt(i - 1) != '*') {
                    strbuf.insert(i, " ");
                    length++;
                    i++;
                }
            }
        }
        str = strbuf.toString();
        String[] temp = str.split(" ");//把每一项分离开来
        for (int i = 0; i < temp.length; i++) {
            terms.add(new Term(temp[i]));
            if (!terms.get(i).checkTerm()) {
                return false;
            }
        }
        return true;//空格，每一项格式都没有问题，检查结果正确
    }

    private boolean preCheck() {
        boolean f = paM("[0-9]+\\s+[0-9]+")  //数字之间有空格
                & paM("[*^]\\s*[+-]\\s+[0-9]+")  //非负整数自带的空格
                & paM("(s\\s+in)|(si\\s+n)|(c\\s+os)|(co\\s+s)") //三角函数保留字内有空格
                & paM("([+-]\\s*){4,}")//四个以上+-相连
                & paM("[+-]\\s*[+-]\\s*[+-]\\s*[^0-9]")//三符号后面不是数字
                & paM("[*][+-]\\s*[+-]\\s*[+-]\\s*")//三个符号不是第一个因子
                & paM("[*^]\\s*[+-]\\s*" +
                "[+-]\\s*[+-]\\s+[0-9]+");  //三符号最后一个符号与数字之间有空格
        f = f & paM("[*][+-]\\s*[+-]\\s*");//双符号不是第一个因子
        return f;
    }

    private boolean paM(String regx) {
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return false;
        } else {
            return true;
        }
    }
}
