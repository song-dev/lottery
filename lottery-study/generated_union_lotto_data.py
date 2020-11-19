import csv
import random

from config import *


def generated():
    red_ball = []
    # random.seed(time.time())
    while len(red_ball) < 6:
        s = random.randint(1, 33)
        if s not in red_ball:
            red_ball.append(s)
    red_ball.sort()
    # red_ball = random.sample(range(1, 34), 6)
    # red_ball.sort()
    blue_ball = random.sample(range(1, 17), 1)
    blue_ball.sort()
    return red_ball, blue_ball


def red_old_data():
    data = list()
    with open(UNION_LOTTO_FILE, 'r') as f:
        reader = csv.DictReader(f)
        for line in reader:
            data.append(line[UNION_LOTTO_BOLL[0]] + line[UNION_LOTTO_BOLL[1]] + line[UNION_LOTTO_BOLL[2]]
                        + line[UNION_LOTTO_BOLL[3]] + line[UNION_LOTTO_BOLL[4]] + line[UNION_LOTTO_BOLL[5]]
                        + line[UNION_LOTTO_BOLL[6]])
    print(len(data))
    print(data)
    return data


def matching_continuous(item):
    if item[0] + 1 == item[1] \
            and item[1] + 1 == item[2] \
            and item[2] + 1 == item[3] \
            and item[3] + 1 == item[4] \
            and item[4] + 1 == item[5]:
        return 6
    elif (item[0] + 1 == item[1] and item[1] + 1 == item[2] and item[2] + 1 == item[3] and item[3] + 1 == item[4]) or (
            item[1] + 1 == item[2] and item[2] + 1 == item[3] and item[3] + 1 == item[4] and item[4] + 1 == item[5]):
        return 5
    elif (item[0] + 1 == item[1] and item[1] + 1 == item[2] and item[2] + 1 == item[3]) \
            or (item[1] + 1 == item[2] and item[2] + 1 == item[3] and item[3] + 1 == item[4]) \
            or (item[2] + 1 == item[3] and item[3] + 1 == item[4] and item[4] + 1 == item[5]):
        return 4
    elif (item[0] + 1 == item[1] and item[1] + 1 == item[2]) \
            or (item[1] + 1 == item[2] and item[2] + 1 == item[3]) \
            or (item[2] + 1 == item[3] and item[3] + 1 == item[4]) \
            or (item[3] + 1 == item[4] and item[4] + 1 == item[5]):
        return 3
    elif (item[0] + 1 == item[1]) \
            or (item[1] + 1 == item[2]) \
            or (item[2] + 1 == item[3]) \
            or (item[3] + 1 == item[4]) \
            or (item[4] + 1 == item[5]):
        return 2
    else:
        return 0


def matching_odd_even(item):
    count = 0
    for i in range(len(item)):
        if int(item[i]) % 2 == 0:
            count += 1
    return abs(2 * count - len(item)) < (len(item) - 2)


def matching_all(ball, data):
    red = ball[0]
    blue = ball[1][0]
    union_lotto = "".join([str(i).zfill(2) for i in ball[0]]) + "".join(['%02d' % i for i in ball[1]])
    lotto = list(red)
    lotto.append(blue)
    if (blue not in red) \
            and (matching_odd_even(red)) \
            and (matching_odd_even(lotto)) \
            and (matching_continuous(red) <= 2) \
            and (blue != 16):
        if union_lotto in data:
            print('========包含==========', ball)
        else:
            return True
    return False


if __name__ == '__main__':
    data = red_old_data()
    # 双色球重复概率为 0，红球重复出现次数仅为 2 次
    # 蓝球和红球不相同概率 82%
    # 红球奇偶相差个数最大为 2 为 83%
    # 所有球奇偶相差个数最大为 2 为 91%
    # 最多2个球连续概率 90%
    # 连续两期蓝球不相同概率 89%
    random_1000 = []
    all = []
    for i in range(10000):
        ball = generated()
        if matching_all(ball, data):
            all.append(ball)
    print(len(all))
    print(random.sample(all, 3))
