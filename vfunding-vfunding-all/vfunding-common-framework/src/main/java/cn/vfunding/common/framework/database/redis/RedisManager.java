package cn.vfunding.common.framework.database.redis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisManager
{
  private ShardedJedisPool shardedJedisPool;

  public String saveString(String key, String value)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.set(key, value);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public String getStringValueByKey(String key)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.get(key);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public boolean exists(String key)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.exists(key).booleanValue();
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public Long delete(String key)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.del(key);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public String saveMap(String key, Map<String, String> map)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.hmset(key, map);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public List<String> getValuesFromMapByStoreKeyAndMapKey(String storeKey, String[] mapKey)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.hmget(storeKey, mapKey);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public Long getLengthFromMapByStoreKey(String storeKey)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.hlen(storeKey);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public Set<String> getMapKeyFromMapByStoreKey(String storeKey)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.hkeys(storeKey);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public List<String> getMapValueFromMapByStoreKey(String storeKey)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.hvals(storeKey);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public Long saveList(String key, List<String> list)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      Long result = null;
      Iterator iter = list.iterator();
      while (iter.hasNext()) {
        result = jedis.lpush(key, (String)iter.next());
      }
      return result;
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public Long getLengthFromListByStoreKey(String storeKey)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.llen(storeKey);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public List<String> getAllValuesFromListByStoreKey(String storeKey)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.lrange(storeKey, 0L, -1L);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public List<String> getValuesFromListByStoreKey(String storeKey, int start, int end)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.lrange(storeKey, start, end);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public Long deleteFromListByByStoreKeyAndValue(String storeKey, String value) throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.lrem(storeKey, 0L, value);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public Long saveSet(String key, Set<String> set)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      Long result = null;
      Iterator iter = set.iterator();
      while (iter.hasNext()) {
        result = jedis.sadd(key, (String)iter.next());
      }
      return result;
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public Long getLengthFromSetByStoreKey(String storeKey)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.scard(storeKey);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public Set<String> getAllValuesFromSetByStoreKey(String storeKey)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.smembers(storeKey);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public boolean isSetValue(String storeKey, String value)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.sismember(storeKey, value).booleanValue();
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public Long deleteFromSetByStoreKeyAndValue(String storeKey, String value)
    throws Exception
  {
    ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
    try {
      return jedis.srem(storeKey, value);
    }
    catch (Exception e) {
      throw e;
    } finally {
      this.shardedJedisPool.returnResource(jedis);
    }
  }

  public ShardedJedisPool getShardedJedisPool() {
    return this.shardedJedisPool;
  }

  public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
    this.shardedJedisPool = shardedJedisPool;
  }
}