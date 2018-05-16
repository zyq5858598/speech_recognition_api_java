package com.zyq.controller;

import cn.hutool.core.util.StrUtil;
import com.zyq.dto.AppInfo;
import com.zyq.dto.Result;
import com.zyq.enums.ResultEnum;
import com.zyq.service.VoiceService;
import com.zyq.util.FileKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


/**
 * JKhaled created by yunqisong@foxmail.com 2018-3-18
 * FOR : 后管-文件相关 API
 */

@RestController
@RequestMapping("file")
@SuppressWarnings({"unchecked"})
public class FileController extends BaseController {



  @Autowired
  private VoiceService voiceService;

  @PostMapping("upload")
  public Result upload(@RequestParam("file") MultipartFile file) {
    return success(voiceService.getList(file));
  }

  @GetMapping("str2list/{text}")
  public Result str2list(@PathVariable @Valid String text) {
    return success(voiceService.str2list(text));
  }
}
