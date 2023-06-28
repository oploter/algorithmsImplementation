from random import randint
def sum_n(n1: list, n2: list) -> list:
    n = max(len(n1), len(n2))
    n1.extend(0 for i in range(n - len(n1)))
    n2.extend(0 for i in range(n - len(n2)))
    for i in range(n):
        n1[i] += n2[i]
    return n1

def del_unnec(N: list) -> list:
    while len(N) > 1 and N[-1] == 0:
        N.pop()
    return N

def shift(N: list) -> list:
    mem = 0
    for i in range(len(N)):
        N[i] += mem
        if N[i] >= 10:
            mem = N[i] // 10
        else:
            mem = 0
        N[i] %= 10
    if mem > 0:
        N.append(mem)
    return N


def mult(n1: list, n2: list) -> list:
    n = max(len(n1), len(n2))
    if n == 1:
        return [n1[0] * n2[0]]
    if n % 2 != 0:
        n += 1
    n1.extend((0 for _ in range(n - len(n1))))
    n2.extend((0 for _ in range(n - len(n2))))
    n11 = n1[:n//2]
    n12 = n1[n//2:]
    n21 = n2[:n//2]
    n22 = n2[n//2:]
    #assert len(n11) == len(n12), 'Assert: len(n11) != len(n12)'
    #assert len(n21) == len(n22), 'Assert: len(n21) != len(n22)'
    #assert len(n11) == len(n21), 'Assert: len(n11) != len(n21)'
    N1 = mult(n11, n21)
    #assert len(N1) == n - 1
    N2 = mult(n12, n22)
    #assert len(N2) == n - 1
    N3 = sum_n(mult(n11, n22), mult(n12, n21))
    #assert len(N3) == n - 1
    ans = [0 for _ in range(2 * n - 1)]
    for i in range(2 * n - 1):
        if i <= n - 2:
            ans[i] += N1[i]
        if n // 2 <= i <= n + n//2 - 2:
            ans[i] += N3[i - n // 2]
        if n <= i <= 2 * n - 2:
            ans[i] += N2[i - n]
    assert len(ans) == 2 * n - 1
    return ans

"""
num1: "12345678"
num2: "32432"
"""

num1 = [int(el) for el in input()[::-1]]
num2 = [int(el) for el in input()[::-1]]
ans = del_unnec(shift(mult(num1, num2)))
ans_s = ''.join(str(el) for el in ans[::-1])
print(ans_s)

