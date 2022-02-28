package com.harukaze.front.util;

import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @PackageName: com.harukaze.front.util
 * @ClassName: CryptoAESUtil
 * @Description:
 * @Author: doki
 * @Date: 2022/1/4 18:30
 */

public class CryptoAESUtil {
    public static String decrypt(String sStr, String sKey) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        if (sStr == null || sStr.length() <= 0) {
            return null;
        }
        if (sKey == null || sKey.length() <= 0) {
            sKey = "JcRYQ4yt3i^9%mdt";
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sStr);
        try {
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
