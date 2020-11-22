package Forum4Bimestre;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobConsumer extends Thread {

    private final Logger logger = LoggerFactory.getLogger(JobConsumer.class);
    private final JobProgress jobProgress;
    private Integer assignedJob = 0;

    public JobConsumer(JobProgress jobProgress) {
        this.jobProgress = jobProgress;
    }

    @Override
    public void run() {

        while (true) {
            if (assignedJob == 0) {
                try {
                    if (jobProgress.getNextJob() == 0) {
                        logger.info("Idle, Time: {} ", System.currentTimeMillis());
                        sleep(3000);
                    }
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            } else {
                for (int i = assignedJob; i >= 0; i--) {
                    logger.info("Working... Time: {}", System.currentTimeMillis());
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage());
                    }
                }
                assignedJob = 0;
                logger.info("Finished processing. Time: {}", System.currentTimeMillis());
            }
        }
    }
}
