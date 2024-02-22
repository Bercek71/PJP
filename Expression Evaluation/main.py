from evaluation import evaluate

def main():
    try:
        n = int(input())
    except ValueError:
        print("ERROR")
        exit()
    if n < 0:
        print("ERROR")
        exit()
    if n > 0:
        print(evaluate(input().strip()))


if __name__ == "__main__":
    print(evaluate("5 + (5 - 2) * 3"))
    # main()
