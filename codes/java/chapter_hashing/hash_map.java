/*
 * File: hash_map.java
 * Created Time: 2022-12-04
 * Author: Krahets (krahets@163.com)
 */

package chapter_hashing;
import java.util.*;
import include.*;

public class hash_map {
    public static void main(String[] args) {
        /* 初始化哈希表 */
        Map<Integer, String> map = new HashMap<>();

        /* 添加操作 */
        // 在哈希表中添加键值对 (key, value)
        map.put(10001, "小哈");   
        map.put(10002, "小啰");   
        map.put(10003, "小算");   
        map.put(10004, "小法");
        map.put(10005, "小哇");
        System.out.println("\n添加完成后，哈希表为\nKey -> Value");
        PrintUtil.printHashMap(map);

        /* 查询操作 */
        // 向哈希表输入键 key ，得到值 value
        String name = map.get(10002);
        System.out.println("\n输入学号 10002 ，查询到姓名 " + name);

        /* 删除操作 */
        // 在哈希表中删除键值对 (key, value)
        map.remove(10005);
        System.out.println("\n删除 10005 后，哈希表为\nKey -> Value");
        PrintUtil.printHashMap(map);

        /* 遍历哈希表 */
        System.out.println("\n遍历键值对 Key->Value");
        for (Map.Entry <Integer, String> kv: map.entrySet()) {
            System.out.println(kv.getKey() + " -> " + kv.getValue());
        }
        System.out.println("\n单独遍历键 Key");
        for (int key: map.keySet()) {
            System.out.println(key);
        }
        System.out.println("\n单独遍历值 Value");
        for (String val: map.values()) {
            System.out.println(val);
        }
    }
}
