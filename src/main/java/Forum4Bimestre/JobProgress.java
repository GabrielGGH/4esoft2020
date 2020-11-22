package Forum4Bimestre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class JobProgress {

    private final Logger logger = LoggerFactory.getLogger(JobProgress.class);
    private final LinkedList<Integer> jobs = new LinkedList<>();
    private JobProgressListener listener;

    public JobProgress() {
        super();
    }

    public void addProgress(JobProgressListener listener) {

        this.listener = listener;
    }

    public interface JobProgressListener {

        void jobProgressChanged(int newSize) throws InterruptedException;
    }

    public synchronized void progress(int job) throws InterruptedException {

        synchronized (this) {
            this.jobs.add(job);
            if (this.listener != null) {
                this.listener.jobProgressChanged(this.jobs.size());
            }
            logger.info("Job Progress {}. Time: {}", job, System.currentTimeMillis());
        }
    }

    public synchronized Integer getNextJob() throws InterruptedException {

        synchronized (this) {
            logger.info("Getting next job. Time: {}", System.currentTimeMillis());

            if (!this.jobs.isEmpty()) {
            	
            	 Integer job = this.jobs.removeFirst();

                 if (this.listener != null) {
                     this.listener.jobProgressChanged(this.jobs.size());
                 }

                 logger.info("Next job retrivied, {}", job);
                 return job;
            }

            return 0;
            
           
        }
    }
}
