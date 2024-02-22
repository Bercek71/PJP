def convertToPostfix(inp):
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
            stack.pop()
        elif i == ' ':
            continue
        else:
            return "ERROR"
    return postFix + stack[::-1]


def evaluatePostfix(postfix):
    stack = []
    for i in postfix:
        if isinstance(i, int):
            stack.append(i)
        else:
            if len(stack) < 2:
                return "ERROR"
            second = stack.pop()
            first = stack.pop()
            if i == '+':
                stack.append(first + second)
            elif i == '-':
                stack.append(first - second)
            elif i == '*':
                stack.append(first * second)
            elif i == '/':
                stack.append(first / second)
    if len(stack) > 1:
        return "ERROR"
    return stack[0]


def evaluate(inp):
    postfix = convertToPostfix(inp)
    return evaluatePostfix(postfix)
    # return firstOperator.getNumber()
    # return op.evaluate()
