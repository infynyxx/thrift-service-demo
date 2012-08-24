/**
 * base.thrift
 * Roughly based on https://github.com/apache/thrift/blob/trunk/contrib/fb303/if/fb303.thrift
 */ 

namespace java com.sailthru.service.base
namespace rb base

/**
 * Common status reporting mechanism across all services
 */ 
enum ServiceStatus {
    DEAD = 0,
    STARTING = 1,
    ALIVE = 2,
    STOPPING = 3,
    STOPPED = 4,
    WARNING = 5
}

/**
 * Standard base service
 */ 
service BaseService {
    /**
    * Returns a descriptive name of the service
    */
    string getName();

    /**
    * Returns the version of the service
    */
    string getVersion();

    /**
    * Gets the status of this service
    */
    ServiceStatus getStaus();

    /**
     * User friendly description of status, such as why the service is in
     * the dead or warning state, or what is being started or stopped.
     */
    string getStatusDetails();

    
    /**
     * Sets an option
     */ 
    void setOption(1: string key, 2: string value);

    /**
     * Get an option
     */ 
    string getOption(1: string key);

    /**
     * Get all options
     */ 
    map<string, string> getOptions();

    /**
     * Returns a CPU profile over the given time interval (client and server must agree on the profile format).
     */ 
    string getCpuProfile(1: i32 profileDurationInSec);

    /**
     * Returns the unix time that the server has been running since
     */ 
    i64 aliveSince();

    /**
     * Tell the server to reload its configuration, reopen log files, etc
     */ 
    oneway void reinitialize();

    /**
     * Suggest a shutdown to the server
     */ 
    oneway void shutdown();
}
