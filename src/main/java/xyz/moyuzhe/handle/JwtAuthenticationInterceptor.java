package xyz.moyuzhe.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.moyuzhe.entity.User;
import xyz.moyuzhe.service.UserService;
import xyz.moyuzhe.utils.JwtUtils;
import xyz.moyuzhe.utils.PassToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        System.out.println("进入jwt");
        // 从请求头中取出 token  这里需要和前端约定好把jwt放到请求头一个叫token的地方
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            System.out.println("跳过验证");
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //默认全部检查
        else {
            System.out.println("被jwt拦截需要验证");
            // 执行认证
            if (token == null) {
                System.out.println("验证失败");
                httpServletRequest.setAttribute("msg","请登录");
                String url = httpServletRequest.getContextPath()+"/hello";
                System.out.println(url);
                httpServletResponse.sendRedirect(url);//拦截后跳转的方法
                //这里其实是登录失效,没token了   这个错误也是我自定义的，读者需要自己修改
                return false;
//                throw new Exception();
            }

            // 获取 token 中的 user Name
            String userId = JwtUtils.getAudience(token);

            //找找看是否有这个user   因为我们需要检查用户是否存在，读者可以自行修改逻辑
            User user = userService.getById(userId);

            if (user == null) {
                //这个错误也是我自定义的
                throw new Exception();
            }

            // 验证 token
            JwtUtils.verifyToken(token, userId);

            //获取载荷内容
            String userName = JwtUtils.getClaimByName(token, "userName").asString();
            String realName = JwtUtils.getClaimByName(token, "realName").asString();

            //放入attribute以便后面调用
            httpServletRequest.setAttribute("userName", userName);
            httpServletRequest.setAttribute("realName", realName);


            return true;

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
