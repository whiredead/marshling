package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee e1= new Employee(1,"El hachmy",0);
        Employee e2= new Employee(1,"yahya",10000);
        Employee e3= new Employee(1,"hamza",50000);

        Wrapper wrap = new Wrapper();
        wrap.employees.add(e1);
        wrap.employees.add(e2);
        wrap.employees.add(e3);

        try {

            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller mar = context.createMarshaller();
            Unmarshaller unmar = context.createUnmarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(e1, new File("./employee.xml"));
            Employee e4 = (Employee) unmar.unmarshal(new FileReader("./employee.xml"));
            System.out.println("first "+e4.toString());

        }catch(JAXBException ex){
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        try{

            JAXBContext context = JAXBContext.newInstance(Wrapper.class);
            Marshaller mar = context.createMarshaller();
            Unmarshaller unmar = context.createUnmarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(wrap, new File("./employees.xml"));
            Wrapper w1 = (Wrapper) unmar.unmarshal(new FileReader("./employees.xml"));
            System.out.println("first w1 "+w1.toString());
            w1.employees.forEach(employee -> {
                if(employee.getNom().equals("El hachmy")){
                    employee.setSalaire(200000);
                    System.out.println("inside if ###");
                }
            });
            System.out.println("second w1 "+w1.toString());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
