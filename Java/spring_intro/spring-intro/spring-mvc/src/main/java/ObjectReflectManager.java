import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ObjectReflectManager<T extends Object> {

    protected final T getObject(Map<String, Object> fields) {
        return null;
    }

    protected final Map<String, Object> getFields(T entity) {
        return null;
    }

    public static void main(String[] args) {
        A e = new A();

        List<Method> m = getPOJOClassMethod(e.getClass());
        Map<String, Object> fields = new HashMap<>();
        m.forEach(method -> {
            String fieldName = getPOJOValueName(method.getName().replaceFirst("get", ""));
            try {
                fields.put(fieldName, method.invoke(e));
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });

        fields.forEach((s, o) -> {
            System.out.println(s);
            if (o != null) System.out.println(o.getClass());
            else System.out.println("value is null");
        });
    }

    public static String getPOJOValueName(String methodName) {
        return methodName.substring(0,1).toLowerCase() + methodName.substring(1);
    }

    public static List<Method> getPOJOClassMethod(Class<?> currentClass) {
        List<Method> getMethod = getMethodStartWith(currentClass, "get");
        List<Method> setMethod = getMethodStartWith(currentClass, "set");
        getMethod.removeIf(
                method -> setMethod.stream()
                        .noneMatch(
                                method1 -> method1.getName().equals(method.getName().replaceFirst("get", "set"))
                        )
        );
        return getMethod;
    }


    public static List<Method> getMethodStartWith(Class<?> currentClass, String prefix) {
        return Arrays.stream(currentClass.getMethods()).filter(method -> method.getName().startsWith(prefix)).collect(Collectors.toList());
    }
}
