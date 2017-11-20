package pl.thewalkingcode.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.thewalkingcode.config.WeldJUnit4Runner;

import javax.inject.Inject;

@RunWith(WeldJUnit4Runner.class)
public class UserRepositoryTest {

    @Inject
    private UserRepository userRepository;

    @Before
    public void checkInjects() {
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void sampleTest() {

    }

}