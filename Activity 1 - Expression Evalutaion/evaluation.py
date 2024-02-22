def convertToPostfix(inp):
    # TODO: Parse Larger Numbers
    operators = {'+': 1, '-': 1, '*': 2, '/': 2}
    postFix = []
    stack = []
    for i in inp:
        if i.isdigit():
            postFix.append(int(i))
        elif i in operators:
            while stack and operators.get(stack[len(stack) - 1], 0) >= operators[i]:
                postFix.append(stack.pop())
            stack.append(i)
        elif i == '(':
            stack.append(i)
        elif i == ')':
            while stack and stack[len(stack) - 1] != '(':
                postFix.append(stack.pop())
            if not stack:
                return "ERROR"
            stack.pop()
        elif i == ' ':
            continue
        else:
            return "ERROR"
    return postFix + stack[::-1]


def evaluatePostfix(postfix):
    stack = []
    for i in postfix:
        if type(i) == int:
            stack.append(i)
        else:
            if len(stack) == 0:
                return "ERROR"
            num2 = stack.pop()
            if len(stack) == 0:
                return "ERROR"
            num1 = stack.pop()

            if i == '+':
                stack.append(num1 + num2)
            elif i == '-':
                stack.append(num1 - num2)
            elif i == '*':
                stack.append(num1 * num2)
            elif i == '/':
                stack.append(num1 / num2)
    if len(stack) == 1:
        return stack.pop()
    return "ERROR"




def evaluate(inp):
    postfix = convertToPostfix(inp)
    return evaluatePostfix(postfix)
    # return firstOperator.getNumber()
    # return op.evaluate()
