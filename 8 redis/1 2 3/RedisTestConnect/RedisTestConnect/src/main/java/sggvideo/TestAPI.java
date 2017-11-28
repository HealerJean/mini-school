package sggvideo;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestAPI {

	public   Jedis ConnectRedis(){
		Jedis jedis = new Jedis("127.0.0.1",6379);
		System.out.println(jedis.ping()+"连接成功");
		return  jedis;
	}

	public static void main(String[] args) {
		TestAPI testAPI = new TestAPI();
		//testAPI.ConnectRedis();
		testAPI.testKey();
	}
	public  void testKey()
	{
		Jedis jedis =ConnectRedis();

		jedis.set("k1","v1");
		jedis.set("k2","v2");
		jedis.set("k3","v3");
		System.out.println("********testKey********");
		System.out.println("k3的值为："+jedis.get("k3"));
		Set<String> sets = jedis.keys("*");
		System.out.println("key的数量为："+sets.size());
		//后续请参考脑图，家庭作业，敲一遍......
		System.out.println("k1存在吗："+jedis.exists("k1"));
		System.out.println("k4存在吗："+jedis.exists("k4"));
		System.out.println("********testKey********");


	}

	public void testString() {
		Jedis jedis =ConnectRedis();

	}



}
