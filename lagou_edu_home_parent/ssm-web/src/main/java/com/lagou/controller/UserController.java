package com.lagou.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lagou.domain.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findALlUserByPage(@RequestBody UserVo userVo) {

        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        return new ResponseResult(true,200,"分页多条件查询成功",pageInfo);
    }


    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User user1 = userService.login(user);

        if (user1 != null) {
            // 保存用户id及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", user1.getId());

            // 将查询出来的信息响应给前台
            Map<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id", user1.getId());

            // 将查询出来的user，也存到map中
            map.put("user",user1);


            return new ResponseResult(true, 1, "登陆成功", map);
        } else {
            return new ResponseResult(true,400,"用户名或密码错误",null);
        }
    }



    /*
  根据ID查询用户当前角色
   */

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationById(Integer id) {

        List<Role> userRelationById = userService.findUserRelationRoleById(id);

        return new ResponseResult(true,200,"分配角色回显成功",userRelationById);
    }

    /*
    分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {

        userService.userContextRole(userVo);

        return new ResponseResult(true,200,"分配角色成功",null);

    }

    /*
    获取用户权限，惊醒菜单动态展示
     */

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {

        // 1.获取请求头中的token
        String header_token = request.getHeader("Authorization");

        // 2.获取session中token
        String session_token = (String) request.getSession().getAttribute("access_token");

        // 3.判断token是否一致
        if(header_token.equals(session_token)) {

            // 获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            // 调用service，进行菜单信息查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        } else {
            ResponseResult responseResult = new ResponseResult(false, 400, "获取信息菜单表失败", null);
            return responseResult;
        }
    }
}
