package jms.converter;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class XMLConverter<T> {

       Class<T> tClass;

       public XMLConverter(Class<T> tClass) {
           this.tClass = tClass;
        }


        public T convertToObject(String objectAsString) {
            JAXBContext jaxbContext = null;
            try {
                jaxbContext = JAXBContext.newInstance(tClass);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                T object = (T) jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(objectAsString.getBytes()));

                return object;

            } catch (JAXBException e) {
                e.printStackTrace();
                return null;
            }
        }

        public String convertToString(T object){

            JAXBContext jaxbContext = null;
            try {
                OutputStream stream = new ByteArrayOutputStream();
                jaxbContext = JAXBContext.newInstance(tClass);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.marshal(object, stream);

                return stream.toString();

            } catch (JAXBException e) {
                e.printStackTrace();
                return null;
            }

        }
}




