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

import java.util.List;

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


    @RequestMapping("/get")
    //因为返回的是一个list  所以消息需要根据新的格式自定义
    public Result recordGet(@RequestBody Record record) {
        List<Record> l = recordService.recordGetService(record);
        if (l.size()>0) {
            return ResultUtil.success(l);//强大的result类可以自定义返回类型
        }
        else {
            return ResultUtil.error("缺少查询条件或查询结果为空");
        }
    }



}

