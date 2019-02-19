package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * https://leetcode-cn.com/problems/encode-and-decode-tinyurl/
 *
 * @author Emac
 * @since 2018-10-15
 */
public class Codec02 {

    private static final String DICT = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final Integer DICT_LENGTH = DICT.length();

    private static final String BASE_URL = "http://tinyurl.com/";

    private static final Integer BASE_URL_LENGTH = BASE_URL.length();

    private static final Integer LENGTH = 6;

    private Map<String, String> urls = new HashMap();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String code;
        do {
            code = _genRandomCode(LENGTH);
            if (!urls.containsKey(code)) {
                break;
            }
        } while (true);
        urls.put(code, longUrl);
        return BASE_URL + code;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return urls.get(shortUrl.substring(BASE_URL_LENGTH));
    }

    private String _genRandomCode(Integer length) {
        Random random = new Random();
        StringBuilder sbuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(DICT_LENGTH);
            sbuilder.append(DICT.charAt(idx));
        }
        return sbuilder.toString();
    }

    public static void main(String[] args) {
        // Your Codec object will be instantiated and called as such:
        Codec02 codec = new Codec02();
        System.out.println(codec.decode(codec.encode("https://leetcode.com/problems/design-tinyurl")));
    }
}
