import java.util.HashMap;

public enum Operator
{
    ADD
    {
        public String toString()
        {
            return "+";
        }
    },
    MINUS
    {
        public String toString()
        {
            return "-";
        }
    },
    MULTIPLY
    {
        public String toString()
        {
            return "*";
        }
    },
    DIVIDE
    {
        public String toString()
        {
            return "/";
        }
    };

    public static HashMap<Operator, String> getEnums()
    {
        HashMap<Operator, String> values = new HashMap<Operator, String>();

        for (Operator operator : Operator.values())
        {
            values.put(operator, operator.toString());
        }

        return values;
    }

    public static String getAll(String separator)
    {
        String string = "";
        boolean noSeparator = true;

        for (String operator : Operator.getEnums().values())
        {
            if (noSeparator)
            {
                noSeparator = false;
                string += operator;
            }
            else
            {
                string += separator + operator;
            }
        }

        return string;
    }

    public static String getAll()
    {
        return Operator.getAll("");
    }
}