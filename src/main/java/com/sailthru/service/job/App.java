package com.sailthru.service.job;

import com.sailthru.service.job.thrift.JobService;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author Prajwal Tuladhar <praj@infynyxx.com>
 */
public class App {
    public static void main(String[] args)  {
        try {
            final JobServiceHandler jobServiceHandler = new JobServiceHandler();
            final JobService.Processor processor = new JobService.Processor(jobServiceHandler);
            final TServerSocket tServerSocket = new TServerSocket(9091);
            final TThreadPoolServer server = new TThreadPoolServer(new TThreadPoolServer.Args(tServerSocket).processor(processor));

            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Starting Job Service...");
                    server.serve();
                }
            };

            new Thread(runnable).start();

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    System.out.println("Shutting down the service...");
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
