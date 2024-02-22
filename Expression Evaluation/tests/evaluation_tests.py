from evaluation import evaluate
import unittest


class TestSimpleEvaluate(unittest.TestCase):
    def test_single_evaluate(self):
        self.assertEqual(evaluate("5 + 5"), 10)
        self.assertEqual(evaluate("5 - 5"), 0)
        self.assertEqual(evaluate("5 * 5"), 25)
        self.assertEqual(evaluate("5 / 5"), 1)

    def test_multiple_evaluate(self):
        self.assertEqual(evaluate("5 + 5 + 5"), 15)
        self.assertEqual(evaluate("5 - 5 - 5"), -5)
        self.assertEqual(evaluate("5 * 5 * 5"), 125)
        self.assertEqual(evaluate("5 / 5 / 5"), 0.2)
        self.assertEqual(evaluate("5 + 5 - 5"), 5)
        self.assertEqual(evaluate("5 - 5 + 5"), 5)
        self.assertEqual(evaluate("5 * 5 / 5"), 5)
        self.assertEqual(evaluate("5 / 5 * 5"), 5)
        self.assertEqual(evaluate("5 + 5 * 5"), 30)
        self.assertEqual(evaluate("5 * 5 + 5"), 30)
        self.assertEqual(evaluate("5 + 5 / 5"), 6)
        self.assertEqual(evaluate("5 / 5 + 5"), 6)
        self.assertEqual(evaluate("5 + 5 * 5 + 5"), 35)
        self.assertEqual(evaluate("5 * 5 + 5 + 5"), 35)
        self.assertEqual(evaluate("5 + 5 / 5 + 5"), 11)
        self.assertEqual(evaluate("5 / 5 + 5 + 5"), 11)
        self.assertEqual(evaluate("5 + 5 * 5 - 5"), 25)
        self.assertEqual(evaluate("5 * 5 + 5 - 5"), 25)
        self.assertEqual(evaluate("5 + 5 / 5 - 5"), 1)
        self.assertEqual(evaluate("5 / 5 + 5 - 5"), 1)
        self.assertEqual(evaluate("5 + 5 * 5 * 5"), 130)
        self.assertEqual(evaluate("5 * 5 + 5 * 5"), 50)
        self.assertEqual(evaluate("5 + 5 / 5 / 5"), 6)
    def test_infixes(self):
        self.assertEqual(evaluate("5 * 3 + 2"), 17)
        self.assertEqual(evaluate("5 + 3 * 2"), 11)
        self.assertEqual(evaluate("5 * 3 - 2"), 13)
        self.assertEqual(evaluate("5 - 3 * 2"), -1)
        self.assertEqual(evaluate("5 * 3 / 2"), 7.5)
        self.assertEqual(evaluate("5 / 3 * 2"), 3.3333333333333335)
        self.assertEqual(evaluate("5 * 3 * 2"), 30)
        self.assertEqual(evaluate("5 + 3 + 2"), 10)
        self.assertEqual(evaluate("5 - 3 - 2"), 0)
        self.assertEqual(evaluate("5 * 3 * 2"), 30)
        self.assertEqual(evaluate("5 / 3 / 2"), 0.8333333333333334)
        self.assertEqual(evaluate("5 + 3 - 2"), 6)
        self.assertEqual(evaluate("5 - 3 + 2"), 4)
        self.assertEqual(evaluate("5 * 3 / 2"), 7.5)
        self.assertEqual(evaluate("5 / 3 * 2"), 3.3333333333333335)
        self.assertEqual(evaluate("5 + 3 * 2 + 2"), 15)

class TestInvalidInput(unittest.TestCase):
    def test_invalid_input(self):
        self.assertEqual(evaluate("5 + 5 +"), "ERROR")
        self.assertEqual(evaluate("5 + 5 5"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 +"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 5"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 +"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 5"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 +"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 5"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 + 5 +"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 + 5 5"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 + 5 + 5 +"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 + 5 + 5 5"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 + 5 + 5 + 5 +"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 + 5 + 5 + 5 5"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 + 5 + 5 + 5 + 5 +"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 + 5 + 5 + 5 + 5 5"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 + 5 + 5 + 5 + 5 + 5 +"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 + 5 + 5 (    + 5 + 5 + )"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + 5 + 5 + 5 + 5 (    + 5 + 5 + 5"), "ERROR")
        self.assertEqual(evaluate("asdfsgdfg"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + ) 5 + 5 ( + 5 + 5 (    + 5 + 5 + 5"), "ERROR")
        self.assertEqual(evaluate("5 + 5 + 5 + (5) + 5 + (5) + 5 (    + 5 + 5 + 5"), "ERROR")

class TestAprentheses(unittest.TestCase):
    def test_parentheses(self):
        self.assertEqual(evaluate("(5 + 5) + 5"), 15)
        self.assertEqual(evaluate("5 + (5 + 5)"), 15)
        self.assertEqual(evaluate("5 + (5 + 5) + 5"), 15)
        self.assertEqual(evaluate("5 + (5 + 5 + 5)"), 15)
        self.assertEqual(evaluate("(5 + 5) + 5 + 5"), 15)
        self.assertEqual(evaluate("(5 + 5 + 5) + 5"), 15)
        self.assertEqual(evaluate("5 + (5 + 5 + 5 + 5)"), 20)
        self.assertEqual(evaluate("(5 + 5 + 5 + 5) + 5"), 25)