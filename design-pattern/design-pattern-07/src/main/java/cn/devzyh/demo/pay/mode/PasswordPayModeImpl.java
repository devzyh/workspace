package cn.devzyh.demo.pay.mode;

public class PasswordPayModeImpl implements IPayMode {
    @Override
    public void auth(String uid) {
        System.out.println("密码验证成功");
    }
}
