package cc.cleverfan.web.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码与解码操作
 */
public class URLUtil {

    /**
     * 将URL编码
     * @param source
     * @return
     */
    public static String encodeURL(String source) {
        String target = null;
        try {
            target = URLEncoder.encode(source,"UTF-8");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 将URL解码
     * @param source
     * @return
     */
    public static String decodeURL(String source) {
        String target = null;
        try {
            target = URLDecoder.decode(source,"UTF-8");
        }catch (Exception e) {
           e.printStackTrace();
        }
        return target;
    }
}
