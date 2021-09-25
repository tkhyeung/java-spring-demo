package com.example.java;


import com.example.demo.wsdl.HelloService;
import com.example.demo.xsd.PurchaseOrderType;
import com.example.demo.xsd.USAddress;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*
WSDL (Web Services Description Language) = about the soap service and operations
XSD (Xml Schema Definition) = about the structure of the data (of the service)
 */
        HelloService service = new HelloService();
        USAddress usAddress = new USAddress();
        PurchaseOrderType purchaseOrderType = new PurchaseOrderType();
    }
}
