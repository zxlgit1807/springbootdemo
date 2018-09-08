package com.zxl.commons.emuns;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Auther: ZXL
 * @Date: 2018/9/8
 * @Description:
 */
@Slf4j
public class CryptoUtils {

    /**
     * MD5加密(通过用户名加盐)
     * @param str
     * @return
     */
    public static String encoderByMd5(String loginName, String str) {
        String newStr = null;
        if (StringUtils.isNoneBlank(str)) {
            try {
                newStr = new SimpleHash("MD5",str.getBytes(),  ByteSource.Util.bytes(loginName), 2).toHex();
            } catch (Exception e) {
                log.error("密码加密失败{}",e);
            }
        }
        return newStr;
    }
}
