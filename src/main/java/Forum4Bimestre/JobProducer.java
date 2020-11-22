package Forum4Bimestre;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobProducer extends Thread {

    private final Random random = new Random();
    private final Logger logger = LoggerFactory.getLogger(JobProducer.class);
    private final JobProgress jobProgress;

    public JobProducer(JobProgress jobProgress) {
        this.jobProgress = jobProgress;
    }

    @Override
    public void run() {

        while (true) {
            try {
                sleep(1000);

                int newJob = 60 * random.nextInt();

                logger.info("Creating a new job: size {}", newJob);

                this.jobProgress.progress(newJob);

                logger.info("Job created.");
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}