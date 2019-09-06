import com.wentong.Application;
import com.wentong.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhouwentong on 2018/7/25.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {
    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Test
    public void test() throws Exception {

        // 保存对象
        User user = new User("superman", 20);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("superman1", 30);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("superman2", 40);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        Assert.assertEquals(20, redisTemplate.opsForValue().get("superman").getAge());
        Assert.assertEquals(30, redisTemplate.opsForValue().get("superman1").getAge());
        Assert.assertEquals(40, redisTemplate.opsForValue().get("superman2").getAge());

    }
}
