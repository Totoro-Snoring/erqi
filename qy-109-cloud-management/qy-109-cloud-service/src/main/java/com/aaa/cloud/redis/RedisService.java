package com.aaa.cloud.redis;

import com.aaa.cloud.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.aaa.cloud.staticproperties.RedisProperties.*;

/**
 * @Author: zcl
 * @ClassName: RedisService
 * @Description: RedisApi
 * @Date: 2020/7/10 15:35
 **/
@Service
public class RedisService<T> {
    private RedisSerializer keySerializer = null;

    /**
     * @Author: zcl
     * @Methodname: initRedisSerializer
     * @Description: 初始化Redis的key的序列化器
     * @Date: 2020/7/10 15:43
     * @Param: []
     * @Return: void
     **/
    @PostConstruct
    public void initRedisSerializer() {
        if (this.keySerializer == null) {
            this.keySerializer = new JdkSerializationRedisSerializer(this.getClass().getClassLoader());
        }
    }

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * @Author: zcl
     * @Methodname: set
     * @Description: 向redis中存入数据
     * @Date: 2020/7/10 15:46
     * @Param: [key, T, nxxx, expx, seconds]
     * nxxx:  是固定值，有两个值
     * "nx":  如果redis中的key不存在，则就可以存储，如果redis中已经有这个key了，则不存数据
     * "xx":  如果redis中的key存在，则直接覆盖，如果key不存在则不存
     * eg:
     * 研发组有两个人，一个是张三，一个是李四
     * 张三负责商品管理的代码编写
     * 李四负责订单管理
     * 张三和李四因为数据量过大都会使用到redis
     * 张三---->redis.set("goods", 商品信息);
     * 李四---->redis.set("goods", 订单信息);
     * 张三---->redis.get("goods")--->订单信息---->转换异常
     * <p>
     * 张三负责商品管理的代码编写
     * 张三---->redis.set("goods", 商品信息);
     * 张三---->redis.set("goods", 商品信息);
     * expx: 值也是固定的，只有两个值:
     * ex:  失效时间的单位是秒
     * px:  失效时间的单位是毫秒
     * <p>
     * seconds:
     * 失效时间
     * @Return: java.lang.String
     **/
    public String set(String key, T value, String nxxx, String expx, Integer seconds) {
        if (null != seconds && 0 < seconds &&
                (EX.equals(expx) || PX.equals(expx)) &&
                (XX.equals(nxxx) || NX.equals(nxxx))) {
            // 说明在存入数据的时候我就必须要上失效时间了
            return jedisCluster.set(key, JSONUtils.toJsonString(value), nxxx, expx, seconds);
        } else {
            //说明不需要上失效时间
            //但仍然要进一步判断用户传递的是nx还是xx     要用    else if
            if (NX.equals(nxxx)) {
                return jedisCluster.set(key, JSONUtils.toJsonString(value));
            } else if (XX.equals(nxxx)) {
                return jedisCluster.set(key, JSONUtils.toJsonString(value));
            }
        }
        return NO;
    }


    /**
     * @Author: zcl
     * @Methodname: getOne
     * @Description:
     *          从redis获取一条数据
     * @Date: 2020/7/10 16:11
     * @Param: [key]
     * @Return: T
     **/
    public T getOne(String key) {
        String s = jedisCluster.get(key);
        return (T) JSONUtils.toJsonString(s);
    }


    /**
     * @Author: zcl
     * @Methodname: getString
     * @Description:
     *          从redis获取一条数据(数据是字符串类型)
     *          JSONUtils.toList(String jsonData, Class<T> beanType)
     *              jsonData:传入的json对象
     *              beanType:所需要转换对象的目标类型   User.class, Book.class
     * @Date: 2020/7/10 16:14
     * @Param: [key]
     * @Return: java.lang.String
     **/
    public String getString(String key) {
        return jedisCluster.get(key);
    }

    /**
     * @Author: zcl
     * @Methodname: getList
     * @Description:
     *         从redis获取一条数据（数据是List<T>）
     * @Date: 2020/7/10 16:22
     * @Param: [key]
     * @Return: java.util.List<T>
     **/
    public List<T> getList(String key) {
        String s = jedisCluster.get(key);
        return (List<T>) JSONUtils.toList(s, Object.class);
    }

    /**
     * @Author: zcl
     * @Methodname: delOne
     * @Description:
     *         删除redis中一条数据
     * @Date: 2020/7/10 16:25
     * @Param: [key]
     * @Return: java.lang.Long
     **/
    public Long delOne(String key){
        return jedisCluster.del(key);
    }

    /**
     * @Author: zcl
     * @Methodname: delMany
     * @Description:
     *          根据key删除多条数据
     *
     *          为什么把多个key值存入集合，而不是数组？
     *          1.数组是大小固定的,一旦创建无法扩容;集合大小不固定,
     *          2.数组的存放的类型只能是一种,集合存放的类型可以不是一种(不加泛型时添加的类型是Object);
     *          3.数组是java语言中内置的数据类型,是线性排列的,执行效率或者类型检查(不懂),都是最快的.
     *              ArrayList就是基于数组创建的容器类.（动态扩展容量，类型不固定）中 （数组长度固定，只能是一直类型）
     *
     *           为什么把Object对象转换为字节数组，而不是直接循环删除？
     *           1.因为JedisCluster只能接收String类型key值，并不符合架构标准
     *           2.循环的形式看似没有毛病，但是有问题
     *                  eg. 假设有10个key需要删除
     *                      其中九个都删了，但是只有一个没有删，如果这一个不是在最后
     *                      那么结果返回一定大于0，不能使用循环操作
     *
     * @Date: 2020/7/10 16:30
     * @Param: [keys]
     * @Return: java.lang.Long
     **/
    public Long delMany(Collection<T> keys){
        if (CollectionUtils.isEmpty(keys)){
            return 0L;
        }else {
            byte[][] raw2keys = this.raw2keys(keys);
            return jedisCluster.del(raw2keys);
        }
    }

    /**
     * @Author: zcl
     * @Methodname: rawkey
     * @Description:
     *         把对象类型转换成 字节数组
     * @Date: 2020/7/10 16:55
     * @Param: [key]
     * @Return: byte[]
     **/
    public byte[] raw1key(Object key){
        /*
        if (key == null){

            System.out.println("key值不存在");
            return null;
        }else {
            //key不为空
        */
            Assert.notNull(key,"key not to be bull");

            /*
            if (keySerializer == null && key instanceof byte[]){
                //直接转换
                return (byte[]) key;
            }else{
                //说明需要初始化Redis的key的序列化器
                return keySerializer.serialize(key);
            }
        */
            return this.keySerializer.serialize(key) == null && key instanceof byte[] ?
                    (byte[]) key : keySerializer.serialize(key);

    }


    /**
     * @Author: zcl
     * @Methodname: rawkeys
     * @Description:
     *         把集合中的对象转换成 二维数组
     * @Date: 2020/7/10 17:03
     * @Param: [keys]
     * @Return: byte[][]
     **/
    public byte[][] raw2keys(Collection<T> keys){

        byte[][] byte2 = new byte[keys.size()][];
        int i = 0;
        Object key;
        for (Iterator i9 = keys.iterator();i9.hasNext(); byte2[i++]=this.raw1key(key)) {
            key = i9.next();
        }
        return byte2;
    }

}
