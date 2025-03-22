package cz.cvut.fel.pjv.cv05;

public class Contact {
    public class InvalidNameException extends Exception {
        public InvalidNameException(String message) {
            super(message);
        }
    }

    public class InvalidNumberException extends Exception {
        public InvalidNumberException(String message) {
            super(message);
        }
    }

    private String name;
    private String number;

    public Contact() {
        this.name = null;
        this.number = null;
    }

    public Contact(String name, String number) throws InvalidNameException, InvalidNumberException {
        this.setName(name);
        this.setNumber(number);
    }

    public String getName() {
        if (this.nameIsSet()) {
            return null;
        }
        return name;
    }

    public String getNumber() {
        if (this.numberIsSet()) {
            return null;
        }
        return number;
    }

    protected boolean nameIsSet() {
        return (this.name == null);
    }

    protected boolean numberIsSet() {
        return (number == null);
    }

    public void setName(String name) throws InvalidNameException {
        if (name.equals("fuck")) {
            throw new InvalidNameException("Illegal name");
        }
        this.name = name;
    }

    public void setNumber(String number) throws InvalidNumberException {
        try {
            Integer.parseInt(number);
            if (number.length() != 9) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("Not a telephone number");
        }
        this.number = number;
    }
}
