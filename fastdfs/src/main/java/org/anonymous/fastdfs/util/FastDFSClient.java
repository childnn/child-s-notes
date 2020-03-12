//package org.anonymous.fastdfs.util;
//
//import org.csource.common.NameValuePair;
//import org.csource.fastdfs.*;
//
///**
// * @author child
// * 2019/6/3 22:24
// */
//public abstract class FastDFSClient {
//    private static final String PREFIX = "/";
//    private static StorageClient1 storageClient;
//
//    public static void initClassPathConfig(String classPathConfig) throws Exception {
//        //获取文件在本地磁盘的绝对路径
//        if (classPathConfig.startsWith("classpath:")) {
//            classPathConfig = classPathConfig.replace("classpath:", FastDFSClient.class.getResource("/").getPath());
//        } else {
//            classPathConfig = FastDFSClient.class.getResource("/").getPath() + classPathConfig;
//        }
////        System.out.println("classPathConfig = " + classPathConfig);  //这里获取的是 编译之后 target/classes 目录下的 配置文件
//        ClientGlobal.init(classPathConfig); //加载 配置文件
//        //创建追踪者客户端
//        TrackerClient trackerClient = new TrackerClient();
//        //通过 追踪者客户端 获取到 追踪者服务端
//        TrackerServer trackerServer = trackerClient.getConnection();
//        //声明一个存储服务端
//        StorageServer storageServer = null;
//        //获取 存储客户端对象
//        storageClient = new StorageClient1(trackerServer, storageServer);
//    }
//
//    //------------------------------------------------------
//    // 根据文件的绝对路径上传文件
//    //----------------------------------------------------
//
//    /**
//     * @param local_filename 文件全路径- 本地磁盘的绝对路径
//     * @param extName  文件扩展名，不包含（.）
//     * @param metas    文件扩展信息
//     * @return eg: group1/M00/00/00/wKgghVz2VX6AZmkjAAkThEsGS5o888.jpg
//     */
//    public static String uploadFile(String local_filename, String extName, NameValuePair[] metas) throws Exception {
//        return PREFIX + storageClient.upload_file1(local_filename, extName, metas);
//    }
//
//    public static String uploadFile(String local_filename) throws Exception {
//        return uploadFile(local_filename, null, null);
//    }
//
//    public static String uploadFile(String local_filename, String extName) throws Exception {
//        return uploadFile(local_filename, extName, null);
//    }
//
//    //------------------------------------------------------
//    // 根据文件内容的 byte 数组上传文件
//    //----------------------------------------------------
//
//    /**
//     * @param fileContent 文件的内容，字节数组
//     * @param extName     文件扩展名
//     * @param metas       文件扩展信息
//     * @return 文件在 fastdfs 上的 fileId -- eg: group1/M00/00/00/wKgghVz2VX6AZmkjAAkThEsGS5o888.jpg
//     */
//    public static String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
//        return PREFIX + storageClient.upload_file1(fileContent, extName, metas);
//    }
//
//    /**
//     * @param fileContent 文件 byte 数组
//     */
//    public static String uploadFile(byte[] fileContent) throws Exception {
//        return uploadFile(fileContent, null, null);
//    }
//
//    /**
//     * @param fileContent 文件 byte 数组
//     * @param extName     扩展名 eg: jpg
//     */
//    public static String uploadFile(byte[] fileContent, String extName) throws Exception {
//        return uploadFile(fileContent, extName, null);
//    }
//}
