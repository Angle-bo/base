package org.huluobo.base.geoip2;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class Test {

    public static void main(String[] args) throws IOException, GeoIp2Exception {
        //asnDb();
        cityDb();
        //cityWebService();
    }

    static void asnDb() throws IOException, GeoIp2Exception {
        String filePath = Test.class.getClassLoader().getResource("GeoLite2-ASN.mmdb").getFile();
        System.out.println(filePath);
        File database = new File(filePath);

        // This reader object should be reused across lookups as creation of it is
        // expensive.
        DatabaseReader reader = new DatabaseReader.Builder(database).build();

        // If you want to use caching at the cost of a small (~2MB) memory overhead:
        // new DatabaseReader.Builder(file).withCache(new CHMCache()).build();

        InetAddress ipAddress = InetAddress.getByName("36.143.1.106");
        AsnResponse response = reader.asn(ipAddress);

        System.out.println(response.getAutonomousSystemNumber());       // 217
        System.out.println(response.getAutonomousSystemOrganization()); // 'University of Minnesota'
    }

    static void cityDb() throws IOException, GeoIp2Exception{
        String filePath = Test.class.getClassLoader().getResource("GeoLite2-City.mmdb").getFile();
        System.out.println(filePath);
        File database = new File(filePath);

        // This reader object should be reused across lookups as creation of it is
        // expensive.
        DatabaseReader reader = new DatabaseReader.Builder(database).build();

        // If you want to use caching at the cost of a small (~2MB) memory overhead:
        // new DatabaseReader.Builder(file).withCache(new CHMCache()).build();

        InetAddress ipAddress = InetAddress.getByName("192.168.1.1");

        CityResponse response = reader.city(ipAddress);

        Country country = response.getCountry();
        //System.out.println(country.getIsoCode());            // 'US'
        //System.out.println(country.getName());               // 'United States'
        System.out.println(country.getNames().get("zh-CN")); // '美国'

        Subdivision subdivision = response.getMostSpecificSubdivision();
        System.out.println(subdivision.getNames().get("zh-CN"));    // 'Minnesota'
        //System.out.println(subdivision.getIsoCode()); // 'MN'

        City city = response.getCity();
        System.out.println(city.getNames().get("zh-CN")); // 'Minneapolis'

        //Postal postal = response.getPostal();
        //System.out.println(postal.getCode()); // '55455'

        //Location location = response.getLocation();
        //System.out.println(location.getLatitude());  // 44.9733
        //System.out.println(location.getLongitude()); // -93.2323
    }

    static void cityWebService(){
        // This creates a WebServiceClient object that is thread-safe and can be
        // reused across requests. Reusing the object will allow it to keep
        // connections alive for future requests. The object is closeable, but
        // it should not be closed until you are finished making requests with it.
        //
        // Replace "42" with your account ID and "license_key" with your license key.
        // To use the GeoLite2 web service instead of GeoIP2 Precision, call the
        // host method on the builder with "geolite.info", e.g.
        // new WebServiceClient.Builder(42, "license_key").host("geolite.info").build()
        try (WebServiceClient client = new WebServiceClient.Builder(557607, "6TSwuwkyxWv4JnCv")
                .build()) {

            InetAddress ipAddress = InetAddress.getByName("36.143.1.106");

            // Do the lookup
            CityResponse response = client.city(ipAddress);

            Country country = response.getCountry();
            System.out.println(country.getIsoCode());            // 'US'
            System.out.println(country.getName());               // 'United States'
            System.out.println(country.getNames().get("zh-CN")); // '美国'

            Subdivision subdivision = response.getMostSpecificSubdivision();
            System.out.println(subdivision.getName());       // 'Minnesota'
            System.out.println(subdivision.getIsoCode());    // 'MN'

            City city = response.getCity();
            System.out.println(city.getName());       // 'Minneapolis'

            Postal postal = response.getPostal();
            System.out.println(postal.getCode());       // '55455'

            Location location = response.getLocation();
            System.out.println(location.getLatitude());        // 44.9733
            System.out.println(location.getLongitude());       // -93.2323
        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
        }

    }
}
