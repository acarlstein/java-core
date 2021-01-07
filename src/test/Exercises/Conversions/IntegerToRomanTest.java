package Exercises.Conversions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IntegerToRomanTest {

    // TODO: Find out why this doesn't work: @Parameterized.Parameters(name = "{0} {1}")
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "I", 1 },  { "III", 3 }, { "IV", 4 }, { "V", 5 },
                { "VI", 6 }, { "VII", 7 }, { "VIII", 8 }, { "IX", 9 },
                { "X", 10 }, { "XIII", 13 }, { "XIV", 14 }, { "XV", 15 },
                {"XVI", 16}, {"XVIII", 18}, {"XIX", 19}, {"XX", 20},
                {"XXX", 30}, {"XL", 40}, {"L", 50}, {"LX", 60},
                {"LXXX", 80}, {"XC", 90}, {"XCIX", 99}, {"C", 100},
                {"CD", 400}, {"D", 500}, {"DC", 600}, {"DCCC", 800},
                {"CM", 900}, {"M", 1000}, {"MI", 1001}, {"MCMXCIX", 1999},
                {"MM", 2000}, {"MMMD", 3500}, {"MMMMMD", 5500}
        });
    }

    private final String romanSymbol;

    private final int value;

    public IntegerToRomanTest(String romanSymbol, int value){
        this.romanSymbol = romanSymbol;
        this.value = value;
    }

    @Test
    public void integerToRomanTest(){
        Assert.assertEquals(romanSymbol, convertToRoman(value));
    }

    static public String convertToRoman(int value){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < value; ++i){
            sb.append("I");
        }
        return sb.toString()
                .replaceAll("IIIII", "V")
                .replaceAll("IIII", "IV")
                .replaceAll("VV", "X")
                .replaceAll("VIV", "IX")
                .replaceAll("XXXXX", "L")
                .replaceAll("XXXX", "XL")
                .replaceAll("LL", "C")
                .replaceAll("LXL", "XC")
                .replaceAll("CCCCC", "D")
                .replaceAll("CCCC", "CD")
                .replaceAll("DD", "M")
                .replaceAll("DCD", "CM");
    }
}
