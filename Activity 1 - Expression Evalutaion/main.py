from evaluation import evaluate


def main():
    try:
        n = int(input())
        values = []
    except ValueError:
        print("ERROR")
        exit()
    for i in range(n):
        values.append(evaluate((input().strip())))

    for i in values:
        print(i)


if __name__ == "__main__":
    main()
