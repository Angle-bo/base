package org.huluobo.influxdb;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.FluxTable;

import java.time.Instant;
import java.util.List;

public class InfluxdbTest {
    public static void main(String[] args) {
        // You can generate a Token from the "Tokens Tab" in the UI
        String token = "-G4bcqVVIlNu_Bzyw1pdITr-3fzAoShik6sn6ZGNngiLdMN6MBfaTg1wvU3pXXZ2Trd3fUn_XEf9rvcMtJlR-Q==";
        String bucket = "test";
        String org = "sys";
        InfluxDBClient client = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());

        Mem mem = new Mem();
        mem.host = "host1";
        mem.used_percent = 23.43234543;
        mem.time = Instant.now();
        try (WriteApi writeApi = client.getWriteApi()) {
            //writeApi.writeMeasurement(bucket, org, WritePrecision.NS, mem);
        }
        String query = String.format("from(bucket: %s) |> range(start: -1h)", bucket);
        List<FluxTable> tables = client.getQueryApi().query(query, org);
        System.out.println(tables.size());
    }

    @Measurement(name = "mem")
    public static class Mem {
        @Column(tag = true)
        String host;
        @Column
        Double used_percent;
        @Column(timestamp = true)
        Instant time;
    }
}
