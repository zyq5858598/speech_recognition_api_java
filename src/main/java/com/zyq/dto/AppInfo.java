package com.zyq.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JKhaled created by yunqisong@foxmail.com 2018-3-18
 * FOR : AppInfo Bean
 */
@Data
@Component
@ConfigurationProperties(prefix = "appInfo")
public class AppInfo {

  private String filePath;

  private String webPath;
}
