package cn.devzyh.demo.threadlocal;

import java.util.Collections;
import java.util.List;

/**
 * 使用ThreadLocal传递用户信息
 *
 * @author devzyh
 */
public class UserContextTest {

    public static ThreadLocal<User> userContext = new ThreadLocal<>();

    public static void main(String[] args) {

        // 新请求过来验证用户信息后，保存到当前线程中
        User user = new User();
        user.setUsername("admin");
        user.setHasAuth(true);
        user.setRoles(Collections.singletonList("admin"));
        userContext.set(user);

        // 在其他服务类中直接获取当前用户信息
        System.out.println(userContext.get().toString());

        // 用完ThreadLocal对象记得进行手动回收，避免value的内存泄漏
        userContext.remove();
    }

    static class User {
        private String username;
        private boolean hasAuth;
        private List<String> roles;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isHasAuth() {
            return hasAuth;
        }

        public void setHasAuth(boolean hasAuth) {
            this.hasAuth = hasAuth;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", hasAuth=" + hasAuth +
                    ", roles=" + roles +
                    '}';
        }
    }

}
