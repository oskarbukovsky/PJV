package cz.cvut.fel.pjv.bukovja4.utils.config;

import java.lang.reflect.Field;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Base class for objects that need a standardized string representation.
 * Provides a toString method that uses reflection to print all fields of the
 * class.
 * Useful for configuration objects loaded from YAML files.
 */
public class YAMLPrint {
    /**
     * Generates a string representation of the object showing all field values.
     * Uses reflection to access all declared fields, including private ones.
     * 
     * @return A string containing the class name and all field name-value pairs
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.getClass().getSimpleName()).append("{");
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
                } catch (final Throwable ignore) {
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
