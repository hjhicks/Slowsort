import random
from time import perf_counter
import sys

MAX = LENGTH = 1000
STEP = 50


def make_list(num_list=[], to_add=LENGTH):
    for i in range(0, to_add):
        num_list.append(random.randint(0, MAX))
        # print(len(numList))
    return num_list


def slow_sort(A, i, j):
    if i >= j:
        return
    m = int((i + j) / 2)
    slow_sort(A, i, m)
    slow_sort(A, m + 1, j)
    if A[j] < A[m]:
        temp = A[j]
        A[j] = A[m]
        A[m] = temp
    slow_sort(A, i, j - 1)


def run_sort():
    buff = 1
    ratio = 0.0
    nums = []
    for i in range(STEP, LENGTH+STEP, STEP):
        if len(nums) == 0:
            nums = make_list(to_add=i)
        else:
            random.shuffle(nums)
            nums = make_list(num_list=nums, to_add=STEP)
        t1_start = perf_counter()
        slow_sort(nums, 0, len(nums) - 1)
        t1_stop = perf_counter()
        elapsed = ops = t1_stop - t1_start
        if buff != 0:
            buff = buff / 1.0
            ratio = ops/buff
            unit = "Seconds"
        if elapsed > 60.0:
            elapsed = elapsed / 60
            unit = "Minutes"
        if elapsed > 60.0:
            elapsed = elapsed / 60
            unit = "Hours"
        print(f'{elapsed/1.0} {unit} for {i} numbers; Ratio = {ratio}\n')
        if ops == 0:
            buff = 1.0
        else:
            buff = ops


if __name__ == '__main__':
    sys.setrecursionlimit(1500)
    run_sort()
