//package org.anonymous.fastdfs;
//
//import org.anonymous.fastdfs.util.FastDFSClient;
//import org.junit.Test;
//
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
///**
// * @author child
// * 2019/6/3 22:28
// */
//public class Demo01 {
//
//    @Test
//    public void test() {
//        URL resource = Demo01.class.getResource("/");
//        System.out.println(resource); //file:/D:/Develop/IDEA_Project/project01/fastdfs-demo/target/classes/
//        String path = resource.getPath();
//        System.out.println(path); ///D:/Develop/IDEA_Project/project01/fastdfs-demo/target/classes/
//    }
//
//    @Test //直接上传本地文件, 参数: 文件绝对路径
//    public void test1() throws Exception {
//        FastDFSClient.initClassPathConfig("fdfs_client.conf");
//        String fileId = FastDFSClient.uploadFile("D:\\web\\新建文件夹/IU.jpg");
//        System.out.println("fileId = " + fileId); //   /group1/M00/00/01/wKgghVz4wfSAFaXLAAkThEsGS5o902.jpg
//    }
//
//    @Test //传递文件的 byte 数组
//    public void test2() throws Exception {
//        FastDFSClient.initClassPathConfig("fdfs_client.conf");
//        byte[] bytes = Files.readAllBytes(Path.of("D:\\web\\新建文件夹/十元_2.jpg"));
////        System.out.println("Arrays.toString(bytes) = " + Arrays.toString(bytes));
//        String fileId = FastDFSClient.uploadFile(bytes, "jpg");
//        System.out.println("fileId = " + fileId);
//    }
//}
