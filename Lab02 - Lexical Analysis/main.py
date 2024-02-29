# This is a sample Python script.
from scanner import *


# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


def main():
    scaner = Scanner("""    
    -2 + (245 div 3);  // note
2 mod 3 * hello
""")
    token = scaner.nextToken()
    while (token.type != TokenType.EOF):
        print(token)
        token = scaner.nextToken()


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    main()
# See PyCharm help at https://www.jetbrains.com/help/pycharm/
