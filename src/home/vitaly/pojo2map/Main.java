package home.vitaly.pojo2map;

  import java.beans.BeanInfo;
  import java.beans.Introspector;
  import java.beans.PropertyDescriptor;
  import java.lang.reflect.Method;
  import java.util.*;


public class Main {
public static void main(String[] args) throws Exception {
    List<Object> pojos = new ArrayList<Object>();

    POJO p1 = new POJO();
    p1.setAge("10");
    p1.setName("Alex");
    pojos.add(p1);

    POJO p2 = new POJO();
    p2.setAge("20");
    p2.setName("Ralf");
    pojos.add(p2);

    LAJO l1 = new LAJO();
    l1.setL(100L);
    l1.setX("x1x1x1");
    l1.setY("y11y1y1");
    pojos.add(l1);

    LAJO l2 = new LAJO();
    l2.setL(200L);
    l2.setX("x2x2x2x2");
    l2.setY("y2y2y2yy2");
    pojos.add(l2);

    System.out.println(convertCollection(pojos));
}

    public static List<Map<String, ?>> convertCollection(Collection collection)
            throws Exception {
        List<Map<String, ?>> list = new ArrayList<Map<String, ?>>();
//        Map m;
        for (Object element : collection) {
            Map m = obj2map(element);
            System.out.println("elemenet:"+m+"\n===");
            list.add(m);
        }
        return list;
    }

    public static Map<String, ?> obj2map(Object o)
            throws Exception {
        Map<String, Object> values = new HashMap<String, Object>();
        BeanInfo info = Introspector.getBeanInfo(o.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            // This will access public properties through getters
            Method getter = pd.getReadMethod();
            if (getter != null) {
                System.out.println(pd.getName() + "==>" + getter.invoke(o));
                values.put(pd.getName(), getter.invoke(o));
            }
            else System.out.println("null getter"+getter);
        }
        System.out.println("---");
        return values;
    }

}