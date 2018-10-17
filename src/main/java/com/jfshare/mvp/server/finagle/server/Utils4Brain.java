package com.jfshare.mvp.server.finagle.server;

import com.twitter.concurrent.BridgedThreadPoolScheduler;
import com.twitter.concurrent.Scheduler;
import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Service;
import com.twitter.finagle.Thrift;
import com.twitter.finagle.loadbalancer.Balancers;
import com.twitter.finagle.loadbalancer.LoadBalancerFactory;
import com.twitter.finagle.service.RetryBudget;
import com.twitter.finagle.util.Rngs;
import com.twitter.util.Duration;
import io.netty.util.internal.StringUtil;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.twitter.util.Function.func;

public final class Utils4Brain {


    /**
     * 默认 zk 地址  ---zk 注册中心的地址  跟  服务器参数配置的不在一块
     *  ip:port
     *  127.0.0.1:2181
     */
    public static final String Zk_Address ="";

    /**
     * 工作的线程个数
     */
    public static final int Thread_Numbers=400;

    /**
     *  是否临时将 所有的 客户端ip 变成测试ip
     */
//    public static final String  changeAllClientAddr2TestIp=null;
    public static final String  changeAllClientAddr2TestIp="39.106.147.35";// 35的内网ip  39.106.147.35


    private static final Logger LOGGER = LoggerFactory.getLogger(Utils4Brain.class);

    public static String buildConsumerPath(String servicePath, String zookeeperDest){
        return "zk2!" + zookeeperDest + "!" + servicePath;
    }

    /**
     * scheme!host!path!shardId
     * @param servicePath
     * @param zookeeperDest
     * @return
     */
    public static String buildProviderPath(String servicePath, String zookeeperDest){
        return "zk!" + zookeeperDest + "!" + servicePath + "!0";
    }

    public static ListeningServer createThriftServer(String zkAddress ,int port, String serviceName, Service<byte[], byte[]> service) {
        LOGGER.info("createThriftServer() called with: zkAddress = [" + zkAddress + "], port = [" + port + "], serviceName = [" + serviceName + "], service = [" + service + "]");

        String zkAddressTest =null;
        if(!StringUtil.isNullOrEmpty(Zk_Address)){
            zkAddressTest = Zk_Address;
        }else{
            zkAddressTest=zkAddress;
        }
        String zkPath =Constants4Brain.SERVICE_MAP.get(serviceName);
        String localAddress = getLocalAddress().getHostAddress();

        LOGGER.info( "createThriftServer() called with: port = [" + port + "], serviceName = [" + serviceName + "], service = [" + service + "]"
                + "], zkPath = [" + zkPath + "]"
                + "], localAddress = [" + localAddress + "]");

        // 增加线程池 大小
        Scheduler.setUnsafe(new BridgedThreadPoolScheduler("finagle-worker",
                func(threadFactory -> Executors.newFixedThreadPool(Thread_Numbers, threadFactory))));


        ListeningServer server = Thrift.server()

//                .withRequestTimeout(Duration.apply(30, TimeUnit.SECONDS))
//                .withAdmissionControl().concurrencyLimit(1000, 10)
//                .withBufferedTransport()
//                .withProtocolFactory(protocolFactory)
//                .withSession().maxIdleTime(Duration.apply(30,TimeUnit.SECONDS))
//                .withSession().maxLifeTime(Duration.apply(60,TimeUnit.SECONDS))
                .withLabel(serviceName)
                .serveAndAnnounce(
                        buildProviderPath(zkPath, zkAddressTest),
                        // bind to local address
                        localAddress + ":" + port,
                        service);
        return server;
    }

    /**
     * 创建thrift 客户端 --- 从zk 中获取服务器的地址
     * @param zkAddress  zk 地址  127.0.0.1：2181 例如
     * @param serviceName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createThriftClient(String zkAddress,String serviceName, Class<T> clazz) {
        LOGGER.info( "createThriftClient() params called with: zkAddress = [" + zkAddress + "], serviceName = [" + serviceName + "], clazz = [" + clazz + "]");


        String zkAddressTest=null;

        // 优先找默认的， 这样如果本地测试就直接 用本地的zk 服务信息
        if(!StringUtil.isNullOrEmpty(Zk_Address)){
            zkAddressTest = Zk_Address;
        }else{
            zkAddressTest=zkAddress;
        }

        //Test LoadBalancer
//        LoadBalancerFactory loadBalancerFactory = Balancers.p2c(5, Rngs.threadLocal());
//        Vector<EndpointFactory<String, String>> vec =
//                new VectorBuilder<EndpointFactory<String, String>>().result();
//        loadBalancerFactory.newBalancer(null, null, null);

        String str = buildConsumerPath(Constants4Brain.SERVICE_MAP.get(serviceName), zkAddress);


        //测试开发使用 ，本地测试使用
        //获取到服务对应的端口
        if(!StringUtil.isNullOrEmpty(changeAllClientAddr2TestIp)){
            String port = Constants4Brain.SERVICE_IP_PORT.get(serviceName);
            str=changeAllClientAddr2TestIp+":"+port;
        }

        LOGGER.info( "final  createThriftClient() called with:[serviceName]= "+serviceName+" zkAddress = [" + zkAddressTest + "]" +" str [] "+str);

        TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
        T client = Thrift.client()
                .withProtocolFactory(protocolFactory)
                .withRetryBudget(RetryBudget.apply())
//                .withSessionPool().minSize(40)
//                .withSessionPool().maxSize(90)
//                .withSessionPool().maxWaiters(100)
//                .withLoadBalancer().apply(loadBalancerFactory)
                .withRequestTimeout(Duration.apply(3*60,TimeUnit.SECONDS))
                .withLabel(serviceName)
                .build(str, clazz);
        LOGGER.info("create client success --->  !!! " +client);
        return client;
    }
    /**
     * 创建thrift 客户端
     * 直接链接  服务器的ip   用于调试
     *
     * @param ipStr  这里的地址要是服务器的ip
     * @param serviceName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createThriftClientDirect(String ipStr,String serviceName, Class<T> clazz) {

        LOGGER.info("createThriftClientDirect() called with: serverIpAndPort = [" + ipStr + "], serviceName = [" + serviceName + "], clazz = [" + clazz + "]");
        //获取到服务对应的端口
        String port = Constants4Brain.SERVICE_IP_PORT.get(serviceName);

        String serverIpAndPort=ipStr+":"+port;
        LOGGER.info("createThriftClientDirect() called with: [serverIpAndPort] = "+serverIpAndPort);
        //Test LoadBalancer
        LoadBalancerFactory loadBalancerFactory = Balancers.p2c(5, Rngs.threadLocal());

        TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
        T client = Thrift.client()
                .withRequestTimeout(Duration.apply(1*60, TimeUnit.SECONDS))
                .withProtocolFactory(protocolFactory)
                .withLoadBalancer().apply(loadBalancerFactory)
                .withLabel(serviceName)
//                .build(buildConsumerPath(Constants4Brain.SERVICE_MAP.get(serviceName),zkAddress), clazz);
                .build(serverIpAndPort, clazz);
        return client;
    }

    /**
     * 创建thrift客户端，默认 测试地址
     * @param serviceName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createThriftClient(String serviceName, Class<T> clazz) {

        LOGGER.info( "createThriftClient() called with: serviceName = [" + serviceName + "], clazz = [" + clazz + "]");
//        String zkAddressTest="120.24.153.155:2181";
//        String zkAddressTest="39.106.208.144:12181";
        String zkAddressTest =null;
        if(!StringUtil.isNullOrEmpty(Zk_Address)){
            zkAddressTest = Zk_Address;
        }else{
            zkAddressTest="192.168.3.222:12181";// 144 内网ip
        }

       return createThriftClient(zkAddressTest,serviceName,clazz);
    }

    private static volatile InetAddress LOCAL_ADDRESS = null;
    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null)
            return LOCAL_ADDRESS;
        InetAddress localAddress = getLocalAddress0();
        LOCAL_ADDRESS = localAddress;
        return localAddress;
    }

    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress())
            return false;
        String name = address.getHostAddress();
        return (name != null
                && !ANYHOST.equals(name)
                && !LOCALHOST.equals(name)
                && IP_PATTERN.matcher(name).matches());
    }

    public static final String LOCALHOST = "127.0.0.1";

    public static final String ANYHOST = "0.0.0.0";

    private static InetAddress getLocalAddress0() {

        InetAddress localAddress = null;

        try {
            Enumeration<NetworkInterface> interfaces0 = NetworkInterface.getNetworkInterfaces();
            if (interfaces0 != null) {
                List<NetworkInterface> interfaces = Collections.list(interfaces0);
                interfaces.sort(new Comparator<NetworkInterface>() {
                    @Override
                    public int compare(NetworkInterface o1, NetworkInterface o2) {
                        return o1.getIndex() - o2.getIndex();
                    }
                });
                for (NetworkInterface network : interfaces) {
                    try {
                        if (!network.isUp() || network.isVirtual() || network.isLoopback()) {
                            continue;
                        }
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        if (addresses != null) {
                            while (addresses.hasMoreElements()) {
                                try {
                                    InetAddress address = addresses.nextElement();
                                    LOGGER.info("network interface:{} get ip:{}", network.getDisplayName(),
                                            address.getHostAddress());
                                    if (isValidAddress(address)) {
                                        return address;
                                    }
                                } catch (Throwable e) {
                                    LOGGER.warn("Failed to retriving ip address, " + e.getMessage(), e);
                                }
                            }
                        }
                    } catch (Throwable e) {
                        LOGGER.warn("Failed to retriving ip address, " + e.getMessage(), e);
                    }
                }
            }
        } catch (Throwable e) {
            LOGGER.warn("Failed to retriving ip address, " + e.getMessage(), e);
            LOGGER.error("Could not get local host ip address, will use 127.0.0.1 instead.");
        }



        try {
            LOGGER.info(" InetAddress.getLocalHost()    ------  第二阶段 ");
            localAddress = InetAddress.getLocalHost();
            LOGGER.info("network getHostAddress:{} ;;; localAddress:{} ----  localAddress ",
                    localAddress.getHostAddress(),localAddress.getAddress(),localAddress.toString());

            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable e) {
            LOGGER.warn("Failed to retriving ip address, " + e.getMessage(), e);
        }


        return localAddress;
    }

}
