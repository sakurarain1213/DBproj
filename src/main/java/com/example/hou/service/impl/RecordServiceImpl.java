package com.example.hou.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hou.entity.Record;
import com.example.hou.entity.UserInfo;
import com.example.hou.mapper.RecordMapper;
import com.example.hou.mapper.UserInfoMapper;
import com.example.hou.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hsin
 * @since 2023-04-08
 */
@Service
public class RecordServiceImpl /*extends ServiceImpl<RecordMapper, Record> */implements RecordService {

    @Autowired
    RecordMapper recordMapper;//不要忘记注入


    @Override
    public String recordAddService(Record record){

        //username   txt   start +end  time
        //add的时候不用判断已存在的教师记录 但是要判断非空
        //疯狂debug
        //测试   注意是add  不是insert

        //record.setTxtFile("测试嗷嗷");

        //System.out.println(record.getUsername()+"?????????");
       //System.out.println(record.getTxtFile()+"?????????");
        if (record.getTxtFile()==null) {
            //System.out.println(record.getTxtFile()+"?????????");//测试语句
             return "缺少文本信息";
        } else if (record.getEndTime()==null) {
            return "缺少时间信息";
        }
        else{
            recordMapper.insert(record);
            return "SUCCESS";
        }


     // record.setTxtFile("测试嗷嗷");
      // record.setUsername("3");
      //  recordMapper.insert(record);
      //  return "yes";
}


    @Override
    public String recordGetService(Record record) {
            return "test";
        }



}
