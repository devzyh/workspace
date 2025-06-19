package cn.devzyh.demo.pay.mode;

public class FingerprintPayModeImpl implements IPayMode {
    @Override
    public void auth(String uid) {
        System.out.println("指纹验证成功");
    }
}
