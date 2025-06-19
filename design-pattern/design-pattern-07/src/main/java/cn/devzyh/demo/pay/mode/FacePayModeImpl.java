package cn.devzyh.demo.pay.mode;

public class FacePayModeImpl implements IPayMode {
    @Override
    public void auth(String uid) {
        System.out.println("面部识别成功");
    }
}
