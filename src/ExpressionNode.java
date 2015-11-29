public class ExpressionNode
{
    private int leftLeaf;
    private int rightLeaf;
    private Operator operator;

    public ExpressionNode(int left, Operator operator, int right)
    {
        leftLeaf = left;
        rightLeaf = right;
        this.operator = operator;
    }

    public ExpressionNode(ExpressionNode left, Operator operator, int right)
    {
        leftLeaf = left.evaluate();
        rightLeaf = right;
        this.operator = operator;
    }

    public ExpressionNode(ExpressionNode left, Operator operator, ExpressionNode right)
    {
        leftLeaf = left.evaluate();
        rightLeaf = right.evaluate();
        this.operator = operator;
    }

    public ExpressionNode(int left, Operator operator, ExpressionNode right)
    {
        leftLeaf = left;
        rightLeaf = right.evaluate();
        this.operator = operator;
    }

    public int evaluate()
    {
        int result = 0;

        switch (operator)
        {
            case ADD:
                result = leftLeaf + rightLeaf;
                break;

            case MINUS:
                result = leftLeaf - rightLeaf;
                break;

            case MULTIPLY:
                result = leftLeaf * rightLeaf;
                break;

            case DIVIDE:
                result = leftLeaf / rightLeaf;
                break;
        }

        return result;
    }
}
