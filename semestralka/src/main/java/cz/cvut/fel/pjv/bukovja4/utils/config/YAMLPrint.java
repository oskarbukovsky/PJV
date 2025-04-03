package cz.cvut.fel.pjv.bukovja4.utils.config;

import java.lang.reflect.Field;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class YAMLPrint {
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.getClass().getSimpleName() + "{");
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            try {
                result.append(field.getName()).append("=").append(field.get(this));
                if (i < fields.length - 1) {
                    result.append(", ");
                }
            } catch (IllegalAccessException e) {
                try {
                    LOG.trace(e);
                } catch (Throwable f) {

                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
