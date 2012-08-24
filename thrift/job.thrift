namespace java com.sailthru.service.job.thrift
namespace php service_job
namespace rb job_service

include "./base.thrift"

enum JobStatus {
    QUEUED,
    PENDING,
    COMPLETED
}

struct Job {
    1: string id;
    2: i32 clientId;
    3: i64 startTime;
    4: i64 stopTime;
    5: string type;
    6: JobStatus status;
    7: string title;
}

struct JobDummy {
    1: Job job;
    2: i32 blastId;
}

service JobService extends base.BaseService {
    Job findById(1: string id);
    Job processJob(1: string job, 2: map<string, string> options);
    Job abort(1: string id);
}

