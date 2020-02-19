import Functions as fn
import Generate as gen


def bubble_test(length):
    print("Bubble sort:")
    # best
    array = gen.array_best(length)
    print(f"Best set.\nStart array: {array}\nSorted: {fn.bubble_sort(array)}\n")
    # random
    array = gen.array_random(length)
    print(f"Random set.\nStart array: {array}\nSorted: {fn.bubble_sort(array)}\n")
    # worth
    array = gen.array_worst(length)
    print(f"Worth set.\nStart array: {array}\nSorted: {fn.bubble_sort(array)}\n")


def improved_bubble_test(length):
    print("Improved bubble sort:")
    # best
    array = gen.array_best(length)
    print(f"Best set.\nStart array: {array}\nSorted: {fn.improved_bubble_sort(array)}\n")
    # random
    array = gen.array_random(length)
    print(f"Random set.\nStart array: {array}\nSorted: {fn.improved_bubble_sort(array)}\n")
    # worth
    array = gen.array_worst(length)
    print(f"Worth set.\nStart array: {array}\nSorted: {fn.improved_bubble_sort(array)}\n")


def insertion_test(length):
    print("Insertion sort:")
    # best
    array = gen.array_best(length)
    print(f"Best set.\nStart array: {array}\nSorted: {fn.insertion_sort(array)}\n")
    # random
    array = gen.array_random(length)
    print(f"Random set.\nStart array: {array}\nSorted: {fn.insertion_sort(array)}\n")
    # worth
    array = gen.array_worst(length)
    print(f"Worth set.\nStart array: {array}\nSorted: {fn.insertion_sort(array)}\n")