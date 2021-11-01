import java.util.ArrayList;
import java.util.jar.JarOutputStream;

import static java.lang.Math.abs;


public class Crypto {

    private static ArrayList<Integer> convert(String s) {
        ArrayList<Integer> r = new ArrayList<>();
        Character m = s.charAt(0);
        boolean isMinus = false;
        if (m.equals('-'))
        {
            isMinus = true;
            s = s.substring(1);
        }

        while (s.length() > 0)
        {
            String s1 = s.substring(0, 1);
            int i = Integer.parseInt(s1);
            r.add(i);
            s = s.substring(1);
        }
        if (isMinus){
            int first = -r.get(0);
            r.set(0, first);
        }
        return r;
    }

    private static void print(ArrayList<Integer> a) {
        for (Integer integer : a) System.out.print(integer);
        System.out.println("\n");
    }

    private static ArrayList<Integer> max(ArrayList<Integer> a1, ArrayList<Integer> b1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        ArrayList<Integer> b = new ArrayList<Integer>(b1);
        if (a.size() > 1)
        if (a.get(0) == 0 && a.get(1) == 0){
            a.clear();
            a.add(0);
        }
    if (b.size() > 1)
        if (b.get(0) == 0 && b.get(1) == 0) {
            b.clear();
            b.add(0);
        }
    if (a.size() == 0) return b;
    if (b.size() == 0) return a;
    if (a.get(0) < 0 && b.get(0) < 0)
    {
        if (a.size() > b.size()) return b;
        if (a.size() < b.size()) return a;
        for (int i = 0; i < a.size(); ++i)
            if (abs(a.get(i)) > abs(b.get(i))) return b;
            else if (abs(b.get(i)) > abs(a.get(i))) return a;
    }
    if (a.get(0) < 0) return b;
    if (b.get(0) < 0) return a;
    if (a.size() > b.size()) return a;
    if (a.size() < b.size()) return b;
    for (int i = 0; i < a.size(); ++i){
        if (a.get(i) > b.get(i)) return a;
        else if (b.get(i) > a.get(i)) return b;}
    return a;
    }

    private static ArrayList<Integer> min(ArrayList<Integer> a1, ArrayList<Integer> b1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        ArrayList<Integer> b = new ArrayList<Integer>(b1);
        if (max(a, b) == a) return b;
        return a;
    }

    private static boolean equal(ArrayList<Integer> a1, ArrayList<Integer> b1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        ArrayList<Integer> b = new ArrayList<Integer>(b1);
        if(a.size()!=b.size())
        {
            return false;
        }
        for(int i=0; i<a.size(); i++){
            if(!a.get(i).equals(b.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Integer> m(ArrayList<Integer> a1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        a.set(0, abs(a.get(0)));
        return a;
    }

    private static ArrayList<Integer> add(ArrayList<Integer> a1, ArrayList<Integer> b1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        ArrayList<Integer> b = new ArrayList<Integer>(b1);
        if (!(a.get(0) < 0 && b.get(0) < 0))
        {
            if (a.get(0) < 0) return sub(m(b), m(a));
            if (b.get(0) < 0) return sub(m(a), m(b));
        }
        if (a.get(0) < 0 && b.get(0) < 0)
        {
           return sub(b, m(a));
        }
        if (a.size() < b.size())
        {
            ArrayList<Integer> tmpList = new ArrayList<Integer>(a);
            a.clear();
            a.addAll(b);
            b.clear();
            b.addAll(tmpList);
        }
        for (int i = 1; i <= a.size(); ++i)
        {
            if (b.size() >= i)
                a.set((a.size() - i), (a.get((a.size() - i))+b.get(b.size() - i)));
            while (a.get(a.size() - i) > 9)
                if (a.size() - i == 0) {
                    ArrayList<Integer> tmpList2 = new ArrayList<Integer>(a);
                    a.clear();
                    a.add(1);
                    a.addAll(tmpList2);
                    a.set(1, (a.get(1)-10));
                }
                else {
                    a.set((a.size() - i), (a.get(a.size() - i)-10));
                    a.set((a.size() - i - 1), (a.get(a.size() - i - 1)+1));
                }
        }
        return a;
    }

    private static ArrayList<Integer> sub(ArrayList<Integer> a1, ArrayList<Integer> b1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        ArrayList<Integer> b = new ArrayList<Integer>(b1);
        boolean isMinus = false;
        if (a.get(0) < 0 && b.get(0) < 0)
            return sub(m(b), m(a));
        if (a.get(0) < 0)
        {
            ArrayList<Integer> zero = new ArrayList<>();
            zero.add(0);
            return sub(zero, add(m(a), m(b)));
        }
        if (b.get(0) < 0)
        {
            return add(m(a), m(b));
        }

        while (a.size() < b.size()) {
            ArrayList<Integer> tmpList2 = new ArrayList<Integer>(a);
            a.clear();
            a.add(0);
            a.addAll(tmpList2);
        }

        ArrayList<Integer> maxA = new ArrayList<Integer>(a);
        if (max(a, b) == b) {
            a.clear();
            a.addAll(maxA);
            ArrayList<Integer> tmpList = new ArrayList<Integer>(a);
            a.clear();
            a.addAll(b);
            b.clear();
            b.addAll(tmpList);
            isMinus=true;
        }
        while (b.size() < a.size() - 1) {
            ArrayList<Integer> tmpList2 = new ArrayList<Integer>(b);
            b.clear();
            b.add(0);
            b.addAll(tmpList2);
        }
        for (int i = 1; i <= b.size(); i++) {
            a.set(a.size() - i, (a.get(a.size() - i) - b.get(b.size() - i)));
            if (a.get(a.size() - i) < 0) {
                a.set(a.size() - i,(a.get(a.size() - i) + 10));
                a.set(a.size() - i - 1, (a.get(a.size() - i - 1) - 1));
            }
        }
        while (a.get(0) == 0 && a.size() > 1) {
            a.remove(0);
        }
        if (isMinus) a.set(0, -1*a.get(0));
        return a;
    }

    private static ArrayList<Integer> neg(ArrayList<Integer> a1) {

        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        a.set(0, a.get(0) * (-1));
        return a;
    }

    private static ArrayList<Integer> mult(ArrayList<Integer> a1, ArrayList<Integer> b1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        ArrayList<Integer> b = new ArrayList<Integer>(b1);
        boolean isMinus = false;
        if (a.size() < b.size()) {
            ArrayList<Integer> tmpList = new ArrayList<Integer>(a);
            a.clear();
            a.addAll(b);
            b.clear();
            b.addAll(tmpList);
        }
        ArrayList<Integer> zero = new ArrayList<>();
        zero.add(0);
        if (a == zero || b == zero) {
            return zero;
        }
        if ((a.get(0) > 0 && b.get(0) < 0) || (a.get(0) < 0 && b.get(0) > 0)) {
            isMinus = true;
        }
        a = m(a);
        b = m(b);
        ArrayList<Integer> r = zero;
        for (int i = 1; i <= b.size(); ++i)
        {
            ArrayList<Integer> c = zero;
            for (int j = b.get(b.size() - i); j > 0; --j)
                c = add(c, a);
            for (int j = 1; j < i; ++j)
                c.add(0);
            r = add(r, c);
        }
        while(r.get(0)==0)
        {
            r.remove(0);
        }
        if (isMinus) {
            r = neg(r);
        }
        return r;
    }

    private static ArrayList<Integer> div(ArrayList<Integer> a1, ArrayList<Integer> b1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        ArrayList<Integer> b = new ArrayList<Integer>(b1);
        boolean isMinus = false;
        ArrayList<Integer> zero = new ArrayList<>();
        zero.add(0);
        ArrayList<Integer> one = new ArrayList<>();
        one.add(1);
        if (equal(a, b)) return one;
        if (a == zero || max(m(a), m(b)) == b) return zero;
        if (b == zero) {
            System.out.println("Error ");
            return a;
        }
        if ((a.get(0) > 0 && b.get(0) < 0) || (a.get(0) < 0 && b.get(0) > 0)) {
            a = m(a);
            b = m(b);
            isMinus = true;
        }
        a = m(a); b = m(b);
        ArrayList<Integer> r = new ArrayList<>();
        r.add(0);
        ArrayList<Integer> c = new ArrayList<>();
        int i = 0;
        ArrayList<Integer> j = new ArrayList<>();
        j.add(0);
        while (max(b, c) != c && i < a.size())
        {
            c.add(a.get(i++));

        }

        ArrayList<Integer> mx = new ArrayList<>();

        while (equal(max(c ,b), c) && (c.size() - b.size() > 1)) {
                int dif = c.size() - b.size() - 1;
                mx.clear();
                mx.add(1);
                mx.add(0);
                mx = pow(mx, convert(Integer.toString(dif)));

                c = sub(c, mult(b, mx));
                j = add(j, mx);
            }
        while (equal(max(c ,b), c)) {
                c = sub(c, b);
                j = add(j, one);
            }

        r = add(r, j);
        while(i > 0) {
            a.remove(0);
            i--;
        }
        while (a.size() > 0)
        {
            if (c == zero) c.remove(0);
            j= zero;
            while (max(b, c) != c)
            {
                if (i == a.size())
                {
                    r.add(0);
                    return r;
                }
                c.add(a.get(i++));
                if (i > 1) r.add(0);
            }
            while (max(b, c) == c) {
                c = sub(c, b);
                j = add(j, one);
            }

            r = add(r, j);

            if (i > a.size()) break;
            while(i > 0)
            {
                a.remove(0);
                i--;
            }
        }

        if (isMinus) r = neg(r);
        return r;
    }

    private static ArrayList<Integer> mod(ArrayList<Integer> a1, ArrayList<Integer> b1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        ArrayList<Integer> b = new ArrayList<Integer>(b1);

        if(b1.size()>a1.size()){
            return a;
        }
        if(equal(max(a, b), b))
        {
            return a;
        }

        return sub(a, mult(b, div(a, b)));
    }

    private static ArrayList<Integer> pow(ArrayList<Integer> a1, ArrayList<Integer> b1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        ArrayList<Integer> b = new ArrayList<Integer>(b1);
        ArrayList<Integer> zero = new ArrayList<Integer>();
        zero.add(0);
        ArrayList<Integer> one = new ArrayList<Integer>();
        one.add(1);
        b = m(b);
        ArrayList<Integer> c = new ArrayList<Integer>(zero);
        ArrayList<Integer> r = new ArrayList<Integer>(one);
        while (!equal(c, b)) {

            r = mult(a, r);
            c = add(c, one);

        }
        return r;
    }

    private static ArrayList<Integer> powmod(ArrayList<Integer> a1, ArrayList<Integer> b1, ArrayList<Integer> md1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        ArrayList<Integer> b = new ArrayList<Integer>(b1);
        ArrayList<Integer> md = new ArrayList<Integer>(md1);
        ArrayList<Integer> zero = new ArrayList<Integer>();
        zero.add(0);
        ArrayList<Integer> one = new ArrayList<Integer>();
        one.add(1);
        ArrayList<Integer> two = new ArrayList<Integer>();
        two.add(2);
        b = m(b);
        ArrayList<Integer> c = new ArrayList<Integer>(one);
        ArrayList<Integer> r = new ArrayList<Integer>(a);
        ArrayList<Integer> ans = new ArrayList<Integer>(one);

        if (equal(b, zero)) {
            r = one;
            return r;
        }
        if (equal(b, one)) {
            r = a;
            return r;
        }

            while(!equal(c, b)){
                if(equal(mod(b, two), zero)){
                    //2
                    b = div(b, two);
                    r = mult(r, r);
                    r = mod(r, md);
                }
                else{
                    //1
                    b = sub(b, one);
                    ans = mult(ans, r);
                    ans = mod(ans, md);
                }
            }
        r = mult(r, ans);
        r = mod(r, md);
        return r;
    }

    private static ArrayList<Integer> sqrt(ArrayList<Integer> a1) {
        ArrayList<Integer> a = new ArrayList<Integer>(a1);
        ArrayList<Integer> one = new ArrayList<Integer>();
        one.add(1);
        ArrayList<Integer> two = new ArrayList<Integer>();
        two.add(2);
        ArrayList<Integer> left = new ArrayList<Integer>(one);
        ArrayList<Integer> right = new ArrayList<Integer>(a);
        ArrayList<Integer> length = new ArrayList<Integer>(one);
        int count = 0;
        while(!equal(max(sub(a, one), one), one) && count<100)
        {
            //System.out.println("!");
            count++;
            length = div(add(right, left), two);
            if(equal(pow(length, two), a))
            {
                return  length;
            }
            if(equal(max(pow(length, two), a), a))
            {
                left = length;
            }
            else {
                right = length;
            }
        }
        return length;
    }

    private static ArrayList<Integer> ChineseTheorem(){
        /* Знайти число, що при діленні на 4 дає в залишку 1,
         при діленні на 5 дає в залишку 3, а при діленні на 7 дає в залишку 2.*/
        ArrayList<Integer> one = new ArrayList<>();
        one.add(1);

        ArrayList<Integer> x1 = new ArrayList<>(convert("16"));
        ArrayList<Integer> modx1 = new ArrayList<>(convert("17"));
        ArrayList<Integer> x2 = new ArrayList<>(convert("22"));
        ArrayList<Integer> modx2 = new ArrayList<>(convert("23"));
        ArrayList<Integer> x3 = new ArrayList<>(convert("30"));
        ArrayList<Integer> modx3 = new ArrayList<>(convert("31"));


        ArrayList<Integer> M1 = new ArrayList<>();
        ArrayList<Integer> M2 = new ArrayList<>();
        ArrayList<Integer> M3 = new ArrayList<>();

        ArrayList<Integer> M1copy = new ArrayList<>();
        ArrayList<Integer> M2copy = new ArrayList<>();
        ArrayList<Integer> M3copy = new ArrayList<>();

        ArrayList<Integer> Answer = new ArrayList<>();

        M1 = mult(modx2, modx3);
        M1copy = M1;
        M2 = mult(modx1, modx3);
        M2copy = M2;
        M3 = mult(modx1, modx2);
        M3copy = M3;
        while(!equal(mod(M1, modx1), one))
        {
            M1 = add(M1, M1copy);
            System.out.println(M1);
        }
        while(!equal(mod(M2, modx2), one))
        {
            M2 = add(M2, M2copy);
            System.out.println(M2);
        }
        while(!equal(mod(M3, modx3), one))
        {
            M3 = add(M3, M3copy);
            System.out.println(M3);
        }

        Answer = mod((add(add(mult(M1, x1), mult(M2, x2)), mult(M3, x3))), mult(M1copy, modx1));
        return Answer;
    }


    public static void main(String[] args) {
        String str  = "24523748428";
        String str2  = "6500000";
        String str3 = "98723450723";
        print(div(mult(convert(str), convert(str2)), convert(str2)));

        //print(powmod(convert(str), convert(str2), convert(str3)));

        //print(ChineseTheorem());
    }
}

