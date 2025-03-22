package cz.cvut.fel.pjv;

import java.util.Arrays;

public class BruteForceAttacker extends Thief {
    public void breakPassword(int sizeOfPassword) {
        breakPassword(new char[0], sizeOfPassword);
    }

    public boolean breakPassword(char[] prefix, int sizeOfPassword) {
        if (prefix.length < sizeOfPassword) {
            prefix = Arrays.copyOf(prefix, prefix.length + 1);
            for (char character : getCharacters()) {
                prefix[prefix.length - 1] = character;
                if (breakPassword(prefix, sizeOfPassword)) {
                    return true;
                }
            }
        } else {
            return tryOpen(prefix);
        }
        return false;
    }
}
