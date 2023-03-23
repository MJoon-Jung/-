package springbook.learningtest.junit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

@Configuration
class EmptyConfiguration {
}

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = EmptyConfiguration.class)
public class JUnitTest {
//    @Autowired
//    ApplicationContext context;

    static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();
//    static ApplicationContext contextObject = null;

    @Test
    public void test1() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

//        assertThat(contextObject == null || contextObject == this.context).isTrue();
//        contextObject = this.context;
    }

    @Test
    public void test2() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

//        Assertions.assertThat(contextObject == null || contextObject == this.context).isTrue();
//        contextObject = this.context;
    }

    @Test
    public void test3() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

//        Assertions.assertThat(contextObject == null || contextObject == this.context).isTrue();
//        contextObject = this.context;
    }
}
