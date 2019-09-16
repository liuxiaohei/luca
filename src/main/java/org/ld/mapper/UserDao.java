package org.ld.mapper;

import org.ld.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by alen.c on 2018/4/24.
 */
@Mapper
public interface UserDao {

    /**
     * case 1，可以不写com/example/mapper/SmUserMapper.xml和interface的实现类，
     *      我们可以直接使用@Select注解写sql来直接进行操作数据库，这个就简单了。[
     *      另外，还有@Insert、@Update、@Delete等注解
     */

    int insert(User obj);

    User login(String username,String password);

    User findByUsername(String username);


}
