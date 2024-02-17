package e2e.utils;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    Faker faker = new Faker();
    @DataProvider
    public Iterator<Object[]> invalidEmail(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{faker.internet().uuid(),faker.internet().password()});
        return list.iterator();
    }

//    @DataProvider
//    public Iterator<Object[]> (){
//        List<Object[]> list = new ArrayList<>();
//        list.add(new Object[]{"Steven","newtest@gmail.com", "with_invalid_email"});
//        list.add(new Object[]{"newtest@gmail.com",faker.internet().uuid(), "with_invalid_password"});
//        list.add(new Object[]{"213123","234234", "with_invalid_data"});
//        return list.iterator();
//    }
//}
}
