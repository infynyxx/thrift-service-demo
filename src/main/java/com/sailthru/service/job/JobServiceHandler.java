package com.sailthru.service.job;

import com.sailthru.service.base.ServiceStatus;
import com.sailthru.service.job.thrift.Job;
import com.sailthru.service.job.thrift.JobService;
import com.sailthru.service.job.thrift.JobStatus;
import org.apache.thrift.TException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Prajwal Tuladhar <praj@infynyxx.com>
 */
public class JobServiceHandler implements JobService.Iface {

    private Date aliveSince = new Date();

    private Map<String, String> options = new HashMap<String, String>();

    private Map<String, Job> jobs = new HashMap<String, Job>();

    //private Map
    @Override
    public Job findById(String id) throws TException {
        return jobs.get(id);
    }

    // dummy job
    @Override
    public Job processJob(String job, Map<String, String> options) throws TException {
        // do processing
        Job jobObject = new Job();
        String id = UUID.randomUUID().toString();
        jobObject.id = id;
        jobObject.status = JobStatus.QUEUED;
        jobs.put(id, jobObject);
        return jobObject;
    }

    @Override
    public Job abort(String id) throws TException {
        Job job = jobs.get(id);
        if (job != null && job.status == JobStatus.QUEUED) {
            jobs.remove(id);
        }
        return job;
    }

    @Override
    public String getName() throws TException {
        return "Job Service";
    }

    @Override
    public String getVersion() throws TException {
        return "1.0.0";
    }

    @Override
    public ServiceStatus getStaus() throws TException {
        return ServiceStatus.ALIVE;
    }

    @Override
    public String getStatusDetails() throws TException {
        return "Some Status";
    }

    @Override
    public void setOption(String key, String value) throws TException {
        options.put(key, value);
    }

    @Override
    public String getOption(String key) throws TException {
        return options.get(key);
    }

    @Override
    public Map<String, String> getOptions() throws TException {
        return options;
    }

    @Override
    public String getCpuProfile(int profileDurationInSec) throws TException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long aliveSince() throws TException {
        return aliveSince.getTime();
    }

    @Override
    public void reinitialize() throws TException {
        options.clear();
    }

    @Override
    public void shutdown() throws TException {
        System.exit(0);
    }
}
