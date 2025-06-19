package cn.devzyh.demo;

/**
 * 计算两个坐标点之间的距离
 */
public class DistanceCalculator {

    private static final double EARTH_RADIUS = 6371.0; // 地球平均半径，单位为千米

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 将经纬度转换为弧度
        double radLat1 = Math.toRadians(lat1);
        double radLon1 = Math.toRadians(lon1);
        double radLat2 = Math.toRadians(lat2);
        double radLon2 = Math.toRadians(lon2);

        // 应用Haversine公式计算两个经纬度之间的距离
        double dLon = radLon2 - radLon1;
        double dLat = radLat2 - radLat1;
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public static void main(String[] args) {
        double lat1 = 40.7128; // 第一个坐标点的纬度
        double lon1 = -74.0060; // 第一个坐标点的经度
        double lat2 = 34.0522; // 第二个坐标点的纬度
        double lon2 = -118.2437; // 第二个坐标点的经度

        double distance = calculateDistance(lat1, lon1, lat2, lon2);
        System.out.println("两个坐标点之间的距离为：" + distance + "千米");
    }

}
