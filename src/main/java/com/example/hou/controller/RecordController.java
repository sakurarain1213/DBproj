package com.example.hou.controller;


import com.example.hou.entity.Record;
import com.example.hou.entity.UserInfo;
import com.example.hou.result.Result;
import com.example.hou.result.ResultUtil;
import com.example.hou.service.RecordService;
import com.example.hou.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hsin
 * @since 2023-04-08
 */
@RestController
@Slf4j
@RequestMapping("/record")
public class RecordController {

    @Autowired
    RecordService recordService;//不要忘记注入

    @RequestMapping("/add")
    public Result recordAdd(@RequestBody Record record) {
        String msg = recordService.recordAddService(record);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("语音文本上传成功");
        } else {
            return ResultUtil.error(msg);
        }
    }
}

