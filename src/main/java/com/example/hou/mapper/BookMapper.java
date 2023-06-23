package com.example.hou.mapper;

import com.example.hou.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hou.entity.Temp_hot_destination;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    Book searchByOrdernum (String the_Ordernum);

    List<Temp_hot_destination>  hotDestination();

}
