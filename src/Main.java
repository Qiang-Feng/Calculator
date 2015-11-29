import java.util.*;

public class Main
{
    private static List<Operator> before = new ArrayList<Operator>();
    private static HashMap<Integer, ExpressionNode> expressionNodes = new HashMap<Integer, ExpressionNode>();

    public static void main(String[] args)
    {
        try
        {
            before.add(Operator.MULTIPLY);
            before.add(Operator.DIVIDE);

            Scanner input = new Scanner(System.in);
            String expression = input.nextLine().replaceAll("[^a-zA-Z\\d" + Operator.getAll("\\") + "]", "");

            if (expression.isEmpty() || expression.matches("^[0-9]+$"))
            {
                System.out.println("No calculation can be done.");
                System.exit(0);
            }

            HashMap<Integer, Operator> operatorList = new HashMap<Integer, Operator>();
            HashMap<Integer, Object> operandList = new HashMap<Integer, Object>();

            StringTokenizer stringTokenizer = new StringTokenizer(expression, Operator.getAll(), true);

            int key = 0;

            while (stringTokenizer.hasMoreTokens())
            {
                String token = stringTokenizer.nextToken();

                if (Operator.getEnums().containsValue(token))
                {
                    operatorList.put(key, getKeyFromValue(Operator.getEnums(), token));
                }
                else
                {
                    operandList.put(key, Integer.parseInt(token));
                }

                key += 1;
            }

            int operatorSize = operatorList.size();

            for (int i = 1; i < operatorSize * 2; i += 2)
            {
                if (before.contains(operatorList.get(i)))
                {
                    operandList.put(i - 1, new ExpressionNode((Integer) operandList.get(i - 1), operatorList.get(i), (Integer) operandList.get(i + 1)));
                    operandList.remove(i + 1);
                    operatorList.remove(i);
                }
            }

            // Reset keys for operand
            List<Integer> operandKeys = new ArrayList<Integer>();

            for (Map.Entry<Integer, Object> entry : operandList.entrySet())
            {
                operandKeys.add(entry.getKey());
            }
            for (int i = 0; i < operandList.size() * 2; i += 2)
            {
                operandList.put(i, operandList.remove(operandKeys.get(0)));
                operandKeys.remove(0);
            }

            // Reset keys for operator
            List<Integer> operatorKeys = new ArrayList<Integer>();

            for (Map.Entry<Integer, Operator> entry : operatorList.entrySet())
            {
                operatorKeys.add(entry.getKey());
            }
            for (int i = 1; i < operatorList.size() * 2; i += 2)
            {
                operatorList.put(i, operatorList.remove(operatorKeys.get(0)));
                operatorKeys.remove(0);
            }

            ExpressionNode resultTree = null;

            if (!operatorList.isEmpty())
            {
                while (!operatorList.isEmpty())
                {
                    Map.Entry<Integer, Operator> firstOperator = operatorList.entrySet().iterator().next();

                    Object leftLeaf;
                    Object rightLeaf;

                    boolean leftE = false;
                    boolean rightE = false;

                    if (resultTree == null)
                    {
                        if (operandList.get(firstOperator.getKey() - 1) instanceof ExpressionNode)
                        {
                            leftLeaf = (ExpressionNode) operandList.get(firstOperator.getKey() - 1);
                            leftE = true;
                        }
                        else
                        {
                            leftLeaf = (Integer) operandList.get(firstOperator.getKey() - 1);
                        }
                    }
                    else
                    {
                        leftLeaf = resultTree;
                        leftE = true;
                    }

                    if (operandList.get(firstOperator.getKey() + 1) instanceof ExpressionNode)
                    {
                        rightLeaf = (ExpressionNode) operandList.get(firstOperator.getKey() + 1);
                        rightE = true;
                    }
                    else
                    {
                        rightLeaf = (Integer) operandList.get(firstOperator.getKey() + 1);
                    }

                    if (leftE)
                    {
                        if (rightE)
                        {
                            resultTree = new ExpressionNode((ExpressionNode) leftLeaf, firstOperator.getValue(), (ExpressionNode) rightLeaf);
                        }
                        else
                        {
                            resultTree = new ExpressionNode((ExpressionNode) leftLeaf, firstOperator.getValue(), (Integer) rightLeaf);
                        }
                    }
                    else if (rightE)
                    {
                        resultTree = new ExpressionNode((Integer) leftLeaf, firstOperator.getValue(), (ExpressionNode) rightLeaf);
                    }
                    else
                    {
                        resultTree = new ExpressionNode((Integer) leftLeaf, firstOperator.getValue(), (Integer) rightLeaf);
                    }

                    operatorList.remove(firstOperator.getKey());
                }
            }
            else
            {
                resultTree = (ExpressionNode) operandList.entrySet().iterator().next().getValue();
            }

            assert resultTree != null;

            System.out.println(resultTree.evaluate());
        }
        catch (Exception e)
        {
            System.out.println("An exception has been caught.");
        }
    }

    public static Operator getKeyFromValue(Map hashMap, Object value)
    {
        for (Object object : hashMap.keySet())
        {
            if (hashMap.get(object).equals(value))
            {
                return (Operator) object;
            }
        }

        return null;
    }
}
