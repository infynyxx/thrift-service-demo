#!/usr/bin/env ruby

$:.push('./gen-rb')

require 'thrift'

require 'Job_service'

begin
    port = 9091
    transport = Thrift::BufferedTransport.new(Thrift::Socket.new('localhost', port))
    protocol = Thrift::BinaryProtocol.new(transport)
    client = Job_service::JobService::Client.new(protocol)
    num = 0
    transport.open()
    until num > 10 do
        job = client.processJob("dummy", {"key1" => "value1"})
        puts job.id
        puts Job_service::JobStatus::VALUE_MAP[job.status]
        num = num + 1
    end

    puts "Shutting down server"
    client.shutdown()

rescue Thrift::Exception => tx
    print 'Thrift::Exception: ', tx.message, "\n"
end
