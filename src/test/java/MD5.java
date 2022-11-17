import icu.kevin557.eq.utils.CryptUtils;

import java.util.Arrays;

public class MD5 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(CryptUtils.string2Md5("TEST")));
    }
}
