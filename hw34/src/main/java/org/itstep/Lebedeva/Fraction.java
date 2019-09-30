package org.itstep.Lebedeva;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fraction {
    private int numerator;//числитель
    private int denominator;//знаменатель

    @RunIt(a = 4, b = 18)
    public Fraction addition(Fraction value) {
        Fraction fraction = new Fraction();
        fraction.denominator = denominator * value.denominator;
        fraction.numerator = numerator * value.denominator + value.numerator * denominator;
        fraction.nod();
        getResult(" + ", value, fraction);
        return fraction;
    }

    @RunIt(a = 0, b = 2)
    public Fraction subtraction(Fraction value) {
        Fraction fraction = new Fraction();
        fraction.denominator = denominator * value.denominator;
        fraction.numerator = numerator * value.denominator - value.numerator * denominator;
        fraction.nod();
        getResult(" - ", value, fraction);
        return fraction;
    }

    @RunIt(a = 3, b = 4)
    public Fraction multiplication(Fraction value) {
        Fraction fraction = new Fraction();
        fraction.denominator = denominator * value.denominator;
        fraction.numerator = numerator * value.numerator;
        fraction.nod();
        getResult(" * ", value, fraction);
        return fraction;
    }

    @RunIt(a = 4, b = 5)
    public Fraction division(Fraction value) {
        Fraction fraction = new Fraction();
        fraction.denominator = denominator * value.numerator;
        fraction.numerator = numerator * value.denominator;
        fraction.nod();
        getResult(" : ", value, fraction);
        return fraction;
    }

    public void nod() {//наименьший общий делитель
        int a = numerator;
        int b = denominator;
        while (a != b) {
            if (a > b) a = a - b;
            else b = b - a;
        }
        numerator = numerator / a;
        denominator = denominator / a;
    }

    public void getResult(String string, Fraction value, Fraction fraction) {
        System.out.println(numerator + "/" + denominator + string + value.numerator + "/" +
                value.denominator + " = " + fraction.numerator + "/" + fraction.denominator);
    }
}
