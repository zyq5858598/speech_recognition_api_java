package com.zyq.util;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * JKhaled created by yunqisong@foxmail.com 2018-3-18
 * FOR : 文件相关工具类
 */
@Slf4j
public class FileKit {
  /**
   * 文件上传
   *
   * @param file
   * @param uploadPath
   * @return
   */
  public static String saveUploadFile(MultipartFile file, String uploadPath) {
    String fileName = null;
    if (! file.isEmpty()) {
      fileName = file.getOriginalFilename();
      log.debug("上传的文件名为：" + fileName);
      String suffixName = fileName.substring(fileName.lastIndexOf("."));
      log.debug("上传的后缀名为：" + suffixName);
      fileName = RandomUtil.randomUUID() + suffixName;
      File dest = new File(uploadPath + fileName);
      if (! dest.getParentFile().exists()) {
        dest.getParentFile().mkdirs();
      }
      try {
        file.transferTo(dest);
      } catch (Exception e) {
        log.error("Fail to save file {} ", dest.getAbsolutePath(), e);
        return null;
      }
    }

    return fileName;
  }
  public static String getUTF8StringFromGBKString(String gbkStr) {
    try {
      return new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new InternalError();
    }
  }

  public static byte[] getUTF8BytesFromGBKString(String gbkStr) {
    int n = gbkStr.length();
    byte[] utfBytes = new byte[3 * n];
    int k = 0;
    for (int i = 0; i < n; i++) {
      int m = gbkStr.charAt(i);
      if (m < 128 && m >= 0) {
        utfBytes[k++] = (byte) m;
        continue;
      }
      utfBytes[k++] = (byte) (0xe0 | (m >> 12));
      utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
      utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
    }
    if (k < utfBytes.length) {
      byte[] tmp = new byte[k];
      System.arraycopy(utfBytes, 0, tmp, 0, k);
      return tmp;
    }
    return utfBytes;
  }
}
