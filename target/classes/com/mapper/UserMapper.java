package com.mapper;

import com.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    /**
     * <select id="selectAll" resultMap="BaseMapper1">
     *     select * from users
     * </select>
     */
    List<User> selectAll();
}
