import enum


class TokenType(enum.Enum):
    EOF = 0
    ID = 1
    NUMBER = 2
    OP = 3
    LPAR = 4
    RPAR = 5
    DIV = 6
    MOD = 7
    SEMICOLON = 8


class Scanner:
    def __init__(self, input):
        self.input = input
        self.index = 0

    def nextToken(self):
        currentStartintIndex = self.index
        if currentStartintIndex >= len(self.input):
            return Token(TokenType.EOF, None)
        waitForCommentEnd = False
        for i in range(currentStartintIndex, len(self.input)):
            if waitForCommentEnd and self.input[i] == '\n':
                waitForCommentEnd = False
                continue
            if waitForCommentEnd:
                continue
            if self.input[i] == " ":
                continue
            if self.input[i].isdigit():
                digits = self.input[i]
                j = i + 1
                while self.input[j].isdigit():
                    digits += self.input[j]
                    j += 1
                self.index = j
                return Token(TokenType.NUMBER, digits)
            if self.input[i] in ['+', '*', '-', '/']:
                if self.input[i] == '/' and self.input[i + 1] == '/':
                    waitForCommentEnd = True
                    self.index = i + 2
                    continue
                self.index = i + 1
                return Token(TokenType.OP, self.input[i])
            if self.input[i] == '(':
                self.index = i + 1
                return Token(TokenType.LPAR, None)
            if self.input[i] == ')':
                self.index = i + 1
                return Token(TokenType.RPAR, None)
            if self.input[i] == ';':
                self.index = i + 1
                return Token(TokenType.SEMICOLON, None)
            if self.input[i] == 'd' and self.input[i + 1] == 'i' and self.input[i + 2] == 'v':
                self.index = i + 3
                return Token(TokenType.DIV, None)
            if self.input[i] == 'm' and self.input[i + 1] == 'o' and self.input[i + 2] == 'd':
                self.index = i + 3
                return Token(TokenType.MOD, None)
            if self.input[i].isalpha():
                identifier = self.input[i]
                j = i + 1
                while self.input[j].isalpha() or self.input[j].isdigit():
                    identifier += self.input[j]
                    j += 1
                self.index = j + 1
                return Token(TokenType.ID, identifier)



class Token:
    def __init__(self, type):
        self.type = type
        self.value = None

    def __init__(self, type, value):
        self.type = type
        self.value = value

    def __str__(self):
        if self.value is None:
            return f"{self.type}"
        return f"{self.type}: {self.value}"

    def __repr__(self):
        return self.__str__()
