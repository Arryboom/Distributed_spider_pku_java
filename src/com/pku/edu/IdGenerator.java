
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by qyx on 2014/12/20.
 */
public class IdGenerator {

    private static final AtomicLong generator = new AtomicLong(0);

    private static IdGenerator instance = null;

    public static IdGenerator getInstance() {
        if (null == instance) {
            synchronized (IdGenerator.class) {
                instance = new IdGenerator();
            }
        }
        return instance;
    }

    private IdGenerator() {
    }

    public Long sequence() {
        return generator.addAndGet(1);
    }

}
