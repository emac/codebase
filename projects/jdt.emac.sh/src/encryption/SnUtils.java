package encryption;

/**
 * @author Emac
 * @since 2016-03-26
 */
public class SnUtils {

    private static final String SEED = "1111100110011011101001011111001";

    /**
     * 对seq加密生成随机数(可逆).算法:
     * <ol>
     * <li>由digits确定随机种子值S</li>
     * <li>将seq与S进行异或运算得到值cipher</li>
     * <li>将cipher首尾按位互换</li>
     * </ol>
     *
     * @param seq    顺序递增的正整数
     * @param digits seq取值范围内的最大值的二进制位数
     * @return
     */
    public static int encrypt(int seq, byte digits) {
        if (digits > SEED.length()) {
            return -1;
        }

        int seed = Integer.valueOf(SEED.substring(SEED.length() - digits), 2);
        int cipher = seq ^ seed;
        for (int i = 0; i < digits / 2; i++) {
            cipher = swap(cipher, i, digits - i);
        }
        return cipher;
    }

    /**
     * 对cipher解密获取原始顺序值.算法:
     * <ol>
     * <li>由digits确定随机种子值S</li>
     * <li>将cipher首尾按位互换得到值seq</li>
     * <li>将seq与S进行异或运算</li>
     * </ol>
     *
     * @param cipher 随机正整数
     * @param digits 原始seq取值范围内的最大值的二进制位数
     * @return
     */
    public static int decrypt(int cipher, byte digits) {
        if (digits > SEED.length()) {
            return -1;
        }

        int seed = Integer.valueOf(SEED.substring(SEED.length() - digits), 2);
        int plain = cipher;
        for (int i = 0; i < digits / 2; i++) {
            plain = swap(plain, i, digits - i);
        }
        plain ^= seed;
        return plain;
    }

    /**
     * 互换num的第pos1位和第pos2位.
     *
     * @param num  非负整数
     * @param pos1 不大于31的非负整数
     * @param pos2 不大于31的非负整数
     * @return
     */
    private static int swap(int num, int pos1, int pos2) {
        if (pos1 == pos2) {
            return num;
        }

        int x = (num >> pos1) & 1;
        int y = (num >> pos2) & 1;
        if (x == y) {
            return num;
        }

        int _num = num;
        if (x == 1) {
            // pos1: 1->0, pos2: 0->1
            _num &= ~(1 << pos1);
            _num |= 1 << pos2;
        } else {
            // pos1: 0->1, pos2: 1->0
            _num &= ~(1 << pos2);
            _num |= 1 << pos1;
        }
        return _num;
    }
}
