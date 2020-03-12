//package org.anonymous.fastdfs;
//
//import org.csource.fastdfs.*;
//
///**
// * @author child
// * 2019/6/3 20:09
// * fastdfs
// *   client， tracker server, storage server
// *   客户端， 追踪器(调度,负载均衡)， 存储服务器
// * 基本执行流程
// * 上传
// *  1. storage server 定时向 tracker 上报状态信息 - tracker 必须实时的了解 存储节点的状态
// *  2. client 发送上传请求,
// *  3. tracker 在自己的记录中查询可用的 storage
// *  4. tracker 向 client 返回查询到的信息: storage 的 ip + port
// *  5. client 按照指定的 ip/port 上传文件
// *  6. storage 按上传的文件生成 file_id, 并将 文件写入磁盘
// *  7. storage 将 file_id (group+文件名)返回给 client - 就是返回的字符串数组
// *  8. 客户端接收数据,进行操作 -- http://192.168.32.85/group1/M00/00/00/wKggVVz1H1WAbEZEAAkThEsGS5o329.jpg 即可访问上传的文件
// * 下载
// *  1. storage 定时向 tracker 上报状态信息
// *  2. client 发起 下载连接请求(请求时,带着需要下载的文件信息)
// *  3. tracker 查询可用 storage, 将 ip:port 返回给 client
// *  4. client 根据文件信息(file_id: 组名,路径,文件名), 找到 指定 ip:port 的 storage
// *  5. storage 查找文件, 返回 file_content 给 client
// */
//public class Demo {
// //http://192.168.32.133/group1/M00/00/00/wKgghVz1zJ2APS25AAqWoycCr-w917_big.jpg
//    public static void main(String... args) throws Exception {
//        //加载配置文件
//        ClientGlobal.init("fastdfs-demo\\src\\main\\resources\\fdfs_client.conf");
//        //构建一个管理者客户端
//        TrackerClient client = new TrackerClient();
//        //获取服务端
//        TrackerServer trackerServer = client.getConnection();
//        //声明一个存储服务端
//        StorageServer storageServer = null;
//        //获取 存储客户端对象
//        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//        //上传文件
//        String[] strings = storageClient.upload_file("D:\\web/2.jpg", "jpg", null);
//        for (String string : strings) {
//            //group1
//            //M00/00/00/wKggVVz1HcWAMo6pAAkThEsGS5o976.jpg
//            //M00/00/00/wKggVVz1H1WAbEZEAAkThEsGS5o329.jpg
//            //同一个文件： 每次生成的名字不一样
//            System.out.println(string);
//        }
//    }
//}
