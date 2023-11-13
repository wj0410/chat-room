package io.github.wj0410.chatroom.auth.controller;

import io.github.wj0410.chatroom.auth.service.TokenService;
import io.github.wj0410.cloudbox.tools.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * token 控制
 *
 * @author XZ
 */
@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;

//    @PostMapping("login")
//    public R<?> login(@RequestBody LoginBody form) {
//        int loginTpe = form.getLoginType();
//        // userType为0表示平台端，用户名或手机号不存在就直接返回用户不存在
//        String userType = form.getUserType();
//        // 用手机号码做用户名会重复，拼接用户名;cellphone存放用户输入的用户名
//        form.setCellphone(form.getUsername());
//        form.setUsername(form.getUsername() + "_" + form.getUserType());
//        LoginUser userInfo;
//        // 密码登录
//        if (LoginType.CAPTCHA.getCode() == loginTpe) {
//            userInfo = sysLoginService.login(form.getUsername(), form.getCellphone(), form.getPassword(), userType);
//        }
//        // 短信登录
//        else if (LoginType.SMS.getCode() == loginTpe) {
//            userInfo = sysLoginService.loginBySms(form.getUsername(), form.getCellphone(), userType);
//        } else {
//            return R.fail("验证码类型错误");
//        }
//
//        if (userInfo == null || userInfo.getSysUser() == null) {
//            return R.fail("用戶不存在");
//        }
//
//        // 获取登录token
//        return R.ok(tokenService.createToken(userInfo));
//    }
//
//    @PostMapping("logout")
//    public R<?> logout(HttpServletRequest request) {
//        String token = SecurityUtils.getToken(request);
//        if (StringUtils.isNotEmpty(token)) {
//            String username = JwtUtils.getUserName(token);
//            // 删除用户缓存记录
//            AuthUtil.logoutByToken(token);
//            // 记录用户退出日志
//            sysLoginService.logout(username);
//        }
//        return R.ok();
//    }
//
//    @PostMapping("refresh")
//    public R<?> refresh(HttpServletRequest request) {
//        LoginUser loginUser = tokenService.getLoginUser(request);
//        if (StringUtils.isNotNull(loginUser)) {
//            // 刷新令牌有效期
//            tokenService.refreshToken(loginUser);
//            return R.ok();
//        }
//        return R.ok();
//    }
//
//    @PostMapping("register")
//    public R<?> register(@RequestBody RegisterBody registerBody) {
//        // 用户注册
//        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword(), registerBody.getCellphone());
//        return R.ok();
//    }
}
